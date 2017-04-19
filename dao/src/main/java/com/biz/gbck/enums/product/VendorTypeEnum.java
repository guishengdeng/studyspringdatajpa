package com.biz.gbck.enums.product;


import com.biz.core.enums.EnumerableValue;

public enum VendorTypeEnum implements EnumerableValue {
    TYPE_A(0),//A类商家
    TYPE_B(1);//B类商家

    private Integer value;

    VendorTypeEnum(Integer value) {
        this.value = value;
    }

    public static VendorTypeEnum valueOf(int val) {
        switch (val) {
            case 0:
                return TYPE_A;
            case 1:
                return TYPE_B;
            default:
                return null;
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}
