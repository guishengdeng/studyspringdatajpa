package com.biz.gbck.enums;

import com.biz.core.enums.EnumerableNameAndValueAndDescription;

/**
 * 维护行为枚举
 *
 * Created by david-liu on 2017/04/21 10:41.
 */
public enum CommonActionEnum implements EnumerableNameAndValueAndDescription {
    新增("新增", 0, "新增操作"),
    修改("修改", 0, "修改操作"),
    删除("删除", 0, "删除操作");

    CommonActionEnum(String name, int value, String description) {
        this.name = name;
        this.value = value;
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

    private String name;

    private int value;

    private String description;
}
