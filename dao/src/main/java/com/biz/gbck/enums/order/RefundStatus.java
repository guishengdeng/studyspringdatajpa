package com.biz.gbck.enums.order;


import com.biz.core.enums.EnumerableValue;
import com.biz.core.enums.converter.BaseEnumValueConverter;

/**
 * 退货状态
 *
 * @author lei
 * @date 2017年04月20日
 * @reviewer
 * @see
 */
public enum RefundStatus implements EnumerableValue {

    PRE_AUDIT(0, "待处理"),
    REFUNDING(10, "退款中"),
    REFUND_FAIL(20, "退款失败"),
    REFUND_SUCCESS(50, "退款完成");

    public final int value;

    public final String desc;

    public static class Converter extends BaseEnumValueConverter<RefundStatus> {
    }

    RefundStatus(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
