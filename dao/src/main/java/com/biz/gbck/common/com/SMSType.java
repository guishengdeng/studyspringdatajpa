package com.biz.gbck.common.com;

/**
 * Created by defei on 3/16/16.
 */
public enum SMSType {

    REGISTER("用户注册"),

    FORGOT_PASSWORD("忘记密码"),

    CHANGE_MOBILE("更换手机"),

    CHANGE_PAYMENT_PASSWORD("更改支付密码"),

    FORGOT_PAYMENT_PASSWORD("忘记支付密码");

    private String description;

    SMSType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
