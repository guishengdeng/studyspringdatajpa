package com.biz.gbck.enums.user;

/**
 * 店铺渠道
 * Created by defei on 3/16/16.
 */
public enum ShopChannel {

    DEPOTNEARBY(1, "隔壁仓库"),

    BAI_DU_NUO_MI(2, "百度糯米");

    private final Integer value;

    private final String description;

    ShopChannel(Integer value, String description) {
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
