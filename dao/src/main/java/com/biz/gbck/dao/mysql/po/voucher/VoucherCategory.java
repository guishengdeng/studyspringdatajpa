package com.biz.gbck.dao.mysql.po.voucher;

public enum VoucherCategory {
    NONE(0);

    int value;

    VoucherCategory(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
