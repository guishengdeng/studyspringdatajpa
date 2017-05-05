package com.biz.pay.wechat.lang;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author bruce.qin
 * @since 2016年10月8日
 * @usage 
 * @reviewer
 */
public class WeChatJSAPITicket implements Serializable{

	private static final long serialVersionUID = 1304539029159684596L;

	private String jsapiTicket;

	private Long expiresIn;

	private Date expireAt;

	public String getJsapiTicket() {
		return jsapiTicket;
	}

	public void setJsapiTicket(String jsapiTicket) {
		this.jsapiTicket = jsapiTicket;
	}

	public Long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Long expiresIn) {
		this.expiresIn = expiresIn;
	}

	public Date getExpireAt() {
		return expireAt;
	}

	public void setExpireAt(Date expireAt) {
		this.expireAt = expireAt;
	}

	public WeChatJSAPITicket(String jsapiTicket, Long expiresIn) {
		super();
		this.jsapiTicket = jsapiTicket;
		this.expiresIn = expiresIn;
		this.expireAt = new Date(System.currentTimeMillis()+expiresIn*900);
	}

}
