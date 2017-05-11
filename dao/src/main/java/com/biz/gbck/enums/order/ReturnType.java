package com.biz.gbck.enums.order;


import com.biz.core.enums.EnumerableValue;
import com.biz.core.enums.converter.BaseEnumValueConverter;

/**
 * 售后类型
 *
 * @author lei
 * @date 2017年04月20日
 * @reviewer
 * @see
 */
public enum ReturnType implements EnumerableValue {

    RETURN(1, "退货"),
    EXCHANGE(10, "换货");

    public final int value;

    public final String desc;

    public static class Converter extends BaseEnumValueConverter<ReturnType> {
    }

    ReturnType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
