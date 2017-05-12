package com.biz.gbck.vo.payment.resp;

public class AlipaySignRespVo extends PaymentRespVo {

	private static final long serialVersionUID = -890153870558874997L;

	public String params;


	public AlipaySignRespVo(String params, Long orderId, String orderCode) {
		super(orderId,orderCode);
		this.params = params;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

}
