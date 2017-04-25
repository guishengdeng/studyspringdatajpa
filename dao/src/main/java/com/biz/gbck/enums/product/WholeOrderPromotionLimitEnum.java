package com.biz.gbck.enums.product;

import com.biz.core.enums.EnumerableNameAndValueAndDescription;
import com.biz.core.enums.converter.BaseEnumNameAndValueAndDescriptionConverter;

/**
 * 整单促销使用范围(针对全部商品/组合商品/分类商品/指定商品)
 *
 * Created by david-liu on 2017/04/24 09:11.
 */
public enum WholeOrderPromotionLimitEnum implements EnumerableNameAndValueAndDescription {

    ALL("全部商品", 0, "促销针对全部商品"),
    RAPID("组合商品", 1, "促销针对组合商品"),
    CATEGORY("分类商品", 2, "促销针对分类商品"),
    SPECIFIC("指定商品", 3, "促销针对指定商品");

    WholeOrderPromotionLimitEnum(String name, int value, String description) {
        this.name = name;
        this.value = value;
        this.description = description;
    }

    public static class Converter extends BaseEnumNameAndValueAndDescriptionConverter {

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
