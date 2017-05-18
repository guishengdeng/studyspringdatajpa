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
public enum ReturnStatus implements EnumerableValue {

    CANCELED(0, "已取消"),
    CREATED(5, "新建"),
    AUDITED(10, "已审核"),
    RETURNED(20, "已退货"),
    RETURN_SUCCESS(50, "退货完成");

    public final int value;

    public final String desc;

    public static class Converter extends BaseEnumValueConverter<ReturnStatus> {
    }

    ReturnStatus(int value, String desc) {
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
