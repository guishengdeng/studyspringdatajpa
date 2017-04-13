package com.biz.redis.trans.impl.convertor;


import com.biz.redis.trans.ConverterMatcher;
import com.biz.redis.trans.DataItem;
import com.biz.redis.trans.ValueConverter;
import org.apache.commons.lang3.EnumUtils;

/**
 * @author yanweijin
 * @date 2016/12/25
 */
public class EnumConverter<E extends Enum<E>> implements ValueConverter<E> {

    public static final ConverterMatcher MARCHER = Enum.class::isAssignableFrom;

    @Override
    public DataItem[] toRedisData(String key, Enum val) {
        if (val == null)
            return new DataItem[0];
        return new DataItem[]{new DataItem(key, val.name().getBytes())};
    }

    @Override
    public E toValue(Class<E> clazz, String prefix, DataItem[] redisData) {
        if (redisData == null || redisData.length == 0) {
            return null;
        }
        String enumName = new String(redisData[0].getData());
        return EnumUtils.getEnum(clazz, enumName);
    }
}
