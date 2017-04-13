package com.biz.redis.config;

import com.biz.core.zookeeper.ZkProperties;
import com.biz.redis.util.ShardedJedisPoolLoad;
import java.io.ByteArrayInputStream;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.codelogger.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import redis.clients.jedis.ShardedJedisPool;

/**
 * @author david-liu
 * @date 2017年04月13日
 * @reviewer
 */
@Configuration
public class RedisConfig {

    private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    @Autowired
    private Environment environment;

    @Bean
    public ShardedJedisPool shardedJedisPool() throws ConfigurationException {
        String zookeeperUrl = environment.getProperty("biz.zookeeper.url", String.class),
                redisConfigZookeeperPath = environment.getProperty("biz.zookeeper.redis.path", String.class);
        boolean isConfigRedisByZookeeper = StringUtils.isNotBlank(zookeeperUrl) && StringUtils.isNotBlank(redisConfigZookeeperPath);
        PropertiesConfiguration propertiesConfiguration = new PropertiesConfiguration();
        if (isConfigRedisByZookeeper) {
            logger.info("使用zookeeper加载redis配置文件, zookeeperUrl: [{}], 配置存放Zookeeper Path: [{}]", zookeeperUrl, redisConfigZookeeperPath);
            ZkProperties zkProperties = new ZkProperties(zookeeperUrl, redisConfigZookeeperPath);
            propertiesConfiguration.load(new ByteArrayInputStream(zkProperties.getPropertiesBytes()));
        } else {
            logger.info("未使用zookeeper加载redis配置文件");
            propertiesConfiguration.addProperty("biz.redis.host", environment.getProperty("biz.redis.host"));
            propertiesConfiguration.addProperty("biz.redis.password", environment.getProperty("biz.redis.password"));
            propertiesConfiguration.addProperty("biz.redis.name", environment.getProperty("biz.redis.name"));
            propertiesConfiguration.addProperty("biz.redis.port", environment.getProperty("biz.redis.port"));
            propertiesConfiguration.addProperty("biz.redis.maxTotal", environment.getProperty("biz.redis.maxTotal", Integer.class));
            propertiesConfiguration.addProperty("biz.redis.maxIdle", environment.getProperty("biz.redis.maxIdle", Integer.class));
            propertiesConfiguration.addProperty("biz.redis.timeBetweenEvictionRunsMillis", environment.getProperty("biz.redis.timeBetweenEvictionRunsMillis", Integer.class));
            propertiesConfiguration.addProperty("biz.redis.minEvictableIdleTimeMillis", environment.getProperty("biz.redis.minEvictableIdleTimeMillis", Integer.class));
            propertiesConfiguration.addProperty("biz.redis.testOnBorrow", environment.getProperty("biz.redis.testOnBorrow", Boolean.class));
        }

        return ShardedJedisPoolLoad.getJedisPool(propertiesConfiguration);
    }
}
