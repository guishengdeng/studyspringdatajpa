package com.biz.redis.trans.impl;

import com.biz.redis.trans.ConverterMatcher;
import com.biz.redis.trans.ConverterRegistry;
import com.biz.redis.trans.ValueConverter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;

/**
 * @author yanweijin
 * @date 2016/12/22
 */
public class DefaultConverterRegistry implements ConverterRegistry {

    private Map<Class, ValueConverter> level1Container = Maps.newHashMap();
    private List<Bucket> level2Container = Lists.newArrayList();


    @Override
    public <V> void registerConverter(ConverterMatcher matcher, ValueConverter<V> converter) {
        level2Container.add(new Bucket(matcher, converter));
    }

    @Override
    public <V> void registerConverter(Class<V> clazz, ValueConverter<V> converter) {
        level1Container.put(clazz, converter);
    }

    @Override
    public ValueConverter<Object> findConverter(Class clazz) {
        ValueConverter vc = level1Container.get(clazz);
        if (vc == null) {
            for (Bucket b : level2Container) {
                if (b.converterMatcher.isMatch(clazz)) {
                    vc = b.valueConverter;
                    break;
                }
            }
        }
        return vc;
    }

    @Override
    public boolean isSupportedPropertyType(Class clazz) {
        return findConverter(clazz) != null;
    }

    private static class Bucket {
        ValueConverter valueConverter;
        ConverterMatcher converterMatcher;

        public Bucket(ConverterMatcher converterMatcher, ValueConverter valueConverter) {
            this.valueConverter = valueConverter;
            this.converterMatcher = converterMatcher;
        }
    }
}
