package com.biz.gbck.vo.payment.resp;

import com.biz.pay.wechat.lang.SignIgnore;
import com.biz.pay.wechat.lang.SignProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.codelogger.utils.StringUtils;

/**
 * 公众号支付版本响应
 * @author bruce.qin
 * @since 2016年9月30日
 * @usage 
 * @reviewer
 */
public class MPUnifiedOrderResponseVo extends PaymentResponseVo {

	@SignIgnore
	private static final long serialVersionUID = 7007522300900273155L;

	@JsonProperty("appId")
	@SignProperty("appId")
	private String appid;

	@JsonProperty("package")
	@SignProperty("package")
	private String wechatPackage;

	@JsonIgnore
	@SignIgnore
	private String prepayid;

	@JsonProperty("nonceStr")
	@SignProperty("nonceStr")
	private String noncestr;

	private String signType;

	@JsonProperty("timeStamp")
	@SignProperty("timeStamp")
	private String timestamp;

	@JsonProperty("paySign")
	@SignIgnore
	private String sign;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getPrepayid() {
		return prepayid;
	}

	public void setPrepayid(String prepayid) {
		this.prepayid = prepayid;
		this.wechatPackage = "prepay_id="+prepayid; 
	}

	public String getWechatPackage() {
		return wechatPackage;
	}

//	public void setWechatPackage(String wechatPackage) {
//		this.wechatPackage = wechatPackage;
//	}

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

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public MPUnifiedOrderResponseVo() {
		this(null,null);
	}
	
	public MPUnifiedOrderResponseVo(Long orderId, String orderCode) {
		super(orderId, orderCode);
		this.signType = "MD5";
		this.timestamp = String.valueOf(System.currentTimeMillis());
		this.noncestr =StringUtils.getRandomPasswordString(16);
	}


}
