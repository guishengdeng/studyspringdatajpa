package com.biz.vo.org;

/**
 * Created by defei on 3/16/16.
 */
public class ForgotPasswordReqVo {

    /**
     * 手机账号
     */
    private String mobile;

    /**
     * 新密码
     */
    private String password;

    /**
     * 短信验证码
     */
    private String smsCode;

    /**
     * 原始密码
     */
    private String originalPassword;
    
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

	public String getOriginalPassword() {
		return originalPassword;
	}

	public void setOriginalPassword(String originalPassword) {
		this.originalPassword = originalPassword;
	}
    
}
