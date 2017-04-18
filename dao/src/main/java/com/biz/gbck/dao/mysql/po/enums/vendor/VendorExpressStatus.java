package com.biz.gbck.dao.mysql.po.enums.vendor;

import com.biz.core.enums.EnumerableValue;
import com.biz.core.enums.converter.BaseEnumValueConverter;

public enum VendorExpressStatus implements EnumerableValue {

    VALID(0),//有效状态
    DEL(1);//删除状态

    public static class Converter extends BaseEnumValueConverter<VendorExpressStatus> {

    }

    private VendorExpressStatus(Integer value) {
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
