package com.biz.gbck.enums.order;


import com.biz.core.enums.EnumerableValue;
import com.biz.core.enums.converter.BaseEnumValueConverter;

/**
 * 退货状态
 *
 * @author lei
 * @date 2017年04月20日
 * @reviewer
 * @see
 */
public enum InventoryStatus implements EnumerableValue {

    CREATED(1, "新建"),
    IN_INVENTORY_(10, "盘点中"),
    INVENTORY_LOSS(20, "盘亏"),
    INVENTORY_PROFIT(50, "退货完成");

    public final int value;

    public final String desc;

    public static class Converter extends BaseEnumValueConverter<InventoryStatus> {
    }

    InventoryStatus(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
