package com.biz.gbck.enums.order;

/**
 * 发票类型
 *
 * @author lei
 * @date 2017年04月20日
 * @reviewer
 * @see
 */
public enum InvoiceType {
    NO(0, "不开发票"), PERSONAL(1, "个人"), COMPANY(2, "公司");

    private int value;

    private String desc;

    public String getDesc() {
        return desc;
    }

    public int getValue() {
        return value;
    }

    private InvoiceType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

}
