package com.biz.gbck.dao.mysql.po.stock;

import com.biz.core.enums.EnumerableNameAndValueAndDescription;
import com.biz.core.enums.converter.BaseEnumNameAndValueAndDescriptionConverter;
import com.biz.gbck.dao.mysql.po.product.meta.Category;

/**
 * Created by lzz on 5/22/17.
 */
public enum StockCategoryEnum implements EnumerableNameAndValueAndDescription {

    SPIRIT("白酒", "白酒", 2),
    BEER("啤酒", "啤酒", 4),
    RED_WINE("红酒", "红酒", 6),
    FOREIGN_WINE("洋酒", "洋酒", 8),
    ELSE_WINE("其他", "其他品种的酒", 10);

    StockCategoryEnum(String name, String description, Integer value) {

        this.name = name;
        this.description = description;
        this.value = value;
    }

    private Category category;
    private String name;

    private String description;

    private Integer value;

    public static class Converter extends BaseEnumNameAndValueAndDescriptionConverter<StockCategoryEnum> {

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
