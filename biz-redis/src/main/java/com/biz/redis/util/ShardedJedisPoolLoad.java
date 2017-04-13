package com.biz.redis.util;

import com.biz.core.util.StringUtil;
import com.biz.core.zookeeper.ZkProperties;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.configuration.PropertiesConfiguration;
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

    private static final Logger log = LoggerFactory.getLogger(ShardedJedisPoolLoad.class);
    private static ShardedJedisPool shardedJedisPool;

    synchronized public static ShardedJedisPool getJedisPool() {
        return shardedJedisPool;
    }

    /**
     * 获取redis 连接池, 支持多host主机列表配置
     */
    synchronized public static ShardedJedisPool getJedisPool(String zookeeperUrl, Boolean configByZookeeper, String localConfigPath) {
        if (shardedJedisPool == null) {
            log.debug("开始初始化redis池对象");
            PropertiesConfiguration config = null;
            try {
                if (configByZookeeper && StringUtils.isNotBlank(zookeeperUrl)) {
                    log.debug("zookeeper加载redis配置文件");
                    ZkProperties zkProperties = new ZkProperties(zookeeperUrl, "/bozhi/config/redis.properties");
                    config = new PropertiesConfiguration();
                    config.load(new ByteArrayInputStream(zkProperties.getPropertiesBytes()));
                } else {
                    // 从classpath加载配置文件
                    config = new PropertiesConfiguration(localConfigPath);
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("加载redis配置出错,-->", e.getMessage());
            }

            GenericObjectPoolConfig poolConfig = new JedisPoolConfig();
            poolConfig.setMaxTotal(config.getInt("redis.maxTotal"));
            poolConfig.setMaxIdle(config.getInt("redis.maxIdle"));
            poolConfig.setTimeBetweenEvictionRunsMillis(config.getLong("redis.timeBetweenEvictionRunsMillis"));
            poolConfig.setMinEvictableIdleTimeMillis(config.getLong("redis.minEvictableIdleTimeMillis"));
            poolConfig.setTestOnBorrow(config.getBoolean("redis.testOnBorrow"));

            log.debug("开始加载redis主机列表");
            String hosts = config.getString("redis.host");
            String ports = config.getString("redis.port");
            String dbnames = config.getString("redis.name");
            String pwasswords = config.getString("redis.password");
            List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
            String[] hostArr = hosts.split("@");
            String[] portArr = ports.split("@");
            String[] dbnameArr = null;
            if (StringUtils.isNotBlank(dbnames)) {
                dbnameArr = dbnames.split("@");
            }
            String[] pwasswordArr = null;
            if (StringUtils.isNotBlank(pwasswords)) {
                pwasswordArr = pwasswords.split("@");
            }
            if (hostArr.length == portArr.length
                //&& hostArr.length == dbnameArr.length && hostArr.length  == pwasswordArr.length
                //&&portArr.length == dbnameArr.length && portArr.length  == pwasswordArr.length
                //&&dbnameArr.length  == pwasswordArr.length
                    ) {
                for (int i = 0; i < hostArr.length; i++) {
                    // 发现redis主机配置
                    String host = hostArr[i].trim();
                    int port = Integer.parseInt(portArr[i].trim());
                    String name = (dbnameArr == null) ? "" : (dbnameArr[i] == null ? "" : dbnameArr[i].trim());
                    String password = (pwasswordArr == null) ? "" : (pwasswordArr[i] == null ? "" : pwasswordArr[i].trim());
                    log.debug("redis主机, host: {}, port: {}, name: {}", host, port, name);
                    JedisShardInfo jedisShardInfo = null;
                    if (StringUtil.isNullOrEmpty(name)) {
                        jedisShardInfo = new JedisShardInfo(host, port, config.getInt("redis.socketTimeout", 2000));
                    } else {
                        jedisShardInfo = new JedisShardInfo(host, port, config.getInt("redis.socketTimeout", 2000), name);
                    }
                    if (StringUtils.isNotBlank(password)) {
                        jedisShardInfo.setPassword(password);
                    }
                    shards.add(jedisShardInfo);
                }
            } else {
                log.error("redis配置有问题，请检查");
                throw new RuntimeException("redis配置有问题，请检查");
            }
            if (shards.isEmpty()) {
                log.error("请指定至少一个redis主机");
                throw new RuntimeException("请指定至少一个redis主机");
            }
            //考虑按照业务分片
            //            shardedJedisPool = new ShardedJedisPool(poolConfig, shards, new ShardHashing());
            //不安业务分片
            shardedJedisPool = new ShardedJedisPool(poolConfig, shards);
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
