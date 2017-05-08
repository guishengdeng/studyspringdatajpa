package com.biz.gbck.vo.payment.resp;

import java.io.Serializable;

/**
 * 支付结果查询
 * @author yanweijin
 * @since 2016年9月2日
 * @usage 
 * @reviewer
 */
public class PaymentQueryResultRespVo implements Serializable {

	private static final long serialVersionUID = -3750929034079312384L;

	//订单id
	private Long orderId;
	
	private String orderCode;

	//支付金额
	private Integer payAmount;

	//支付方式
	private Integer paymentType;

	//支付时间
	private Long paidTime;

	//是否支付成功
	private boolean paid = true;

	//查询结果文本
	private String message;

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

	public Integer getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(Integer payAmount) {
		this.payAmount = payAmount;
	}

	public Integer getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}

	public Long getPaidTime() {
		return paidTime;
	}

	public void setPaidTime(Long paidTime) {
		this.paidTime = paidTime;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
