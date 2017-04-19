package com.biz.gbck.enums.user;

import com.biz.core.enums.EnumerableValue;
import com.biz.core.enums.converter.BaseEnumValueConverter;

/**
 * 类说明：用户状态枚举
 *
 * @author xiaoyasong
 * @version 创建时间：2016年12月21日 下午4:37:52
 * @E-mail:yasong.xiao@biz-united.com.cn
 */
public enum UserState implements EnumerableValue {

    DISABLED(-99, "禁用"),
    LOCKED(-50, "锁定"),
    NORMAL(0, "正常");

    private int value;
    private String desc;
    private UserState(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static UserState getUserStateByValue(int codeVal) {
        for (UserState userState : UserState.values()) {
            if (userState.getValue() == codeVal) {
                return userState;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static class Converter extends BaseEnumValueConverter<UserState> {
    }

}
