package com.biz.gbck.vo.payment.resp;

import com.biz.gbck.dao.mysql.po.order.OrderPayment;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

	//商户名称
	private String businessName = "隔壁仓库网上订单";

	//支付金额
	private Integer payAmount;

	//支付方式
	private Integer paymentType;

	//支付方式名称
	private String paymentTypeName;

	//付款状态
	private Integer payStatus;

	//付款状态名称
	private String payStatusName;

	//支付时间
	private Long paidTime;

	//是否支付成功
	private boolean paid = true;

	public PaymentQueryResultRespVo() {
	}

	public PaymentQueryResultRespVo(OrderPayment payment) {
		this();
		this.orderId = payment.getOrder().getId();
		this.orderCode = payment.getOrder().getOrderCode();
		this.payAmount = payment.getPayAmount();
		this.paymentType = payment.getPaymentType().getValue();
		this.paymentTypeName = payment.getPaymentType().getDesc();
		this.payStatus = payment.getPayStatus().getValue();
		this.payStatusName = payment.getPayStatus().getDesc();
		this.paidTime = payment.getSuccessTimestamp() == null ? null :payment.getSuccessTimestamp().getTime();
	}


	//查询结果文本
	@JsonIgnore
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

	public String getPaymentTypeName() {
		return paymentTypeName;
	}

	public void setPaymentTypeName(String paymentTypeName) {
		this.paymentTypeName = paymentTypeName;
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

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public String getPayStatusName() {
		return payStatusName;
	}

	public void setPayStatusName(String payStatusName) {
		this.payStatusName = payStatusName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
