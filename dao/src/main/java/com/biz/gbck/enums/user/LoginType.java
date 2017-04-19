package com.biz.gbck.enums.user;

import com.biz.core.enums.EnumerableValue;
import com.biz.core.enums.converter.BaseEnumValueConverter;

/**
 * 类说明：会员登录类型
 *
 * @author lei
 * @date 2017年02月10日
 * @reviewer
 * @see
 */
public enum LoginType implements EnumerableValue {

    LOCAL(0, "local"),
    MOBILE(1, "mobile"),
    EMAIL(2, "email");

    private int value;
    private String name;
    LoginType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static class Converter extends BaseEnumValueConverter<LoginType> {
    }
}
