package com.biz.gbck.common.model.order;

/**
 * Created by defei on 4/18/16.
 */
public enum PaymentType {

    PAY_ON_DELIVERY(1, "货到付款"),

    PAY_ONLINE(2, "在线支付"),

    ALIPAY(21, "支付宝"),

    WECHAT(22, "微信"),

    MOUTH(25, "嘴上功夫"),

    XIMU(35, "徙木");


    private final Integer value;

    private final String description;

    PaymentType(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public Integer getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

}
