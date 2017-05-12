package com.biz.gbck.common.com.util;

import com.alibaba.fastjson.JSONObject;
import com.biz.gbck.common.com.AlidayuTemplateCode;
import com.biz.gbck.common.com.SMSType;

public class SmsContentTemplate {

    public static final String ZSGF_LOAN_APPLY = "券抵货款";

    public static final String XIMU_LOAN_APPLY = "酒先拿走";

    private static final String SMS_CODE_TEMPLATE = "【隔壁仓库】您好，欢迎使用隔壁仓库。您正在使用[%s], 短信验证码是[%s]";

    private static final String CHANGE_MOBILE_TEMPLATE =
        "亲爱的用户，您的账号[%s]正在使用其他手机登录，如非本人操作，请立即修改您的登录密码。";

    private static final String AUDIT_DETAIL_FAILED =
        "尊敬的用户，您好，您在注册隔壁仓库APP会员过程中，店铺名称、店铺类型、营业执照编号、营业执照名称、收货地址均为必填项，请仔细核对必填项是否一一对应填写完整正确无误，谢谢您的谅解，详情可咨询1919酒类直供门店员工以及电话热线400-9991919（9:00—22：00）按5号键进行咨询！";

    private static final String AUDIT_LICENE_IMAGE_FAILED =
        "尊敬的用户，您好，您提交的注册资料中营业执照不清晰，未能通过隔壁仓库APP的审核，请仔细核对营业执照上的店铺名称和营业执照编号是否完整清晰，更改后可再次上传审核，给您带来的不便，敬请谅解，详情可咨询1919酒类直供门店员工以及电话热线400-9991919（9:00—22：00）按5号键进行咨询！";

    private static final String AUDIT_PHOTO_FAILED =
        "尊敬的用户，您好，您提交的注册资料中门头照片不符合我司审核要求，未能通过隔壁仓库APP的审核，更改后请上传符合审核要求的门头照片可再次审核，给您带来不便，我们深感抱歉，详情可咨询1919酒类直供门店员工以及电话热线400-9991919（9:00—22：00）按5号键进行咨询！";

    private static final String AUDIT_LICENE_NUMBER_FAILED =
        "尊敬的用户，您好，您提交的注册资料中您的营业执照编号已被注册使用，未能通过隔壁仓库APP的审核，给您带来的不便，敬请谅解，详情可咨询1919酒类直供门店员工以及电话热线400-9991919（9:00—22：00）按5号键进行咨询！";

    private static final String AUDIT_COMPANY_TYPE_FAILED =
        "尊敬的用户，您好，您提交的注册资料中您的企业类型不符合我司本次参与活动商户，未能通过隔壁仓库APP的审核，给您带来的不便，敬请谅解，详情可咨询1919酒类直供门店员工以及电话热线400-9991919（9:00—22：00）按5号键进行咨询！";

    private static final String AUDIT_COMPANY_MANAGE_FAILED =
        "尊敬的用户，您好，您提交的注册资料中，您的营业执照经营范围与实际经营范围类型不符合，未能通过隔壁仓库APP的审核，给您带来的不便，敬请谅解，详情可咨询1919酒类直供门店员工以及电话热线400-9991919（9:00—22：00）按5号键进行咨询！";

    private static final String AUDIT_SUCCESS =
        "尊敬的用户，您好，恭喜您已通过隔壁仓库APP审核并成为我们的VIP会员，下单即可享受限时优惠，详情可咨询1919酒类直供门店员工以及电话热线400-9991919（9:00—22：00）按5号键进行咨询，祝您在隔壁仓库购物愉快！";

    private static final String SYNC_ORDER_TO_OCS_SUCCESS =
        "尊敬的会员：您好！隔壁仓库系统升级中，目前只接受本省配送，如您的订单本省无货或者7日内无法完成备货，我司客服将联系您取消订单，谢谢您的支持！ 详情咨询400-999-1919转5";

    private static final String ZSGF_LOAN_APPLY_SUCCESS =
        "尊敬的用户，您好，恭喜您已通过\"券抵货款\"审核，先您可使用”券抵货款“支付方式支付";

    private static final String ZSGF_LOAN_APPLY_FAILED = "尊敬的用户，您好，您\"券抵货款\"申请未能通过，您可重新上传资料再次申请";

    private static final String XIMU_LOAN_APPLY_SUCCESS =
        "尊敬的用户，您好，恭喜您已成功申请“酒先拿走”服务，您现在可以使用“酒先拿走”支付方式支付订单。";

    private static final String XIMU_LOAN_APPLY_FAILED = "尊敬的用户，您\"酒先拿走\"申请未能通过审核，您可在30天后重新申请该服务。";

    private static final String XIMU_LOAN_APPLY_PENDING = "尊敬的用户，您申请的%s服务因%s信息填写错误，未能申请成功，请重新填写或上传该资料，以便完成申请";

    private static final String VOUCHER_TO_BE_EXPIRED = "尊敬的客户：您在隔壁仓库的红包即将过期，温馨提醒您尽快使用！更多优惠请随时登陆查看！";

    private static final String XIMU_APPLY_FAILED = "尊敬的用户，非常遗憾，您\"酒先拿走\"服务申请未通过。";

    public static final class Builder {

        private String smsCode;

        private SMSType smsType;

        private AlidayuTemplateCode alidayuTemplateCode;

        private String username;

        private JSONObject params;

        public Builder() {
        }

        public SmsContentTemplate.Builder setParams(JSONObject params) {
            this.params = params;
            return this;
        }

        public SmsContentTemplate.Builder setSmsCode(String smsCode) {
            this.smsCode = smsCode;
            return this;
        }

        public SmsContentTemplate.Builder setSmsType(SMSType smsType) {
            this.smsType = smsType;
            return this;
        }

        public SmsContentTemplate.Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public SmsContentTemplate.Builder setAlidayuTemplateCode(
            AlidayuTemplateCode alidayuTemplateCode) {
            this.alidayuTemplateCode = alidayuTemplateCode;
            return this;
        }

        public String build() {
            if (alidayuTemplateCode == AlidayuTemplateCode.SMS_CODE) {
                return String
                    .format(SmsContentTemplate.SMS_CODE_TEMPLATE, this.smsType.getDescription(),
                        this.smsCode);
            } else if (alidayuTemplateCode == AlidayuTemplateCode.AUDIT_COMPANY_MANAGE_FAILED) {
                return AUDIT_COMPANY_MANAGE_FAILED;
            } else if (alidayuTemplateCode == AlidayuTemplateCode.AUDIT_COMPANY_TYPE_FAILED) {
                return AUDIT_COMPANY_TYPE_FAILED;
            } else if (alidayuTemplateCode == AlidayuTemplateCode.AUDIT_DETAIL_FAILED) {
                return AUDIT_DETAIL_FAILED;
            } else if (alidayuTemplateCode == AlidayuTemplateCode.AUDIT_LICENCE_NUMBER_FAILED) {
                return AUDIT_LICENE_NUMBER_FAILED;
            } else if (alidayuTemplateCode == AlidayuTemplateCode.AUDIT_LICENCE_PHOTO_FAILED) {
                return AUDIT_LICENE_IMAGE_FAILED;
            } else if (alidayuTemplateCode == AlidayuTemplateCode.CHANGE_MOBILE_LOGIN) {
                return String.format(CHANGE_MOBILE_TEMPLATE, this.username);
            } else if (alidayuTemplateCode == AlidayuTemplateCode.AUDIT_PHOTO_FAILED) {
                return AUDIT_PHOTO_FAILED;
            } else if (alidayuTemplateCode == AlidayuTemplateCode.SYNC_ORDER_TO_OCS_SUCCESS) {
                return SYNC_ORDER_TO_OCS_SUCCESS;
            } else if (alidayuTemplateCode == AlidayuTemplateCode.ZSGF_LOAN_APPLY_SUCCESS) {
                return ZSGF_LOAN_APPLY_SUCCESS;
            } else if (alidayuTemplateCode == AlidayuTemplateCode.ZSGF_LOAN_APPLY_FAILED) {
                return ZSGF_LOAN_APPLY_FAILED;
            } else if (alidayuTemplateCode == AlidayuTemplateCode.AUDIT_SUCCESS){
                return AUDIT_SUCCESS;
            } else if (alidayuTemplateCode == AlidayuTemplateCode.XIMU_LOAN_APPLY_SUCCESS) {
                return XIMU_LOAN_APPLY_SUCCESS;
            } else if (alidayuTemplateCode == AlidayuTemplateCode.XIMU_LOAN_APPLY_FAILED) {
                return XIMU_LOAN_APPLY_FAILED;
            } else if (alidayuTemplateCode == AlidayuTemplateCode.XIMU_LOAN_APPLY_PENDING) {
                return String.format(XIMU_LOAN_APPLY_PENDING, params.get("service"), params.get("content"));
            } else if (alidayuTemplateCode == AlidayuTemplateCode.VOUCHER_TO_BE_EXPIRED) {
                return VOUCHER_TO_BE_EXPIRED;
            } else if (alidayuTemplateCode == AlidayuTemplateCode.XIMU_APPLY_AUDIT_FAILED) {
                return XIMU_APPLY_FAILED;
            }
            return "";
        }
    }

}
