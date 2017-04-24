package com.biz.gbck.enums.vendor;

import com.biz.core.enums.EnumerableValue;
import com.biz.core.enums.converter.BaseEnumValueConverter;

public enum FreightStrategyStatus implements EnumerableValue {

    VALID(0),//有效状态
    DEL(1);//删除状态

    private Integer value;

    private FreightStrategyStatus(Integer value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public static class Converter extends BaseEnumValueConverter<FreightStrategyStatus> {

    }

}
