package com.biz.gbck.vo.sms;

import com.biz.gbck.common.com.SMSType;

import javax.validation.constraints.NotNull;

/**
 * Created by defei on 3/20/16.
 */
public class SMSSentReqVo {

    @NotNull(message = "手机号不能为空") private String mobile;

    @NotNull(message = "行为不能为空") private SMSType action;

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

    public String getClientIP() {
        return clientIP;
    }

    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }
}
