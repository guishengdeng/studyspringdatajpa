package com.biz.message.mo;

/**
 * 短信模板枚举
 * id为短信模板id,在网关管理页面查看
 *
 * @author yanweijin 2016年5月17日
 */
public enum SmsTemplate {

    ALIDAYU_REG("SMS_10630862", "会员注册"),
    ALIDAYU_RESET_PASSWORD("SMS_10625998", "会员找回密码"),
    ALIDAYU_CHANGE_MOBILE("SMS_10690747", "修改手机号"),
    ALIDAYU_CUSTOMER_SMSLOGIN("SMS_10692022", "用户随机验证码登录"),
    SHARE_INDEX_SENDSMS("SMS_11550410", "嘴上功夫分享登录验证码获取");

    private final String id;
    private final String desc;

    private SmsTemplate(String id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }


}
