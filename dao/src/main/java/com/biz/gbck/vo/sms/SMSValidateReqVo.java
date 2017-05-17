package com.biz.gbck.vo.sms;


import com.biz.gbck.common.com.SMSType;

/**
 * Created by defei on 3/20/16.
 */
public class SMSValidateReqVo {

    private String mobile;

    private SMSType action;

    private String smsCode;

    private String clientIP;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public SMSType getAction() {
        return action;
    }

    public void setAction(SMSType action) {
        this.action = action;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getClientIP() {
        return clientIP;
    }

    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }
}
