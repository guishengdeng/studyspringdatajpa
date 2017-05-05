package com.biz.pay.wechat.lang;

import java.util.Date;

public class WeChatAccessToken {

    private String token;

    private Integer expiresIn;

    private Date expiresAt;

    public WeChatAccessToken() {
    }

    public WeChatAccessToken(String token, Integer expiresIn) {
        this.token = token;
        this.expiresIn = expiresIn;
        this.expiresAt = new Date(System.currentTimeMillis() + 1000 * expiresIn);
    }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}

	public Date getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(Date expiresAt) {
		this.expiresAt = expiresAt;
	}
    
}
