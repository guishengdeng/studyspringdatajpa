package com.biz.gbck.enums.user;

/**
 * 商铺类型状态
 * Created by defei on 3/18/16.
 */
public enum ShopWeight {

    /**
     * 隐藏
     */
    HIDE(0),

    /**
     * 正常
     */
    NORMARL(1),

    /**
     * 好
     */
    GOOD(10),

    /**
     * 很好
     */
    BETTER(20),

    /**
     * 非常好
     */
    BEST(30);

    private Integer value;

    ShopWeight(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
