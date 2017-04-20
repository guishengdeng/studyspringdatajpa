package com.biz.gbck.enums.order;

import com.biz.core.enums.EnumerableValue;
import com.biz.core.enums.converter.BaseEnumValueConverter;

/**
 * 支付状态
 *
 * @author lei
 * @date 2017年04月20日
 * @reviewer
 * @see
 */
public enum PaymentStatus implements EnumerableValue {
    UN_PAY(1, "未支付"), CREATE_PAYMENT(40, "创建支付单"), PAYED(100, "已支付");

    public static class Converter extends BaseEnumValueConverter<PaymentStatus> {
    }


    public final int value;

    public final String desc;

    PaymentStatus(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public int getValue() {
        return value;
    }

}
