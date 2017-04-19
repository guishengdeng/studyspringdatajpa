package com.biz.gbck.enums.product;

import com.biz.core.enums.EnumerableValue;
import com.biz.core.enums.converter.BaseEnumValueConverter;

/**
 * Created by david-liu on 2017/04/17 12:05.
 */
public enum SaleStatusEnum implements EnumerableValue {
    ON_SALE(10, "已上架"), OFF_SALE(0, "未上架");

    private Integer value;
    private String description;

    SaleStatusEnum(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    @Override
    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static class Converter extends BaseEnumValueConverter<SaleStatusEnum> {
    }

}
