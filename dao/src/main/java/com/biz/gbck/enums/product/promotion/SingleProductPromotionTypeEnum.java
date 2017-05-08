package com.biz.gbck.enums.product.promotion;

import com.biz.core.enums.EnumerableNameAndValueAndDescription;

/**
 * 单品促销类型枚举
 *
 * Created by david-liu on 2017/04/25 14:41.
 */
public enum SingleProductPromotionTypeEnum implements EnumerableNameAndValueAndDescription {
    BATCH_SPECIAL_OFFER(0, "批量特价", "批量特价"),
    MULTIPLE_QUANTITY(1, "倍数特价", "倍数特价"),
    QUANTITY_SPECIAL_OFFER(2, "数量特价", "数量特价"),
    SINGLE_PURCHASE_GIFT(3, "单品买赠", "单品买赠");

    SingleProductPromotionTypeEnum(int value, String name, String description) {
        this.value = value;
        this.name = name;
        this.description = description;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getValue() {
        return this.value;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    private int value;

    private String name;

    private String description;
}
