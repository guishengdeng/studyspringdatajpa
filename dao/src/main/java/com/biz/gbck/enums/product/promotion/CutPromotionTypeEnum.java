package com.biz.gbck.enums.product.promotion;

import com.biz.core.enums.EnumerableNameAndValueAndDescription;

/**
 * 满减促销类型
 *
 * Created by david-liu on 2017/05/22 09:28.
 */
public enum CutPromotionTypeEnum implements EnumerableNameAndValueAndDescription {
    REACH_CUT(0, "倍增满减", "倍增满减"),
    STAIR_CUT(1, "阶梯满减", "阶梯满减");

    CutPromotionTypeEnum(int value, String description, String name) {
        this.value = value;
        this.description = description;
        this.name = name;
    }

    private int value;

    private String description;

    private String name;

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
}
