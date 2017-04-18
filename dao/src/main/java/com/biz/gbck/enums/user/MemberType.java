package com.biz.gbck.enums.user;

import com.biz.core.enums.EnumerableValue;
import com.biz.core.enums.converter.BaseEnumValueConverter;

/**
 * 类说明：会员类型枚举
 *
 * @author xiaoyasong
 * @version 创建时间：2016年12月21日 下午3:43:55
 * @E-mail:yasong.xiao@biz-united.com.cn
 */
public enum MemberType implements EnumerableValue {

    PERSONAL(1, "个人"),
    COMPANY(2, "公司");

    public static class Converter extends BaseEnumValueConverter<MemberType> {
    }

    private int value;
    private String desc;

    private MemberType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static MemberType getMemberTypeByValue(int codeVal) {
        for (MemberType memberType : MemberType.values()) {
            if (memberType.getValue() == codeVal) {
                return memberType;
            }
        }
        return null;
    }


}
