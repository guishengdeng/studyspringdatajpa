package com.biz.gbck.dao.mysql.po.enums.vendor;


import com.biz.core.enums.EnumerableValue;
import com.biz.core.enums.converter.BaseEnumValueConverter;

public enum VendorStatus implements EnumerableValue {
    OPEN(0),//开业
    CLOSED(1),//关店
    FROZEN(2),//冻结
    NOT_PERFECT(3);//未完善

    public static class Converter extends BaseEnumValueConverter<VendorStatus> {

    }


    VendorStatus(Integer value) {
        this.value = value;
    }

    private Integer value;

    public int getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}