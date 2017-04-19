package com.biz.redis.trans;

/**
 * @author yanweijin
 * @date 2016/12/21
 */
public interface ConverterRegistry {


    /**
     * 注册一个类型的值转换器,并同时注册一个转换器匹配策略
     */
    <V> void registerConverter(ConverterMatcher matcher, ValueConverter<V> converter);

    /**
     * 注册一个类型的值转换器,使用默认的转换器匹配策略(类型直接匹配),重复注册同一个 class,后一个注册的会覆盖前一个
     */
    <V> void registerConverter(Class<V> clazz, ValueConverter<V> converter);

    /**
     * 根据类型寻找对应的 convertor,先找默认匹配策略,再找自定义匹配策略
     */
    ValueConverter<Object> findConverter(Class clazz);

    /**
     * 判断当前类型的属性是否受到转换器支持
     *
     * @param clazz 属性类型
     */
    boolean isSupportedPropertyType(Class clazz);


}
