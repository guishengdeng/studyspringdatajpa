package com.biz.gbck.enums.depot;

import com.biz.core.enums.EnumerableValue;
import com.biz.core.enums.converter.BaseEnumValueConverter;

/**
 * @author zhangcheng
 * @date 2016/12/13
 * @reviewer
 * @see
 */
public enum BusinessStatus implements EnumerableValue {

    OFF_BUSINESS(0), ON_BUSINESS(1);

    private int value;

    BusinessStatus(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    public static class Convert extends BaseEnumValueConverter<BusinessStatus> {

    }
}
