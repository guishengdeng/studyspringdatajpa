package com.biz.gbck.enums.user;

/**
 * 特殊店铺ID
 */
public enum ShopId {

    BAI_DU_NUO_MI(100L, "百度糯米");

    private final Long id;

    private final String name;

    ShopId(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
