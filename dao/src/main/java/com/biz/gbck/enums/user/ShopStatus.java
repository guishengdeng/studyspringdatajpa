package com.biz.gbck.enums.user;

/**
 * 店铺状态
 * Created by defei on 3/16/16.
 */
public enum ShopStatus {

    DISABLED(0, "禁用"),

    NORMAL(9, "正常");

    private final Integer value;

    private final String description;

    ShopStatus(Integer value, String description) {
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
