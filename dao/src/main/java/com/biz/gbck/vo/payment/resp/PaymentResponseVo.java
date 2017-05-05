package com.biz.gbck.vo.payment.resp;


import com.biz.pay.wechat.lang.SignIgnore;

import java.io.Serializable;

/**
 * 支付结果resp
 * @author yanweijin
 * @since 2016年8月30日
 * @usage 
 * @reviewer
 */
public class PaymentResponseVo implements Serializable {

	private static final long serialVersionUID = -4855253604707200872L;
	
	@SignIgnore
	private Long orderId;
	
	@SignIgnore
	private String orderCode;

	public PaymentResponseVo(Long orderId, String orderCode) {
		this.orderId = orderId;
		this.orderCode = orderCode;
	}
	public PaymentResponseVo() {
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
