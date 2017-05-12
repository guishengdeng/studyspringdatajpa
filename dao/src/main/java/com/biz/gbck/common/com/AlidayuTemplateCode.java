package com.biz.gbck.common.com;

public enum AlidayuTemplateCode {
    /**
     * 短信验证码短信模板号
     */
    SMS_CODE("SMS_7810200"),

    /**
     * 更换手机登录短信模板号
     */
    CHANGE_MOBILE_LOGIN("SMS_8450115"),

    /**
     * 详情审核不通过短信模板号
     */
    AUDIT_DETAIL_FAILED("SMS_8956298"),

    /**
     * 营业执照不清晰短信模板号
     */
    AUDIT_LICENCE_PHOTO_FAILED("SMS_8956299"),

    /**
     * 门头照片不符合审核要求短信模板号
     */
    AUDIT_PHOTO_FAILED("SMS_8891466"),

    /**
     * 营业执照编号已被注册短信模板号
     */
    AUDIT_LICENCE_NUMBER_FAILED("SMS_8891467"),

    /**
     * 企业类型不符短信模板号
     */
    AUDIT_COMPANY_TYPE_FAILED("SMS_8956315"),

    /**
     * 营业执照经营范围与实际范围不符合短信模板号
     */
    AUDIT_COMPANY_MANAGE_FAILED("SMS_8931330"),

    /**
     * 审核通过短信模板号
     */
    AUDIT_SUCCESS("SMS_8956316"),

    /**
     * 同步短信到ocs成功短信通知
     */
    SYNC_ORDER_TO_OCS_SUCCESS("SMS_12916685"),

    /**
     * 同步短信到oms成功短信通知
     */
    SYNC_ORDER_TO_OMS_SUCCESS("SMS_12916685"),

    /**
     * 嘴上功夫券抵货款申请成功
     */
    ZSGF_LOAN_APPLY_SUCCESS("SMS_11520508"),

    /**
     * 嘴上功夫券抵货款申请失败
     */
    ZSGF_LOAN_APPLY_FAILED("SMS_11490445"),

    /**
     * 徙木金融酒先拿走申请成功
     */
    XIMU_LOAN_APPLY_SUCCESS("SMS_11500369"),

    /**
     * 徙木金融酒先拿走申请失败
     */
    XIMU_LOAN_APPLY_FAILED("SMS_11540440"),

    /**
     * 徙木金融酒先拿走资料认证失败
     */
    XIMU_LOAN_APPLY_PENDING("SMS_11685023"),

    /**
     * 优惠券即将到期
     */
    VOUCHER_TO_BE_EXPIRED("SMS_12685229"),

    /**
     * 徙木1919后台审核不通过短信模板号
     */
    XIMU_APPLY_AUDIT_FAILED("SMS_13035355");


    AlidayuTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    private String templateCode;

    public String getTemplateCode() {
        return this.templateCode;
    }

}
