package com.biz.gbck.dao.redis;

import com.biz.core.asserts.SystemAsserts;
import com.biz.redis.ShardedJedisCurdCommonRedisDao;
import com.biz.redis.bean.BaseRedisObject;
import com.biz.redis.util.RedisUtil;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

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
        return set == null ? Collections.emptyList() : set.stream().map(RedisUtil::byteArrayToLong).collect(Collectors.toList());
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
        set.forEach(bs -> target.add(RedisUtil.byteArrayToLong(bs)));
        return target;
    }

    /**
     * 以timstamp为score,将id放入sorted-set
     * @author yanweijin
     * @date 2016年8月20日
     * @param key
     * @param timestamp
     * @param id
     * @return
     */
    protected boolean zadd(String key, Date timestamp, Integer id){
        return zadd(key, timestamp.getTime(), RedisUtil.toByteArray(id));
    }

    /**
     * 以timestamp为score,将id放入sorted-set
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
        long start = page * size, end = (page + 1) * size - 1;
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
        long nowSize = Math.min(size, llen(listKey));
        List<Object> list = pipeRpop(listKey.getBytes(), nowSize);
        Set<byte[]> idSet = list.stream().map(o -> (byte[]) o).collect(Collectors.toSet());
        return findByIds(idSet);
    }

    @SuppressWarnings("unchecked")
    protected List<Long> pipeLlen(List<String> keys) {
        try (ShardedJedis jedis = shardedJedisPool.getResource()) {
            ShardedJedisPipeline jedisPipeline = jedis.pipelined();
            keys.forEach(jedisPipeline::llen);
            return (List) jedisPipeline.syncAndReturnAll();
        }
    }
}

