package com.biz.gbck.enums.user;

/**
 * 商铺类型状态
 * Created by defei on 3/18/16.
 */
public enum ShopTypeStatus {

    /**
     * 已经删除
     */
    DELETED(0),

    /**
     * 正常
     */
    NORMAL(1);

    private Integer value;

    ShopTypeStatus(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
