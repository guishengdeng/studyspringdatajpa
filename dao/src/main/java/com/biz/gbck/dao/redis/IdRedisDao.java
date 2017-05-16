package com.biz.gbck.dao.redis;

import com.biz.gbck.common.dao.redis.CommonRedisDao;
import com.biz.gbck.common.ro.RedisKeyGenerator;
import com.biz.gbck.enums.IdType;
import com.biz.redis.util.RedisUtil;
import org.springframework.stereotype.Component;

/**
 * Created by defei on 3/20/16.
 */
@Component
public class IdRedisDao extends CommonRedisDao {

    /**
     * 获取下一个长整型ID
     */
    public long nextLongId(IdType idType) {
        byte[] nextId = lpop(RedisKeyGenerator.System.getIdPoolListKey(idType));
        return RedisUtil.byteArrayToLong(nextId);
    }

    /**
     * 当前池里面还剩多少
     */
    public long remainCount(IdType idType) {
        return llen(RedisKeyGenerator.System.getIdPoolListKey(idType));
    }

    /**
     * 生成后一批整型Id,添加到池中
     */
    public void generatorLongIds(IdType idType, Long from, int size) {
        long[] arr = new long[size];
        for (int i = 0; i < size; i++) {
            arr[i] = from + i;
        }
        rpush(RedisKeyGenerator.System.getIdPoolListKey(idType), RedisUtil.toByteArray(arr));
        hset(RedisKeyGenerator.System.getConfigHashKey(), idType.name(), RedisUtil.toByteArray(from + size + 1));
    }

    public Number getFromNum(IdType idType, Long defaultFrom) {
        byte[] b = hget(RedisKeyGenerator.System.getConfigHashKey(), idType.name());
        if (b != null) {
            return RedisUtil.byteArrayToLong(b);
        } else {
            return defaultFrom;
        }
    }

}
