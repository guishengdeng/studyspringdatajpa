package com.biz.redis.trans.impl.convertor;

import com.biz.redis.trans.ConverterRegistry;
import com.biz.redis.trans.DataItem;
import com.biz.redis.trans.Translator;
import com.biz.redis.trans.ValueConverter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yanweijin
 * @date 2016/12/22
 */
public abstract class AbstractMultiValueConverter<V> implements ValueConverter<V> {
    private ConverterRegistry converterRegistry;
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected ConverterRegistry getConverterRegistry() {
        return converterRegistry;
    }

    public AbstractMultiValueConverter(ConverterRegistry converterRegistry) {
        this.converterRegistry = converterRegistry;
    }

    protected DataItem[] resolveItems(String key, Object value) {
        return this.resolveItems(null, key, value);
    }

    protected DataItem[] resolveItems(String prefix, String propKey, Object value) {
        if (StringUtils.isNotBlank(prefix)) {
            propKey = prefix + Translator.SEPERATOR + propKey;
        }
        ValueConverter<Object> convertor = getConverterRegistry().findConverter(value.getClass());
        if (convertor == null) {
            logger.warn("prop[name={},type={}] convertor not found", propKey, value.getClass().getSimpleName());
        }
        return convertor.toRedisData(propKey, value);
    }

}
