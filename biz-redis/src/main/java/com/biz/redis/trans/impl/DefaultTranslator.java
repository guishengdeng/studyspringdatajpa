package com.biz.redis.trans.impl;

import com.biz.core.property.ExtPropertyDescriptor;
import com.biz.redis.annotation.RedisWriteIgnore;
import com.biz.redis.bean.BaseRedisObject;
import com.biz.redis.trans.*;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

/**
 * @author yanweijin
 * @date 2016/12/22
 */
public class DefaultTranslator implements Translator {

    private DataTransformer dataTransformer;

    private ConverterRegistry converterRegistry;

    private BeanRegistry beanRegistry;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public <RO> RO toObject(Class<RO> clazz, Map<byte[], byte[]> redisData) {
        BeanRegistry beanRegistry = getBeanRegistry();
        RO ro = beanRegistry.newBeanInstance(clazz);
        if (MapUtils.isEmpty(redisData)) {
            return ro;
        }
        fillObject(ro, redisData);

        return ro;
    }

    @Override
    public void fillObject(Object ro, Map<byte[], byte[]> redisData) {
        Map<String, DataItem[]> map = getDataTransformer().redisData2ItemsMap(redisData);
        List<ExtPropertyDescriptor> extPropertyDescriptors = beanRegistry.findProperties(ro.getClass());
        for (ExtPropertyDescriptor epd : extPropertyDescriptors) {
            String name = epd.getName();
            DataItem[] values = map.get(name);
            Class propertyType = epd.getPropertyType();
            Object value = getConverterRegistry().findConverter(propertyType).toValue(propertyType, name, values);
            setValue(ro, epd, value);
        }
    }

    @Override
    public Map<byte[], byte[]> toRedisData(BaseRedisObject object) {

        if (object == null) return Maps.newHashMapWithExpectedSize(1);

        List<DataItem> data = resolveBean(object);
        return this.getDataTransformer().items2RedisData(data.toArray(new DataItem[data.size()]));
    }

    private List<DataItem> resolveBean(Object object) {
        List<ExtPropertyDescriptor> extPropertyDescriptors = this.getBeanRegistry().findProperties(object.getClass());
        List<DataItem> data = Lists.newArrayList();
        for (ExtPropertyDescriptor epd : extPropertyDescriptors) {
            Class<? extends Object> propertyType = epd.getPropertyType();
            Object val = getValue(object, epd);
            //约定值:void.class,特殊处理
            if (val == void.class) continue;
            //			if(val==null)continue;
            String name = epd.getName();
            ValueConverter<Object> converter = this.getConverterRegistry().findConverter(propertyType);
            DataItem[] items = converter.toRedisData(name, val);
            data.addAll(Arrays.asList(items));
        }
        return data;
    }

    @Override
    public DataTransformer getDataTransformer() {
        return dataTransformer;
    }

    @Override
    public ConverterRegistry getConverterRegistry() {
        return converterRegistry;
    }

    @Override
    public BeanRegistry getBeanRegistry() {
        return beanRegistry;
    }

    public void setDataTransformer(DataTransformer dataTransformer) {
        this.dataTransformer = dataTransformer;
    }

    public void setConverterRegistry(ConverterRegistry converterRegistry) {
        this.converterRegistry = converterRegistry;
    }

    public void setBeanRegistry(BeanRegistry beanRegistry) {
        this.beanRegistry = beanRegistry;
    }

    private void setValue(Object pojo, ExtPropertyDescriptor epd, Object value) {
        if (value == null) return;
        Method writeMethod = epd.getWriteMethod();
        if (writeMethod == null) {
            if (logger.isDebugEnabled()) {
                logger.warn("ro[type={}] prop[name={}] not found setter", pojo.getClass(), epd.getName());
            }
            return;
        }
        ReflectionUtils.invokeMethod(writeMethod, pojo, value);
    }

    /**
     * 约定:当返回 void.class 时 表示这个值不需要写入 redis
     */
    private Object getValue(Object pojo, ExtPropertyDescriptor epd) {
        Method readMethod = epd.getReadMethod();
        if (readMethod == null) {
            if (logger.isDebugEnabled()) {
                logger.warn("ro[type={}] prop[name={}] not found getter", pojo.getClass(), epd.getName());
            }
            return null;
        }
        RedisWriteIgnore ignore = epd.findAnnotation(RedisWriteIgnore.class);
        if (ignore == null) {
            return ReflectionUtils.invokeMethod(readMethod, pojo);
        }

        if (ignore.type() == RedisWriteIgnore.IgnoreType.ALL) {
            return void.class;//特殊的返回值
        }
        Object val = ReflectionUtils.invokeMethod(readMethod, pojo);
        if (ignore.type() == RedisWriteIgnore.IgnoreType.NULL && val == null) {
            return void.class;//特殊的返回值
        }
        if (ignore.type() == RedisWriteIgnore.IgnoreType.NULL_OR_EMPTY_STRING && val instanceof String && StringUtils.isBlank((String) val)) {
            return void.class;//特殊的返回值
        }
        return val;
    }


}
