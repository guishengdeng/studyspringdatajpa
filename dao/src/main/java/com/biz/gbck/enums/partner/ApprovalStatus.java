package com.biz.gbck.enums.partner;

import com.biz.core.enums.EnumerableValue;
import com.biz.core.enums.converter.BaseEnumValueConverter;

/**
 * Created by haibin.tang on 2017/5/10.
 * 合伙人审核状态
 */
public enum  ApprovalStatus implements EnumerableValue {

    UNDER_REVIEW(1,"审核中"),

    PASS(2,"审核通过"),

    VETO(3,"否决");

    private int value;

    private String description;

    ApprovalStatus(int value, String description) {
        this.value = value;
        this.description = description;
    }

    @Override
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static class Converter
            extends BaseEnumValueConverter<ApprovalStatus> {
    }
}
