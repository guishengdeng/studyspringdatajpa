package com.biz.gbck.vo.payment.resp;

import com.biz.pay.wechat.lang.SignIgnore;
import com.biz.pay.wechat.lang.SignProperty;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WechatPayResp extends PaymentRespVo {

	@SignIgnore
	private static final long serialVersionUID = 7007522300900273155L;

	private String appid;

	private String partnerid;

	private String prepayid;

	@JsonProperty("package")
	@SignProperty("package")
	private String wechatPackage = "Sign=WXPay";

	private String noncestr;

	private String timestamp;

	@SignIgnore
	private String sign;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getPartnerid() {
		return partnerid;
	}

	public void setPartnerid(String partnerid) {
		this.partnerid = partnerid;
	}

	public String getPrepayid() {
		return prepayid;
	}

	public void setPrepayid(String prepayid) {
		this.prepayid = prepayid;
	}

	public String getWechatPackage() {
		return wechatPackage;
	}

	public void setWechatPackage(String wechatPackage) {
		this.wechatPackage = wechatPackage;
	}

	public String getNoncestr() {
		return noncestr;
	}

	public void setNoncestr(String noncestr) {
		this.noncestr = noncestr;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}


}
