package com.biz.gbck.enums.vendor;

import com.biz.core.enums.EnumerableValue;
import com.biz.core.enums.converter.BaseEnumValueConverter;

public enum AuditStatus implements EnumerableValue {
    WAITING_AUDIT(0),//待审核
    PASS(1),//审核通过
    REJECT(2),//审核不通过
    FIRST_PERFECT(3);//第一页注册完成

    private Integer value;


    private AuditStatus(Integer value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public static class Converter extends BaseEnumValueConverter<AuditStatus> {

    }
}
