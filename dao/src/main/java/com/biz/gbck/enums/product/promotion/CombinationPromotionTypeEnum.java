package com.biz.gbck.enums.product.promotion;

import com.biz.core.enums.EnumerableNameAndValueAndDescription;

/**
 * 组合促销类型枚举
 *
 * Created by david-liu on 2017/04/26 09:22.
 */
public enum CombinationPromotionTypeEnum implements EnumerableNameAndValueAndDescription {
    STANDARD_COMBINATION(0, "组合促销", "组合促销"),
    QUANTITY_COMBINATION(1, "数量组合", "数量组合");

    CombinationPromotionTypeEnum(Integer value, String name, String description) {
        this.value = value;
        this.name = name;
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getDescription() {
        return description;
    }

    private Integer value;

    private String name;

    private String description;
}
