package com.biz.gbck.enums.vendor;


import com.biz.core.enums.EnumerableValue;
import com.biz.core.enums.converter.BaseEnumValueConverter;

public enum VendorOperationType implements EnumerableValue {
    SELF(0),//商家自运营
    AGENCY(1);//平台代运营

    private Integer value;

    VendorOperationType(Integer value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public static class Converter extends BaseEnumValueConverter<VendorOperationType> {

    }

}
