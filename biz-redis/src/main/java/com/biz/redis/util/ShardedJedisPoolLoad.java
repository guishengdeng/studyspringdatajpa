package com.biz.redis.util;

import com.biz.core.util.StringUtil;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Hashing;

/**
 * load ShardedJedisPool
 *
 * @author jun.liu(by xiaoyu)
 * @date 2016年8月13日
 * @reviewer
 */
public class ShardedJedisPoolLoad {

    private static final Logger logger = LoggerFactory.getLogger(ShardedJedisPoolLoad.class);

    private static ShardedJedisPool shardedJedisPool;

    synchronized public static ShardedJedisPool getJedisPool() {
        return shardedJedisPool;
    }

    /**
     * 获取redis 连接池, 支持多host主机列表配置
     */
    synchronized public static ShardedJedisPool getJedisPool(PropertiesConfiguration propertiesConfiguration) {
        if (shardedJedisPool == null) {
            logger.debug("开始初始化redis池对象");
            GenericObjectPoolConfig redisPoolConfig = new JedisPoolConfig();
            redisPoolConfig.setMaxTotal(propertiesConfiguration.getInt("biz.redis.maxTotal"));
            redisPoolConfig.setMaxIdle(propertiesConfiguration.getInt("biz.redis.maxIdle"));
            redisPoolConfig.setTimeBetweenEvictionRunsMillis(propertiesConfiguration.getLong("biz.redis.timeBetweenEvictionRunsMillis"));
            redisPoolConfig.setMinEvictableIdleTimeMillis(propertiesConfiguration.getLong("biz.redis.minEvictableIdleTimeMillis"));
            redisPoolConfig.setTestOnBorrow(propertiesConfiguration.getBoolean("biz.redis.testOnBorrow"));

            logger.debug("开始加载redis主机列表");
            String hosts = propertiesConfiguration.getString("biz.redis.host");
            String ports = propertiesConfiguration.getString("biz.redis.port");
            String dbNames = propertiesConfiguration.getString("biz.redis.name");
            String passwords = propertiesConfiguration.getString("biz.redis.password");
            List<JedisShardInfo> shards = new ArrayList<>();
            String[] hostArr = hosts.split("@");
            String[] portArr = ports.split("@");
            String[] dbNameArr = null;
            if (StringUtils.isNotBlank(dbNames)) {
                dbNameArr = dbNames.split("@");
            }
            String[] passwordArr = null;
            if (StringUtils.isNotBlank(passwords)) {
                passwordArr = passwords.split("@");
            }

            Preconditions.checkArgument(ArrayUtils.isNotEmpty(hostArr) && ArrayUtils.isNotEmpty(portArr));
            if (ArrayUtils.isNotEmpty(passwordArr)) {
                Preconditions.checkArgument(ArrayUtils.isNotEmpty(hostArr) && ArrayUtils.isNotEmpty(passwordArr));
            }
            if (hostArr.length == portArr.length) {
                for (int i = 0; i < hostArr.length; i++) {
                    // 发现redis主机配置
                    String host = hostArr[i].trim();
                    int port = Integer.parseInt(portArr[i].trim());
                    String name = (dbNameArr == null) ? "" : (dbNameArr[i] == null ? "" : dbNameArr[i].trim());
                    String password = (passwordArr == null) ? "" : (passwordArr[i] == null ? "" : passwordArr[i].trim());
                    logger.debug("redis主机, host: {}, port: {}, name: {}", host, port, name);
                    JedisShardInfo jedisShardInfo;
                    if (StringUtil.isNullOrEmpty(name)) {
                        jedisShardInfo = new JedisShardInfo(host, port, propertiesConfiguration.getInt("biz.redis.socketTimeout", 2000));
                    } else {
                        jedisShardInfo = new JedisShardInfo(host, port, propertiesConfiguration.getInt("biz.redis.socketTimeout", 2000), name);
                    }
                    if (StringUtils.isNotBlank(password)) {
                        jedisShardInfo.setPassword(password);
                    }
                    shards.add(jedisShardInfo);
                }
            } else {
                logger.error("redis配置有问题，请检查");
                throw new RuntimeException("redis配置有问题，请检查");
            }
            if (shards.isEmpty()) {
                logger.error("请指定至少一个redis主机");
                throw new RuntimeException("请指定至少一个redis主机");
            }
            //考虑按照业务分片
            //            shardedJedisPool = new ShardedJedisPool(poolConfig, shards, new ShardHashing());
            //不安业务分片
            shardedJedisPool = new ShardedJedisPool(redisPoolConfig, shards);
        }
        return shardedJedisPool;
    }

    public static class ShardHashing implements Hashing {
        //key 前缀，如果按照业务分片，需要将key规范，前缀必须出现在下表中
        static final String keys = "global user shop colony timeline deal";

        /**
         * @see redis.clients.util.Sharded#initialize 把 全局放在第一个,如果没有找到已经分组的就放在 global中
         * @see redis.clients.util.Sharded#getShardInfo user shop timeline colony global
         */
        @Override
        public long hash(String key) {
            if (key.indexOf("SHARD-") == 0) {
                return 0;
            } else {
                return keys.indexOf(StringUtils.substringBefore(key, "*"));
            }
        }

        /**
         * @see redis.clients.util.Sharded#getShardInfo(byte[] key)
         */
        @Override
        public long hash(byte[] key) {
            return keys.indexOf(StringUtils.substringBefore(new String(key), ":"));
        }
    }

}
