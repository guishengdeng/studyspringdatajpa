package com.biz.gbck.vo.payment.resp;

/**
 * 电子钱包支付响应
 * @author yanweijin
 * @since 2016年9月14日
 * @usage 
 * @reviewer
 */
public class BalancePaymentResponseVo extends PaymentResponseVo {

	private static final long serialVersionUID = 7527021306021600113L;
	
	private String paymentPage;

	public BalancePaymentResponseVo() {
		super();
	}

	public BalancePaymentResponseVo(Long orderId, String orderCode, String paymentPage) {
		super(orderId, orderCode);
		this.paymentPage = paymentPage;
	}

	public String getPaymentPage() {
		return paymentPage;
	}

	public void setPaymentPage(String paymentPage) {
		this.paymentPage = paymentPage;
	}
	
	
	
}
