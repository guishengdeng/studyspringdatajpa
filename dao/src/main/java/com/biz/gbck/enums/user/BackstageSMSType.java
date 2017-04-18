package com.biz.gbck.enums.user;

import com.biz.core.enums.EnumerableValue;
import com.biz.core.enums.converter.BaseEnumValueConverter;

/**
 * 类说明：短信模板枚举
 *
 * @author xiaoyasong
 * @version 创建时间：2016年12月26日 下午5:53:54
 * @E-mail:yasong.xiao@biz-united.com.cn
 */
public enum BackstageSMSType implements EnumerableValue {

    REGISTER(0, "用户注册"),
    FORGOT_PASSWORD(1, "忘记密码"),
    REGISTER_SUCCESS(2, "注册成功"),
    RECOMMEND(3, "推荐"),
    CHANGE_MOBILE(4, "更换手机号"),
    VERIFY_IDENTITY(5, "验证身份"),
    VENDOR_AUDIT_PASS(6, "店铺审核通过"),
    VENDOR_AUDIT_NOT_PASS(7, "店铺审核未通过");

    public static class Converter extends BaseEnumValueConverter<BackstageSMSType> {
    }

    private int value;
    private String desc;

    BackstageSMSType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static BackstageSMSType getBackstageSMSTypeByValue(int codeVal) {
        for (BackstageSMSType backstageSMSType : BackstageSMSType.values()) {
            if (backstageSMSType.getValue() == codeVal) {
                return backstageSMSType;
            }
        }
        return null;
    }
}
