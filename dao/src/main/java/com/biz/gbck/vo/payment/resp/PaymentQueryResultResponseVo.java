package com.biz.gbck.vo.payment.resp;

import com.biz.gbck.enums.order.PaymentType;

import java.io.Serializable;

/**
 * 支付结果查询
 * @author yanweijin
 * @since 2016年9月2日
 * @usage 
 * @reviewer
 */
public class PaymentQueryResultResponseVo implements Serializable {

	private static final long serialVersionUID = -3750929034079312384L;

	//订单id
	private Long orderId;
	
	private String orderCode;
	//支付金额
	private Integer payAmount;
	//支付方式
	private PaymentType paymentType;
	//是否开启分享有礼
	private boolean enableShare = false;
	//分享有礼url
	private String shareUrl;
	//分享有礼标签,用于回调时判断
	private String shareTag;
	//是否支付成功
	private boolean success = true;
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

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public boolean isEnableShare() {
		return enableShare;
	}

	public void setEnableShare(boolean enableShare) {
		this.enableShare = enableShare;
	}

	public String getShareUrl() {
		return shareUrl;
	}

	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}

	public String getShareTag() {
		return shareTag;
	}

	public void setShareTag(String shareTag) {
		this.shareTag = shareTag;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
