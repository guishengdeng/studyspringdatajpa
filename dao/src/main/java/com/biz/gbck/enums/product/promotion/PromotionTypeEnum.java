package com.biz.gbck.enums.product.promotion;

import com.biz.core.enums.EnumerableNameAndValueAndDescription;

/**
 * 所有的商品促销类型
 *
 * Created by david-liu on 2017/04/27 15:23.
 */
public enum PromotionTypeEnum implements EnumerableNameAndValueAndDescription {
    BATCH_SPECIAL_OFFER(0, "批量特价", "批量特价"),
    MULTIPLE_QUANTITY(1, "倍数特价", "倍数特价"),
    QUANTITY_SPECIAL_OFFER(2, "数量特价", "数量特价"),
    SINGLE_PURCHASE_GIFT(3, "单品买赠", "单品买赠"),
    ACCOUNT_PER_UNIT_CUT(4, "倍增满减", "倍增满减"),
    FULL_REDEMPTION(5, "满额换购", "满额换购"),
    MULTIPLE_INCREMENT_GIFT(6, "倍增满减", "倍增满减"),
    STAIR_CUT(7, "阶梯满减", "阶梯满减"),
    STAIR_GIFT(8, "阶梯满赠", "阶梯满赠"),
    STANDARD_COMBINATION(9, "组合促销", "组合促销"),
    QUANTITY_COMBINATION(10, "数量组合", "数量组合");

    PromotionTypeEnum(int value, String name, String description) {
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
