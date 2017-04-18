package com.biz.gbck.enums.user;

import com.biz.core.enums.EnumerableValue;
import com.biz.core.enums.converter.BaseEnumValueConverter;

/**
 * 类说明：婚姻状况枚举
 *
 * @author xiaoyasong
 * @version 创建时间：2016年12月21日 下午3:58:05
 * @E-mail:yasong.xiao@biz-united.com.cn
 */
public enum Wedlock implements EnumerableValue {

    UNMARRIED(0, "未婚"),
    MARRIED(1, "已婚");

    public static class Converter extends BaseEnumValueConverter<Wedlock> {
    }

    private int value;
    private String desc;

    Wedlock(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static Wedlock getWedlockByValue(int codeVal) {
        for (Wedlock wedlock : Wedlock.values()) {
            if (wedlock.getValue() == codeVal) {
                return wedlock;
            }
        }
        return null;
    }
}
