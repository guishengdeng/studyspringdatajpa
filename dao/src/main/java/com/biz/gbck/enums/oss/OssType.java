package com.biz.gbck.enums.oss;

import com.biz.core.enums.EnumerableValue;
import com.biz.core.enums.converter.BaseEnumValueConverter;

/**
 * 中台oss全量主数据类型
 */
public enum OssType implements EnumerableValue {
    STOCK(10, "stock", "库存"),
    WAREHOUSE_STOCK(15, "warehouseStock", "省仓库存"),
    PROMOTION(20, "promotion", "门店促销"),
    PRICE(25, "price", "价格");
    private int value;
    private String name;
    private String description;

    OssType(int value, String name, String description) {
        this.value = value;
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static class Converter extends BaseEnumValueConverter<OssType> {
    }
}
