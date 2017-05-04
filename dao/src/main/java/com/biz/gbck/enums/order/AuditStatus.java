package com.biz.gbck.enums.order;

import com.biz.core.enums.EnumerableValue;
import com.biz.core.enums.converter.BaseEnumValueConverter;

/**
 * 审核状态
 *
 * @author lei
 * @date 2017年04月20日
 * @reviewer
 * @see
 */
public enum AuditStatus implements EnumerableValue {
    WAITING_AUDIT(0),//待审核
    PASS(1),//审核通过
    REJECT(2); //审核不通过

    private int value;


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
