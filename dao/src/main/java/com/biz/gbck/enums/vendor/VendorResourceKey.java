package com.biz.gbck.enums.vendor;


import com.biz.core.enums.EnumerableValue;
import com.biz.core.enums.converter.BaseEnumValueConverter;

public enum VendorResourceKey implements EnumerableValue {

    IMAGEONE(1),//广告图1
    IMAGETWO(2),//广告图2
    IMAGETHREE(3);//广告图3

    private Integer value;

    private VendorResourceKey(Integer value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public static class Converter extends BaseEnumValueConverter<VendorResourceKey> {

    }

}
