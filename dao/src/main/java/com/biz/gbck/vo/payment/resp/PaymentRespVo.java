package com.biz.gbck.vo.payment.resp;


import com.biz.pay.wechat.lang.SignIgnore;

import java.io.Serializable;

/**
 * 支付结果 返回vo
 * @author lei
 * @since 2017年5月7日
 * @usage 
 * @reviewer
 */
public class PaymentRespVo implements Serializable {

	private static final long serialVersionUID = -4855253604707200872L;
	
	@SignIgnore
	private Long orderId;
	
	@SignIgnore
	private String orderCode;

	public PaymentRespVo(Long orderId, String orderCode) {
		this.orderId = orderId;
		this.orderCode = orderCode;
	}
	public PaymentRespVo() {
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	
}