package com.biz.gbck.vo.payment.resp;


/**
 * 支付结果vo
 * @author lei
 * @since 2017年5月6日
 * @usage 
 * @reviewer
 */
public class CommonPaidRespVo implements PaidRespVo {

	/**
	 * 交易单号
	 */
	private String tradeNo;

	private Boolean paid = false;

	/**
	 * 付款账号
	 */
	private String payAccount;

	public CommonPaidRespVo(String tradeNo, Boolean paid, String payAccount) {
		this.tradeNo = tradeNo;
		this.paid = paid;
		this.payAccount = payAccount;
	}

	@Override
	public String getTradeNo() {
		return tradeNo;
	}

	@Override
	public Boolean isPaid() {
		return paid;
	}

	@Override
	public String getPayAccount() {
		return payAccount;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public Boolean getPaid() {
		return paid;
	}

	public void setPaid(Boolean paid) {
		this.paid = paid;
	}

	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}
}
