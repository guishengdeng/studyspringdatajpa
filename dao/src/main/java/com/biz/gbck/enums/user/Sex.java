package com.biz.gbck.enums.user;

import com.biz.core.enums.EnumerableValue;
import com.biz.core.enums.converter.BaseEnumValueConverter;

/**
 * 类说明：性别枚举
 *
 * @author xiaoyasong
 * @version 创建时间：2016年12月19日 下午3:37:13
 * @E-mail:yasong.xiao@biz-united.com.cn
 */
public enum Sex implements EnumerableValue {

    FEMALE(0, "女性"),
    MALE(1, "男性"),
    SECRECY(2, "保密");

    public static class Converter extends BaseEnumValueConverter<Sex> {
    }

    private int value;
    private String desc;

    Sex(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static Sex getSexByValue(int codeVal) {
        for (Sex sex : Sex.values()) {
            if (sex.getValue() == codeVal) {
                return sex;
            }
        }
        return null;
    }
}
