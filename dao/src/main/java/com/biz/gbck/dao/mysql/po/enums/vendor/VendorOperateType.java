package com.biz.gbck.dao.mysql.po.enums.vendor;

import com.biz.core.enums.EnumerableValue;

public enum VendorOperateType implements EnumerableValue {

    OPERATE(0),//代运营
    SELF(1);//自营

    private Integer value;

    VendorOperateType(Integer value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return this.value;
    }


}
