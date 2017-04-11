package com.biz.core.enums;

/**
 * 枚举int的上层接口,只有枚举才应该继承本接口
 * 配合BaseEnumValueConverter使用
 */
public interface EnumerableValue {

    int getValue();

}
