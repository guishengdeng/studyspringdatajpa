package com.biz.redis.trans.impl;

import com.biz.core.property.ExtPropertyDescriptor;
import com.biz.redis.trans.BeanRegistry;
import com.biz.redis.trans.ConverterRegistry;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

/**
 * @author yanweijin
 * @date 2016/12/22
 */
public class DefaultBeanRegistry implements BeanRegistry {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private ConverterRegistry converterRegistry;

    private Map<Class, List<ExtPropertyDescriptor>> properties = Maps.newConcurrentMap();

    @Override
    public List<ExtPropertyDescriptor> findProperties(Class beanClazz) {
        List<ExtPropertyDescriptor> result = properties.get(beanClazz);
        if (result == null) {
            synchronized (beanClazz) {
                registerObject(beanClazz);
                result = properties.get(beanClazz);
            }
        }
        return result;
    }

    @Override
    public <RO> RO newBeanInstance(Class<RO> beanClazz) {
        try {
            return beanClazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            ReflectionUtils.handleReflectionException(e);
            //make compiler happy
            return null;
        }
    }

    @Override
    public void registerObject(Class beanClazz) {
        List<ExtPropertyDescriptor> result = Lists.newArrayList();
        try {
            BeanInfo bi = Introspector.getBeanInfo(beanClazz, Object.class);
            PropertyDescriptor[] pds = bi.getPropertyDescriptors();
            for (PropertyDescriptor pd : pds) {
                if (getConverterRegistry().isSupportedPropertyType(pd.getPropertyType())) {
                    result.add(new ExtPropertyDescriptor(pd, beanClazz));
                } else {
                    logger.warn("ro[clazz={}] prop[name={},type={}] is unsupported", beanClazz.getClass(), pd.getName(), pd.getPropertyType());
                }
            }
        } catch (IntrospectionException e) {

        }
        properties.put(beanClazz, result);
    }

    @Override
    public ConverterRegistry getConverterRegistry() {
        return converterRegistry;
    }

    public void setConverterRegistry(ConverterRegistry converterRegistry) {
        this.converterRegistry = converterRegistry;
    }
}
