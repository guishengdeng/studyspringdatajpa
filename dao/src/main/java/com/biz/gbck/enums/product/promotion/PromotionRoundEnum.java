package com.biz.gbck.enums.product.promotion;

import com.biz.core.enums.EnumerableNameAndValueAndDescription;
import com.biz.core.enums.converter.BaseEnumNameAndValueAndDescriptionConverter;

/**
 * 商品促销循环方式
 * Created by david-liu on 2017/04/21 14:14.
 */
public enum PromotionRoundEnum implements EnumerableNameAndValueAndDescription {
    NONE(0, "不循环", "不循环"),
    DATE(1, "日期", "按日期循环"),
    WEEK_DAY(2, "星期", "按星期几循环");

    PromotionRoundEnum(int value, String name, String description) {
        this.value = value;
        this.name = name;
        this.description = description;
    }

    public static class Converter extends BaseEnumNameAndValueAndDescriptionConverter<PromotionRoundEnum> {

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
