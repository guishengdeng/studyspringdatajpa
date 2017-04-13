package com.biz.gbck.dao.redis;

import com.biz.core.asserts.SystemAsserts;
import com.biz.redis.ShardedJedisCurdCommonRedisDao;
import com.biz.redis.bean.BaseRedisObject;
import com.biz.redis.util.RedisUtil;
import com.google.common.collect.Sets;
import java.io.Serializable;
import java.util.*;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;

/**
 * 请继承此类
 *
 * @author jun.liu(by xiaoyu)
 * @date 2016年8月13日
 * @reviewer
 */
public class CrudRedisDao<T extends BaseRedisObject<ID>, ID extends Serializable> extends ShardedJedisCurdCommonRedisDao<T, ID> {

    /**
     * 将Set<bytes[]>转换为List<Long>
     *
     * @author yanweijin
     * @date 2016年8月17日
     */
    protected List<Long> bytesSet2LongList(Collection<byte[]> set) {
        if (set == null) return Collections.emptyList();
        List<Long> result = new ArrayList<>(set.size());
        for (byte[] bs : set) {
            result.add(RedisUtil.byteArrayToLong(bs));
        }
        return result;
    }

    /**
     * 将Set<bytes[]>的数据转换为Long后装入目标集合
     *
     * @author yanweijin
     * @date 2016年8月20日
     */
    protected <TARGET extends Collection<Long>> TARGET bytesSet2TargetCollection(Collection<byte[]> set, TARGET target) {
        SystemAsserts.notNull(target);
        if (set == null) return target;
        for (byte[] bs : set) {
            target.add(RedisUtil.byteArrayToLong(bs));
        }
        return target;
    }

    /**
     * 以timstamp为score,将id放入sorted-set
     *
     * @author yanweijin
     * @date 2016年8月20日
     */
    protected boolean zadd(String key, Date timestamp, Long id) {
        return zadd(key, timestamp.getTime(), RedisUtil.toByteArray(id));
    }

    /**
     * @Description: 以time为score 将string放入sorted-set
     * @author mounan
     * @time:2017年1月18日 下午4:58:39
     */
    protected boolean zadd(String key, Date timestamp, String str) {
        return zadd(key, timestamp.getTime(), RedisUtil.toByteArray(str));
    }


    /**
     * 从一个保存Long值的sorted-set中,分页查询值,并转化为一个List<Long>返回,降序
     *
     * @author yanweijin
     * @date 2016年8月20日
     */
    protected List<Long> findIdListFromSortedSetRevrange(String key, Integer page, Integer size) {
        long start = page * size;//0
        long end = (page + 1) * size - 1;//10
        Set<byte[]> set = zrevrange(key, start, end);
        return bytesSet2LongList(set);
    }

    /**
     * 配合pop使用
     *
     * @author yanweijin
     * @date 2016年9月17日
     */
    protected void lpushToList(String listKey, ID id) {
        //将足迹信息存入list,便于task持久化
        lpush(listKey, RedisUtil.toByteArray(id));
    }

    /**
     * 配合push使用
     *
     * @author yanweijin
     * @date 2016年9月17日
     */
    protected List<T> rpopFromList(String listKey, int size) {
        long nowsize = llen(listKey);
        nowsize = Math.min(size, nowsize);
        List<Object> list = pipeRpop(listKey.getBytes(), nowsize);
        Set<byte[]> idset = Sets.newHashSet();
        for (Object o : list) {
            byte[] data = (byte[]) o;
            idset.add(data);
        }
        return findByIds(idset);
    }

    protected List<Long> pipeLlen(List<String> keys) {
        ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
            ShardedJedisPipeline jedisPipeline = jedis.pipelined();
            for (String key : keys) {
                jedisPipeline.llen(key);
            }
            List lenth = jedisPipeline.syncAndReturnAll();
            return lenth;
        } finally {
            shardedJedisPool.returnResource(jedis);
        }
    }
}

