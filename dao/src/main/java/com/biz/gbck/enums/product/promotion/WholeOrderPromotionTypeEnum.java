package com.biz.gbck.enums.product.promotion;

import com.biz.core.enums.EnumerableNameAndValueAndDescription;

/**
 * 整单促销类型枚举
 *
 * Created by david-liu on 2017/04/25 15:38.
 */
public enum WholeOrderPromotionTypeEnum implements EnumerableNameAndValueAndDescription {
    ACCOUNT_PER_UNIT_CUT(0, "倍增满减", "倍增满减"),
    FULL_REDEMPTION(1, "满额换购", "满额换购"),
    MULTIPLE_INCREMENT_GIFT(2, "倍增满减", "倍增满减"),
    STAIR_CUT(3, "阶梯满减", "阶梯满减"),
    STAIR_GIFT(4, "阶梯满赠", "阶梯满赠");

    WholeOrderPromotionTypeEnum(int value, String name, String description) {
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

    private int value;

    private String name;

    private String description;
}
