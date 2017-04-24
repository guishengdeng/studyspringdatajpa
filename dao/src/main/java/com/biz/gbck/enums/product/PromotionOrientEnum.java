package com.biz.gbck.enums.product;

import com.biz.core.enums.EnumerableNameAndValueAndDescription;
import com.biz.core.enums.converter.BaseEnumNameAndValueAndDescriptionConverter;

/**
 * 促销面向设定类型枚举(创建促销是面向客户还是面向价格组)
 *
 * Created by david-liu on 2017/04/21 15:34.
 */
public enum PromotionOrientEnum implements EnumerableNameAndValueAndDescription {
    COMPANY(0, "针对客户", "设定促销包含哪些客户和不包含哪些客户群体"),
    PRICE_GROUP(1, "准对客户组", "设定促销包含哪些客户组和不包含哪些客户组");

    PromotionOrientEnum(int value, String name, String description) {
        this.value = value;
        this.name = name;
        this.description = description;
    }

    public static class Converter extends BaseEnumNameAndValueAndDescriptionConverter {

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

    private String description;

    private String name;
}
