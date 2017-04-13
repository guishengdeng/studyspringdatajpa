package com.biz.redis.trans.impl.convertor;

import com.biz.core.property.ExtPropertyDescriptor;
import com.biz.redis.trans.*;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ReflectionUtils;

/**
 * @author yanweijin
 * @date 2016/12/22
 */
public class BeanConverter extends AbstractMultiValueConverter<Object> {

    public static final ConverterMatcher MATCHER = RedisObject.class::isAssignableFrom;

    private BeanRegistry beanRegistry;

    public BeanConverter(ConverterRegistry converterRegistry, BeanRegistry beanRegistry) {
        super(converterRegistry);
        this.beanRegistry = beanRegistry;
    }


    @Override
    public DataItem[] toRedisData(String key, Object val) {
        List<DataItem> items = resolveBean(key, val);
        return items.toArray(new DataItem[items.size()]);
    }

    private List<DataItem> resolveBean(String prefix, Object object) {
        //TODO 这块要需要改造
        if (beanRegistry == null) {
            return Lists.newArrayList();
        }
        List<ExtPropertyDescriptor> propertyDescriptors = beanRegistry.findProperties(object.getClass());
        boolean usePrefix = StringUtils.isNotBlank(prefix);
        List<DataItem> data = Lists.newArrayList();
        for (ExtPropertyDescriptor propertyDescriptor : propertyDescriptors) {
            Class<? extends Object> propertyType = propertyDescriptor.getPropertyType();
            ValueConverter<Object> converter = this.getConverterRegistry().findConverter(propertyType);
            Object val = this.getValue(object, propertyDescriptor);
            if (val == null) continue;
            String name = propertyDescriptor.getName();
            name = usePrefix ? prefix + Translator.SEPERATOR + name : name;
            DataItem[] items = converter.toRedisData(name, val);
            data.addAll(Arrays.asList(items));
        }
        return data;
    }

    private void setValue(Object pojo, ExtPropertyDescriptor pde, Object value) {
        if (value == null) return;
        Method writeMethod = pde.getWriteMethod();
        if (writeMethod == null) {
            if (logger.isWarnEnabled()) {
                logger.warn("ro[type={}] prop[name={}] not found setter", pojo.getClass(), pde.getName());
            }
            return;
        }
        ReflectionUtils.invokeMethod(writeMethod, pojo, value);
    }

    private Object getValue(Object pojo, ExtPropertyDescriptor pde) {
        Method readMethod = pde.getReadMethod();
        if (readMethod == null) {
            if (logger.isWarnEnabled()) {
                logger.warn("ro[type={}] prop[name={}] not found getter", pojo.getClass(), pde.getName());
            }
            return null;
        }
        return ReflectionUtils.invokeMethod(readMethod, pojo);
    }


    @Override
    public Object toValue(Class<Object> clazz, String prefix, DataItem[] redisData) {

        //TODO 这块希望重构，transient 的数据不应该被写入到ro的字段中，或者支持 @RedisWriteIgnore @RedisWriteIgnore @RedisReadIgnore 这些注解
        if (beanRegistry == null) {
            return null;
        }
        Object obj = beanRegistry.newBeanInstance(clazz);
        Map<String, DataItem[]> classifyItems = classify(redisData);

        for (ExtPropertyDescriptor pde : beanRegistry.findProperties(clazz)) {
            String name = pde.getName();
            DataItem[] data = classifyItems.get(name);
            Class type = pde.getPropertyType();
            Object value = this.getConverterRegistry().findConverter(type).toValue(type, name, data);
            setValue(obj, pde, value);
        }

        return obj;
    }

    private Map<String, DataItem[]> classify(DataItem[] items) {
        Map<String, DataItem[]> result = Maps.newHashMap();
        if (items == null || items.length == 0) return result;
        Multimap<String, DataItem> map = MultimapBuilder.hashKeys().arrayListValues().build();
        for (DataItem item : items) {
            map.put(item.getKey().split(Translator.SEPERATOR)[0], item);
        }
        for (Map.Entry<String, Collection<DataItem>> entry : map.asMap().entrySet()) {
            result.put(entry.getKey(), entry.getValue().toArray(new DataItem[entry.getValue().size()]));
        }
        return result;
    }

}
