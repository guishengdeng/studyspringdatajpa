package com.biz.gbck.enums.user;

/**
 * 前端使用的枚举
 *
 * @author zhangcheng
 * @date 2017/2/16
 * @reviewer
 * @see
 */
public enum FrontendSMSType {

    REGISTER("用户注册"),
    FORGOT_PASSWORD("忘记密码"),
    RECOMMEND("推荐"),
    CHANGE_MOBILE("更换手机号"),
    VERIFY_IDENTITY("验证身份"),;

    private String description;

    FrontendSMSType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    //客户端templateType与后台templateType相对应
    public BackstageSMSType toBackstageSMSType() {
        switch (this) {
            case REGISTER:
                return BackstageSMSType.REGISTER;
            case FORGOT_PASSWORD:
                return BackstageSMSType.FORGOT_PASSWORD;
            case RECOMMEND:
                return BackstageSMSType.RECOMMEND;
            case CHANGE_MOBILE:
                return BackstageSMSType.CHANGE_MOBILE;
            case VERIFY_IDENTITY:
                return BackstageSMSType.VERIFY_IDENTITY;
        }
        return null;
    }
}
