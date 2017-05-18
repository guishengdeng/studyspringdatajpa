package com.biz.gbck.enums.order;


import com.biz.core.enums.EnumerableValue;
import com.biz.core.enums.converter.BaseEnumValueConverter;

/**
 * 退款状态
 *
 * @author lei
 * @date 2017年04月20日
 * @reviewer
 * @see
 */
public enum RefundStatus implements EnumerableValue {

    NO_REFUND(-1, "无退款"),
    REFUNDING(5, "处理中"),
    REFUNDED(10, "已退款");

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
