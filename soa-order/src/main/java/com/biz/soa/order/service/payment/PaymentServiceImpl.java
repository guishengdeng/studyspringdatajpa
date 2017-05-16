package com.biz.soa.order.service.payment;

import com.biz.core.asserts.BusinessAsserts;
import com.biz.core.util.*;
import com.biz.gbck.dao.mysql.po.order.Order;
import com.biz.gbck.dao.mysql.po.order.OrderPayment;
import com.biz.gbck.dao.mysql.po.payment.AlipayPaymentLogPo;
import com.biz.gbck.dao.mysql.po.payment.WechatPaymentLogPo;
import com.biz.gbck.dao.mysql.repository.order.OrderPaymentRepository;
import com.biz.gbck.dao.mysql.repository.payment.Alipay.AlipayPaymentLogPoRepository;
import com.biz.gbck.dao.mysql.repository.payment.wechatpay.WechatPaymentLogPoRepository;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.enums.order.OrderStatus;
import com.biz.gbck.enums.order.PaymentStatus;
import com.biz.gbck.enums.order.PaymentType;
import com.biz.gbck.exceptions.DepotNextDoorExceptions;
import com.biz.gbck.exceptions.order.PaymentException;
import com.biz.gbck.vo.IdReqVo;
import com.biz.gbck.vo.payment.req.IWechatPaymentReqVo;
import com.biz.gbck.vo.payment.resp.*;
import com.biz.pay.alipay.AlipayFactory;
import com.biz.pay.alipay.IAlipayPayment;
import com.biz.pay.alipay.IAlipayPayment.ParamKeys;
import com.biz.pay.alipay.config.AlipayConfig;
import com.biz.pay.alipay.util.AlipaySubmit;
import com.biz.pay.wechat.WeChatPayFactory;
import com.biz.pay.wechat.lang.PropertyCollector;
import com.biz.pay.wechat.req.UnifiedOrder;
import com.biz.pay.wechat.res.OrderQueryResponse;
import com.biz.pay.wechat.res.WechatPayNotifyRespVo;
import com.biz.pay.wechat.res.WechatPayRespVo;
import com.biz.service.AbstractBaseService;
import com.biz.service.order.frontend.OrderFrontendService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.DateUtils;
import org.codelogger.utils.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static com.biz.gbck.enums.order.PaymentType.ALIPAY;
import static com.biz.gbck.enums.order.PaymentType.WECHAT;
import static com.biz.pay.alipay.IAlipayPayment.ParamKeys.*;
import static com.biz.pay.wechat.lang.Keys.TIME_EXPIRE_DATE_FORMATER;

@Service
public class PaymentServiceImpl extends AbstractBaseService implements PaymentService {

	@Autowired
	private OrderFrontendService orderFrontendService;

	@Autowired
	private OrderPaymentRepository paymentRepository;

	@Autowired
	private WechatPaymentLogPoRepository wechatPaymentLogPoRepository;

	@Autowired
	private AlipayPaymentLogPoRepository alipayPaymentLogPoRepository;
	
	private SyncUtil paymentSyncUtil = new SyncUtil(256);

	@Override
	@Transactional
	public WechatPayResp wechatPay(IWechatPaymentReqVo req, Order order) throws PaymentException {
		Long orderId = order.getId();
		if (logger.isDebugEnabled()) {
			logger.debug("getOrderId {}", orderId);
		}
		OrderPayment payment = this.getPayablePayment(order, WECHAT);
		String errorMsg;
		try {
			String appid = WeChatPayFactory.getCurrentOpenAppid();
			WeChatPayFactory weChatPayFactory = WeChatPayFactory.newInstance();
			String timeExpire = DateUtils.formatDate(payment.getExpireTimestamp(), TIME_EXPIRE_DATE_FORMATER);
			UnifiedOrder unifiedOrder = weChatPayFactory.newUnifiedOrder(appid, payment.getId().toString(), payment.getSubject(),
					payment.getPayAmount(), req.getIp(), req.getTradeType(), timeExpire, req.getOpenid());
			WechatPayRespVo unifiedOrderResponse = unifiedOrder.execute();
			if (logger.isDebugEnabled()) {
				logger.debug("Get response from wechat: {}", unifiedOrderResponse.getProperties());
			}
			if (unifiedOrderResponse.isProcessSuccess()) {
				WechatPayResp resp = new WechatPayResp();

				resp.setAppid(unifiedOrderResponse.getAppId());
				resp.setPartnerid(unifiedOrderResponse.getMchId());
				resp.setPrepayid(unifiedOrderResponse.getPrepayId());
				resp.setNoncestr(unifiedOrderResponse.getNonceStr());
				resp.setTimestamp(String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())));
				resp.setOrderId(orderId);
				String sign = weChatPayFactory.newSigner(req.getAppId()).sign(resp);
				resp.setSign(sign);
				return resp;
			}
			errorMsg = StringUtils.isNotBlank(unifiedOrderResponse.getErrorDescption()) ? unifiedOrderResponse.getErrorDescption()
					: unifiedOrderResponse.getReturnMessage();
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error("生成预付单失败.", e);
			}
			errorMsg = e.getMessage();
		}
		throw new PaymentException(errorMsg);
	}


	@Override
	public PaidRespVo queryWechatPaid(String tradeNo, Long paymentId, String appId) throws PaymentException {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("tradeNo: {}, paymentId: {}, appId: {}", tradeNo, paymentId, appId);
			}
			OrderQueryResponse orderQueryResponse = WeChatPayFactory.newInstance().newOrderQuery(appId, tradeNo,
					paymentId.toString()).execute();
			return new CommonPaidRespVo(orderQueryResponse.getTradeNo(), orderQueryResponse.isPaid(),
					orderQueryResponse.getOpenId());
		} catch (Exception e) {
			throw new PaymentException("查询微信支付异常");
		}
	}


	@Override
	public PaidRespVo queryAlipayPaid(String tradeNo, Long paymentId) throws PaymentException {
		if (StringUtils.isEmpty(tradeNo) && paymentId == null) {
			throw new PaymentException("tradeNo and paymentId can not be both null.");
		}
		if (logger.isDebugEnabled()) {
			logger.debug("tradeNo--> {}", tradeNo);
			logger.debug("paymentId--> {}", paymentId);
		}

		Map<String, String> sParaTemp = new HashMap<>();
		sParaTemp.put(ParamKeys.SERVICE, IAlipayPayment.Service.SINGLE_TRADE_QUERY);
		sParaTemp.put(ParamKeys.PARTNER, AlipayConfig.partner);
		sParaTemp.put(ParamKeys._INPUT_CHARSET, AlipayConfig.input_charset);
		sParaTemp.put(ParamKeys.TRADE_NO, tradeNo);
		sParaTemp.put(ParamKeys.OUT_TRADE_NO, paymentId == null ? null : paymentId.toString());
		// 建立请求
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("查询支付宝支付请求参数:{}", JsonUtil.obj2Json(sParaTemp));
			}
			String sHtmlText = AlipaySubmit.buildRequest("", "", sParaTemp);
			if (sHtmlText != null) {
				if (logger.isDebugEnabled()) {
					logger.debug("查询支付宝支付结果: {}", sHtmlText);
				}
				Properties properties;
				SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
				InputStream source = new ByteArrayInputStream(sHtmlText.getBytes("utf-8"));
				PropertyCollector pc = new PropertyCollector();
				saxParser.parse(source, pc);
				properties = pc.returnProperties();
				// 请求是否成功
				String isSuccess = properties.getProperty("is_success");
				// 交易状态
				String tradeStatus = properties.getProperty(ParamKeys.TRADE_STATUS);
				boolean isPaid = "T".equals(isSuccess) && (StringUtils.equals(IAlipayPayment.TradeStatus.TRADE_SUCCESS,
						tradeStatus) || StringUtils.equals(IAlipayPayment.TradeStatus.TRADE_FINISHED, tradeStatus));

				return new CommonPaidRespVo(properties.getProperty("trade_no"), isPaid, properties.getProperty(BUYER_EMAIL));
			}
		} catch (Exception e) {
			logger.error("查询支付宝支付状态失败", e);
		}
		throw new PaymentException("查询支付宝单笔交易失败");
	}


	@Transactional
	public AlipaySignRespVo getAlipaySign(Order order) throws PaymentException {
		Timers timers = Timers.createAndBegin(logger.isDebugEnabled());
		OrderPayment payment = this.getPayablePayment(order, ALIPAY);
		timers.record("生成或获取支付单");
		String params;
		try {
			params = AlipayFactory.newInstance().getSignedMobilePayRequestParams(payment.getId().toString(), payment.getSubject(),
					payment.getPayAmount(), payment.getExpireTimestamp());
		} catch (Throwable e) {
			if (logger.isDebugEnabled()) {
				logger.error("get alipay mobile pay request params sign failed", e);
			}
			throw new PaymentException("签名失败，无法发起支付");
		}
		timers.record("计算签名");
		timers.print("use time alipay-sign");
		return new AlipaySignRespVo(params, order.getId(), order.getOrderCode());
	}

	@Override
	@Transactional
	public AlipaySignRespVo getWapAlipaySign(Long orderId) throws PaymentException {
		if (logger.isDebugEnabled()) {
			logger.debug("getOrderId --------->{}", orderId);
		}
		Order order = orderFrontendService.getOrder(orderId);
		return this.getAlipaySign(order);
	}

	@Override
	@Transactional
	public PaymentRespVo noNeedPay(Order order) {
		OrderPayment payment = getPayablePayment(order, PaymentType.PAY_ON_DELIVERY);
		payment.setPayStatus(PaymentStatus.PAYED);
		payment.setSuccessDate(new Date(System.currentTimeMillis()));
		confirmPaid(order.getId(), payment);
		return new PaymentRespVo(order.getId(), order.getOrderCode());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bozhi.service.order.PaymentService#confirmPaid(java.lang.Long)
	 */
	@Override
	@Transactional
	public void confirmPaid(Long paymentId) {
		OrderPayment payment = paymentRepository.findOne(paymentId);
		this.confirmPaid(payment.getOrder().getId(), payment);
	}

	@Override
	@Transactional
	public void aliNotify(Map<String, String> params) {
		String notifyId = params.get(NOTIFY_ID);
		String ourTradeNo = params.get(OUT_TRADE_NO);
		String tradeNo = params.get(TRADE_NO);
		Long paymentId = Long.valueOf(ourTradeNo);
		String tradeStatus = params.get(TRADE_STATUS);
		// 该笔交易的买家付款时间。格式为 yyyy-MM-dd HH:mm:ss
		// String gmtPaymentStr = params.get(GMT_PAYMENT);
		// Long paidTime = StringUtils.isNotBlank(gmtPaymentStr) ?
		// org.codelogger.utils.DateUtils.getDateFromString(gmtPaymentStr).getTime()
		// : null;
		Integer totalFee = StringTool.formatPriceToFen(params.get(TOTAL_FEE));
		String buyerEmail = params.get(BUYER_EMAIL);
		String buyerId = params.get(BUYER_ID);
		Integer discount = StringTool.formatPriceToFen(params.get(DISCOUNT));
		Timestamp gmtCreate = params.get(GMT_CREATE) == null ? null : DateUtil.newTimestamp(params.get(GMT_CREATE));
		Timestamp gmtPayment = params.get(GMT_PAYMENT) == null ? null : DateUtil.newTimestamp(params.get(GMT_PAYMENT));
		String isTotalFeeAdjust = params.get(IS_TOTAL_FEE_ADJUST);
		Timestamp notifyTime = params.get(NOTIFY_TIME) == null ? null : DateUtil.newTimestamp(params.get(NOTIFY_TIME));
		String paymentType = params.get(PAYMENT_TYPE);
		Integer price = StringTool.formatPriceToFen(params.get(PRICE));
		Integer quantity = params.get(QUANTITY) == null ? null : Integer.parseInt(params.get(QUANTITY));
		String subject = params.get(SUBJECT);
		String useCoupon = params.get(USE_COUPON);

		logger.info("process an alipay trade notify: orderPaymentId[{}], tradeno[{}], tradeStauts[{}]", paymentId, tradeNo, tradeStatus);

		// 保存 alipay支付通知log
		AlipayPaymentLogPo logPo = new AlipayPaymentLogPo();
		logPo.setId(idService.nextId());
		logPo.setOrderPaymentId(paymentId);
		logPo.setTransactionId(tradeNo);
		logPo.setLog(JsonUtil.obj2Json(params));
		logPo.setBuyerEmail(buyerEmail);
		logPo.setBuyerId(buyerId);
		logPo.setDiscount(discount);
		logPo.setGmtCreate(gmtCreate);
		logPo.setGmtPayment(gmtPayment);
		logPo.setIsTotalFeeAjust(isTotalFeeAdjust);
		logPo.setNotifyId(notifyId);
		logPo.setNotifyTime(notifyTime);
		logPo.setPaymentType(paymentType);
		logPo.setPrice(price);
		logPo.setTotalFee(totalFee);
		logPo.setQuantity(quantity);
		logPo.setSubject(subject);
		logPo.setTradeStatus(tradeStatus);
		logPo.setUseCoupon(useCoupon);
		alipayPaymentLogPoRepository.save(logPo);

        // 将支付宝的支付单号存取到 payment
        this.savePaymentTradeNo(paymentId, tradeNo);
		if (IAlipayPayment.TradeStatus.TRADE_FINISHED.equals(tradeStatus)) {
			logger.info("alipay trade notify:trade_finish->orderPaymentId[],tradeno[]", paymentId, tradeNo);
			// 判断该笔订单是否在商户网站中已经做过处理
			// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
			// 如果有做过处理，不执行商户的业务程序
			// TODO trade finished deal

			// 注意：
			// 退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
		} else if (IAlipayPayment.TradeStatus.TRADE_SUCCESS.equals(tradeStatus)) {
			// payment
			OrderPayment payment = paymentRepository.findOne(paymentId);
			if (payment != null && StringUtils.isNotEmpty(payment.getTradeNo())&& payment.getPayStatus() == PaymentStatus.PAYED) {
				logger.info("alipay trade notify 已经将交易单号写入--{}", tradeNo);
			} else {
				confirmPaid(paymentId);
			}
		}
	}

	@Override
	@Transactional
	public void wechatTradeNotify(WechatPayNotifyRespVo notifyRes) {
		OrderPayment payment = paymentRepository.findOne(Long.valueOf(notifyRes.getOutTradeNo()));
		logger.debug("save wechat payment notify to db log.");
		if (notifyRes.isProcessSuccess()) {
			if (payment.getPayStatus() != PaymentStatus.PAYED) {
				try {
					String notifyJson = JsonUtil.obj2Json(notifyRes.getProperties());
					logger.info("log wechat payment notify:'{}'", notifyJson);

					WechatPaymentLogPo paymentLog = new WechatPaymentLogPo();
					paymentLog.setId(idService.nextId());
					paymentLog.setOrderPaymentId(payment.getId());
					paymentLog.setTransactionId(notifyRes.getTransactionId());
					paymentLog.setLog(notifyJson);
					paymentLog.setTs(new Timestamp(System.currentTimeMillis()));
					paymentLog.setResultCode(notifyRes.getResultCode());
					paymentLog.setErrCode(notifyRes.getErrorCode());
					paymentLog.setErrCodeDes(notifyRes.getErrorDescption());
					paymentLog.setOpenid(notifyRes.getOpenId());
					paymentLog.setTradeType(notifyRes.getTradeType());
					paymentLog.setBankType(notifyRes.getBankType());
					paymentLog.setTotalFee(notifyRes.getTotalFee());
					paymentLog.setFeeType(notifyRes.getFeeType());
					paymentLog.setCashFee(notifyRes.getCashFee());
					paymentLog.setCashFeeType(notifyRes.getCashFeeType());
					paymentLog.setCouponFee(notifyRes.getCouponFee());
					paymentLog.setCouponCount(notifyRes.getCouponCount());
					paymentLog.setCouponId(notifyRes.getCouponId());
					paymentLog.setAttach(notifyRes.getAttach());
					paymentLog.setTimeEnd(new Timestamp(notifyRes.getTimeEnd().getTime()));
					wechatPaymentLogPoRepository.save(paymentLog);
				} catch (Exception e) {
					logger.info("log wechat payment notify:'{}' failed.", e);
				}

				if (StringUtils.equals(payment.getTradeNo(), notifyRes.getTransactionId())) {
                    // 将微信的支付单号存取到 payment
                    this.savePaymentTradeNo(payment.getId(), notifyRes.getTradeNo());
					this.confirmPaid(payment.getId());
				}
			}
		} else {
			logger.warn("微信支付查询处理失败: {}", notifyRes.getErrorDescption());
		}
	}

	// 支付后更新订单状态
	@Override
	@Transactional
	public void confirmPaid(final Long orderId, final OrderPayment payment) {
		paymentSyncUtil.syncExecute(new SyncExecutionUnit() {
			@Override
			public boolean isExecutable() {
				return true;
			}

			@Override
			public Object getSyncLockSource() {
				return orderId;
			}

			@Override
			public <R> R execute() {
				payment.setPayStatus(PaymentStatus.PAYED);
				paymentRepository.updatePaymentState(payment.getId(), PaymentStatus.PAYED.getValue());
				logger.info("订单查询支付成功：orderId:{}",orderId);
				Order order = orderFrontendService.getOrder(orderId);
				updateOrderPayState(order, payment, payment.getPayAmount());
				return null;
			}
		});
	}

	@Override
	@Transactional
	public PaymentQueryResultRespVo queryPaid(IdReqVo reqVo) throws PaymentException {
		// 查询订单
		Long orderId = reqVo.getId();
		Order order = orderFrontendService.getOrder(orderId);
		OrderPayment payment = this.getPayablePayment(order, null);
		if (logger.isDebugEnabled()) {
			logger.debug("payment Id : {}", payment.getId());
		}
		PaymentType paymentType = payment.getPaymentType();
		PaymentQueryResultRespVo resp = new PaymentQueryResultRespVo();
		resp.setPaymentType(paymentType.getValue());
		resp.setOrderId(orderId);
		resp.setOrderCode(order.getOrderCode());
		resp.setPayAmount(payment.getPayAmount());
		switch (paymentType) {
			case ALIPAY:
				try {
					PaidRespVo paidRespVo = this.queryAlipayPaid(null, payment.getId());
					if (paidRespVo.isPaid()) {
						if (StringUtils.isEmpty(payment.getTradeNo())) {
							logger.info("order alipay success save tradeNo:[{}]", paidRespVo.getTradeNo());
							this.savePaymentTradeNo(payment.getId(), paidRespVo.getTradeNo());
						}
						resp.setPaid(true);
						resp.setMessage("您的订单已经成功提交");
					} else {
						resp.setPaid(false);
						resp.setMessage("您的订单未成功提交");
					}
				} catch (PaymentException e) {
					e.printStackTrace();
				}
				break;
			case WECHAT:
				try {
					PaidRespVo paidRespVo = queryWechatPaid(null, payment.getId(), null);
					if (paidRespVo.isPaid()) {
						if (StringUtils.isEmpty(payment.getTradeNo())) {
							logger.info("order wechatpay success save tradeNo:[{}]", paidRespVo.getTradeNo());
							this.savePaymentTradeNo(payment.getId(), paidRespVo.getTradeNo());
						}
						resp.setPaid(true);
						resp.setMessage("您的订单已经成功提交");
					} else {
						resp.setPaid(false);
						resp.setMessage("您的订单未成功提交");
					}
				} catch (PaymentException e) {
					e.printStackTrace();
				}
				break;
			case PAY_ON_DELIVERY:
				resp.setPaid(true);
				resp.setMessage("您的订单已经成功提交");
				break;
			default:
				throw new PaymentException("不支持的支付方式:" + paymentType);
		}

		if (resp.isPaid()) {
			this.confirmPaid(orderId, payment);
		}

		return resp;
	}

	@Override
	@Transactional
	public void savePaymentTradeNo(Long paymentId, String tradeNo) {
		if(StringUtils.isNotEmpty(tradeNo)){
			logger.info("order online pay success save tradeNo:[{}]",tradeNo);
			OrderPayment payment = this.paymentRepository.findOne(paymentId);
			payment.setTradeNo(tradeNo);
			payment.setUpdateTimestamp(new Timestamp(System.currentTimeMillis()));
			this.paymentRepository.save(payment);
		}
	}

	@Override
	public WechatPayResp wechatPay(IWechatPaymentReqVo req, Long orderId) throws PaymentException {
		Order order = orderFrontendService.getOrder(orderId);
		return this.wechatPay(req, order);
	}

	@Override
	public AlipaySignRespVo getAlipaySign(Long orderId) throws PaymentException {
		Order order = orderFrontendService.getOrder(orderId);
		return this.getAlipaySign(order);
	}

	@Override
	public PaymentRespVo noNeedPay(Long orderId) {
		Order order = orderFrontendService.getOrder(orderId);
		return this.noNeedPay(order);
	}

	@Override
	public List<PaymentType> getSupportedPaymentTypes(String userId) {
		//TODO 获取用户可用支付方式
		return ArrayUtils.toList(PaymentType.values());
	}

	/**
	 * 创建支付单
	 * @return
	 */
	private OrderPayment createPayment(Order order, PaymentType paymentType) {
		OrderPayment payment = new OrderPayment();
		payment.setId(idService.nextId());
		payment.setOrder(order);
		payment.setExpireTimestamp(order.getExpireTimestamp());
		payment.setPayAmount(order.getPayAmount());
		payment.setPaymentType(paymentType);
		payment.setPayStatus(PaymentStatus.CREATE_PAYMENT);
		payment.setSubject(order.getSubject()); //TODO
		paymentRepository.save(payment);
		logger.debug("create new payment, orderId={}, paymentType={}, paymentId={}", order.getId(), paymentType, payment.getId());
		return payment;
	}

	private void updateOrderPayState(final Order order,final OrderPayment payment,final Integer payAmount){
		if (order.isPayable()) {
			order.setStatus(OrderStatus.DELIVERED);
			order.setPayStatus(PaymentStatus.PAYED);
			order.setPaymentType(payment.getPaymentType());
			order.setPayAmount(payAmount);
			orderFrontendService.saveOrder(order);
		} else {
			logger.warn("订单无法支付, orderId=[{}],paymentId=[{}], status: {} ", order.getId(), payment.getId(),
					order.getStatus());
		}
	}
	// 获取可支付的支付单
	private OrderPayment getPayablePayment(Order order, PaymentType paymentType) {
		if (paymentType == null) {
			paymentType = order.getPaymentType();
			logger.warn("订单默认支付方式: {}", paymentType);
		}
		Long orderId = order.getId();
		if (logger.isDebugEnabled()) {
			logger.debug("获取可用支付单, orderId={}, paymentType={}", orderId, paymentType);
		}
		Timers timers = Timers.createAndBegin(logger.isDebugEnabled());

		// 1.获取支付单
		BusinessAsserts.notNull(order, DepotNextDoorExceptions.Order.ORDER_NOT_EXIST);
		BusinessAsserts.isTrue(order.getPayStatus() == PaymentStatus.CREATE_PAYMENT, DepotNextDoorExceptions.Order
				.ORDER_PAYED);
		timers.record("查询订单");

		OrderPayment payment = null;
		List<OrderPayment> payments = order.getPayments();
		if (CollectionUtils.isNotEmpty(payments)) {
			for (OrderPayment orderPayment : payments) {
				if (orderPayment.getStatus() == CommonStatusEnum.ENABLE) {
					payment = orderPayment;
					break;
				}
			}
		}
		timers.record("获取支付单");
		// 2.检查支付单是否可用,如果不可用,创建新的支付单,作废其他支付单
		if (payment != null) {
			logger.error("find old payment id={} orderId={} paymentUserId={}",
					payment.getId(),orderId, order.getUserId());
			payment.setStatus(CommonStatusEnum.DISABLE);
			paymentRepository.save(payment);
		}
		payment = createPayment(order, paymentType);
		logger.error("getPayment orderId={} paymentId={}",orderId, payment.getId());
		timers.record("处理支付单");
		timers.print("use time get-payment");
		return payment;
	}

}
