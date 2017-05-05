package com.biz.soa.order.service.payment;

import com.biz.gbck.dao.mysql.po.order.Order;
import com.biz.gbck.dao.mysql.po.order.OrderPayment;
import com.biz.gbck.exceptions.order.PaymentException;
import com.biz.gbck.vo.IdReqVo;
import com.biz.gbck.vo.payment.req.IUnifiedPaymentReqVo;
import com.biz.gbck.vo.payment.resp.*;
import com.biz.pay.wechat.res.OrderNotifyResponse;

import java.util.Map;

public interface PaymentService {

//	List<PaymentWay> findSupportedWay(UserRo userRo, OrderType type);

	/**
	 * 微信统一下单
	 * @author yanweijin
	 * @date 2016年8月29日
	 * @param req
	 * @param orderId
	 * @return
	 * @throws PaymentException
	 */
	UnifiedOrderResponseVo wechatUnifiedOrder(IUnifiedPaymentReqVo req, Long orderId) throws PaymentException;
	/**
	 * 微信统一下单
	 * @author yanweijin
	 * @date 2016年8月29日
	 * @param req
	 * @return
	 * @throws PaymentException
	 */
	UnifiedOrderResponseVo wechatUnifiedOrder(IUnifiedPaymentReqVo req, Order order) throws PaymentException;

	/**
	 * 微信验证订单是否支付成功
	 * @author jun.liu(by xiaoyu)
	 * @date 2016年9月2日
	 * @param appId
	 * @param tradeNo
	 * @param paymentId
	 * @return
	 * @throws PaymentException
	 */
	PaidRespVo queryWechatPaid(String tradeNo, Long paymentId, String appId) throws PaymentException;

	/**
	 * 查询支付宝是否支付成功
	 * @throws PaymentException
	 */
	PaidRespVo queryAlipayPaid(String tradeNo, Long paymentId) throws PaymentException;
	

	/**
	 * 获取支付宝支付签名
	 * @return
	 * @throws PaymentException
	 */
	AlipaySignResponseVo getAlipaySign(Long orderId) throws PaymentException;

	/**
	 * 获取支付宝支付签名
	 * @author yanweijin
	 * @date 2016年8月29日
	 * @param order
	 * @return
	 * @throws PaymentException
	 */
	AlipaySignResponseVo getAlipaySign(Order order) throws PaymentException;
	
	/**
	 * 获取WAP支付宝支付签名
	 * @author zhangning
	 * @date 2016年10月10日
	 * @param orderId
	 * @return
	 * @throws PaymentException
	 */
	AlipaySignResponseVo getWapAlipaySign(Long orderId) throws PaymentException;

	/**
	 * 货到付款支付动作
	 * 只修改订单状态
	 * @author yanweijin
	 * @date 2016年8月30日
	 * @param orderId
	 * @return
	 */
	PaymentResponseVo noNeedPay(Long orderId);

	/**
	 * 货到付款支付动作
	 * 只修改订单状态
	 * @author yanweijin
	 * @date 2016年8月30日
	 * @param order
	 * @return
	 */
	PaymentResponseVo noNeedPay(Order order);
	
	/**
	 * @author jun.liu(by xiaoyu)
	 * @date 2016年9月2日
	 * @param paymentId
	 */
	void confirmPaid(Long paymentId);
	
	/**
	 * 阿里回调
	 * @author jun.liu(by xiaoyu)
	 * @date 2016年10月28日
	 * @param map
	 */
	void aliNotify(Map<String, String> map);
	
	void wechatTradeNotify(OrderNotifyResponse notifyRes);

	PaymentQueryResultResponseVo query(IdReqVo req) throws PaymentException;
	
	void confirmPaid(Long orderId, OrderPayment payment);

	void savePaymentTradeNo(Long paymentId, String tradeNo);

}