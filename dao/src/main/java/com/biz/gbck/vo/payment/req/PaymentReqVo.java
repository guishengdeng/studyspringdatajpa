package com.biz.gbck.vo.payment.req;

import com.biz.gbck.common.vo.CommonReqVoBindUserId;
import com.biz.gbck.vo.user.BaseRequestVo;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class PaymentReqVo extends CommonReqVoBindUserId implements IPaymentReqVo {
	
	private static final long serialVersionUID = 2407334255953666845L;
	
	@NotNull(message="订单编号不能为空")
	@JsonProperty("id")
    private Long orderId;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	
}
