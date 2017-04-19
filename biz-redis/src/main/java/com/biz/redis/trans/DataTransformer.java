package com.biz.redis.trans;

import java.util.Map;

/**
 * @author yanweijin
 * @date 2016/12/22
 */
public interface DataTransformer {

    /**
     * 将一个 redis 数据结构转换为 DataItem 的数组
     */
    DataItem[] redisData2Items(Map<byte[], byte[]> redisData);

    Map<String, DataItem[]> redisData2ItemsMap(Map<byte[], byte[]> redisData);

    Map<byte[], byte[]> items2RedisData(DataItem[] items);

}
