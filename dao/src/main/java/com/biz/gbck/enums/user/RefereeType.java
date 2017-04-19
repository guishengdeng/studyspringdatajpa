package com.biz.gbck.enums.user;

import com.biz.core.enums.EnumerableValue;
import com.biz.core.enums.converter.BaseEnumValueConverter;

/**
 * 类说明：推荐人类型枚举
 *
 * @author xiaoyasong
 * @version 创建时间：2016年12月21日 下午3:53:35
 * @E-mail:yasong.xiao@biz-united.com.cn
 */
public enum RefereeType implements EnumerableValue {

    MEMBER(1, "会员"),
    STORE(2, "门店");

    private int value;
    private String desc;
    private RefereeType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static RefereeType getRefereeTypeByValue(int codeVal) {
        for (RefereeType refereeType : RefereeType.values()) {
            if (refereeType.getValue() == codeVal) {
                return refereeType;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static class Converter extends BaseEnumValueConverter<RefereeType> {
    }
}
