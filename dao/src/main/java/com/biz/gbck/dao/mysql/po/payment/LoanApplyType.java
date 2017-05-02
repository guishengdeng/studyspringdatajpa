package com.biz.gbck.dao.mysql.po.payment;

public enum LoanApplyType {
    ZSGF("嘴上功夫"),

    XIMU("徙木金融");

    private String descroption;

    LoanApplyType(String descroption) {
        this.descroption = descroption;
    }

    public String getDescroption() {
        return descroption;
    }
}
