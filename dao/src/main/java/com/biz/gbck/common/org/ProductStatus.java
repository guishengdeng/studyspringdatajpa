package com.biz.gbck.common.org;

import java.util.Objects;

/**
 * 商品状态
 * Created by defei on 3/21/16.
 */
public enum ProductStatus {

    OFF_SALE(0, "下架"),

    WAIT_FOR_ADD_SALE_PRICE(5, "等待调价"),

    WAIT_FOR_SALE(9, "待上架"),

    ON_SALE(10, "上架");

    private final Integer value;

    private final String description;

    ProductStatus(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public Integer getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static ProductStatus changeStatus(Integer value) {
        if (Objects.equals(value, ON_SALE.value)) {
            return ProductStatus.OFF_SALE;
        }
        return ProductStatus.ON_SALE;
    }

    public static String getStatus(Integer value) {
        if (Objects.equals(value, OFF_SALE.value)) {
            return ProductStatus.OFF_SALE.getDescription();
        } else if (Objects.equals(value, WAIT_FOR_ADD_SALE_PRICE.value)) {
            return WAIT_FOR_ADD_SALE_PRICE.getDescription();
        } else {
            return ON_SALE.getDescription();
        }
    }
}

