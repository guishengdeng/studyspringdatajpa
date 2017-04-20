package com.biz.gbck.enums.order;

import com.biz.core.enums.EnumerableValue;
import com.biz.core.enums.converter.BaseEnumValueConverter;

/**
 * 支付类型
 *
 * @author lei
 * @date 2017年04月20日
 * @reviewer
 * @see
 */
public enum PaymentType implements EnumerableValue {

    PAY_ON_DELIVERY(1, "货到付款"),

    ALIPAY(21, "支付宝"),

    WECHAT(22, "微信");

    public static class Converter extends BaseEnumValueConverter<PaymentType> {}

    private int value;

    private String desc;

    @Override
    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    PaymentType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }


}
