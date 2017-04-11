package com.biz.support.web.assist;

import com.biz.core.enums.EnumerableValue;
import com.biz.core.enums.converter.BaseEnumValueConverter;

/**
 * 调用接口的客户端类型
 *
 * @author yanweijin
 * @usage
 * @reviewer
 * @since 2016年8月2日
 */
public enum ClientType implements EnumerableValue {
    //ios app
    ios(0, "ios app"),
    //安卓app
    android(1, "安卓app"),
    //微商城
    wechat(2, "微商城"),
    //浏览器
    web(3, "浏览器"),
    //后台注册会员调用过
    manage(4, "后台注册会员"),
    //不知道什么鬼
    unknown(5, "未知");


    public static class Converter extends BaseEnumValueConverter<ClientType> {
    }

    private int value;
    private String desc;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    ClientType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

}
