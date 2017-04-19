package com.biz.gbck.enums.user;

import com.biz.core.enums.EnumerableValue;
import com.biz.core.enums.converter.BaseEnumValueConverter;

/**
 * 学历枚举
 *
 * @author Nian.Li <br>2016年12月28日
 */
public enum Education implements EnumerableValue {

    DOCTORATE(1, "博士"),
    GRADUATE(2, "研究生"),
    UNDERGRADUATE(3, "本科"),
    SPECIALTY(4, "专科"),
    HIGH_SCHOOL(5, "高中");

    private int value;
    private String desc;
    private Education(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static Education getEducationByValue(int codeVal) {
        for (Education education : Education.values()) {
            if (education.getValue() == codeVal) {
                return education;
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

    public static class Converter extends BaseEnumValueConverter<Education> {
    }


}
