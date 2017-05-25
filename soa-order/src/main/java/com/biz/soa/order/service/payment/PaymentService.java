package com.biz.soa.order.service.payment;

import com.biz.gbck.dao.mysql.po.order.Order;
import com.biz.gbck.dao.mysql.po.order.OrderPayment;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.exceptions.order.PaymentException;
import com.biz.gbck.vo.IdReqVo;
import com.biz.gbck.vo.payment.req.IWechatPaymentReqVo;
import com.biz.gbck.vo.payment.resp.*;
import com.biz.pay.wechat.res.WechatPayNotifyRespVo;

import java.util.List;
import java.util.Map;

public interface PaymentService {

	/**
	 * 微信统一下单
	 * @throws PaymentException
	 */
	WechatPayResp wechatPay(IWechatPaymentReqVo req, Long orderId) throws PaymentException;
	/**
	 * 微信统一下单
	 * @throws PaymentException
	 */
	WechatPayResp wechatPay(IWechatPaymentReqVo req, Order order) throws PaymentException;

	/**
	 * 微信验证订单是否支付成功
	 */
	PaidRespVo queryWechatPaid(String tradeNo, Long paymentId, String appId) throws PaymentException;

	/**
	 * 查询支付宝是否支付成功
	 * @throws PaymentException
	 */
	PaidRespVo queryAlipayPaid(String tradeNo, Long paymentId) throws PaymentException;
	

	/**
	 * 获取支付宝支付签名
	 * @throws PaymentException
	 */
	AlipaySignRespVo getAlipaySign(Long orderId) throws PaymentException;

	/**
	 * 获取支付宝支付签名
	 */
	AlipaySignRespVo getAlipaySign(Order order) throws PaymentException;
	
	/**
	 * 获取WAP支付宝支付签名
	 */
	AlipaySignRespVo getWapAlipaySign(Long orderId) throws PaymentException;

	/**
	 * 货到付款支付
	 */
	PaymentRespVo noNeedPay(Long orderId);

	/**
	 * 货到付款支付
	 *
	 */
	PaymentRespVo noNeedPay(Order order);
	
	/**
	 * 确认支付
	 */
	void confirmPaid(Long paymentId);
	
	/**
	 * 阿里回调
	 */
	void aliNotify(Map<String, String> map);
	
	void wechatTradeNotify(WechatPayNotifyRespVo notifyRes);

	PaymentQueryResultRespVo queryPaid(IdReqVo req) throws PaymentException;
	
	void confirmPaid(Long orderId, OrderPayment payment);

	void savePaymentTradeNo(Long paymentId, String tradeNo);

    List<Long> getSupportedPaymentTypes(String userId) throws DepotNextDoorException;
}