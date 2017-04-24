package com.biz.redis.trans;

import com.biz.redis.bean.BaseRedisObject;
import java.util.Map;

/**
 * @author yanweijin
 * @date 2016/12/21
 */
public interface Translator {

    String SEPERATOR = ".";

    /**
     * 将一个 redis hash 数据转换为一个 ro 对象
     */
    <RO> RO toObject(Class<RO> clazz, Map<byte[], byte[]> redisData);


    /**
     * 将一个 redis hash 数据向对象中填充
     *
     * @param ro 被填充数据的对象
     */
    void fillObject(Object ro, Map<byte[], byte[]> redisData);


    /**
     * 将一个 ro 对象转换为 redis hash 数据
     */
    Map<byte[], byte[]> toRedisData(BaseRedisObject object);


    DataTransformer getDataTransformer();

    ConverterRegistry getConverterRegistry();

    BeanRegistry getBeanRegistry();

}
