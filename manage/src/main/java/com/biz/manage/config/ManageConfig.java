package com.biz.manage.config;

import com.aliyun.oss.OSSClient;
import com.biz.core.ali.oss.config.OssConfig;
import com.biz.core.event.BizEventMulticaster;
import com.biz.core.event.BizEventPublisher;
import com.biz.core.transaction.BizTransactionManager;
import com.biz.manage.security.ManageLogoutSuccessHandler;
import com.biz.service.IdService;
import com.biz.service.security.AdminServiceImpl;
import java.beans.PropertyVetoException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author david-liu
 * @date 2017年04月08日
 * @reviewer
 */
@Configuration
@EnableRedisHttpSession
public class ManageConfig {

    private Environment environment;

    public ManageConfig(@Autowired Environment environment) {
        this.environment = environment;
    }

    @Bean
    public ManageLogoutSuccessHandler manageLogoutSuccessHandler() {
        return new ManageLogoutSuccessHandler();
    }

    @Bean
    public Md5PasswordEncoder md5PasswordEncoder() {
        return new Md5PasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new AdminServiceImpl();
    }

    @Bean
    public IdService idService() {
        Integer soaIdx = Integer.valueOf(environment.getProperty("biz.soa.idx"));
        String soaIdxZooNodePath = environment.getProperty("biz.zookeeper.soaIdx.path");
        String zookeeperUrl = environment.getProperty("biz.zookeeper.url");
        return new IdService(soaIdx, soaIdxZooNodePath, zookeeperUrl);
    }

    @Bean
    public BizEventMulticaster bizEventMulticaster() {
        return new BizEventMulticaster();
    }

    @Bean
    public BizEventPublisher bizEventPublisher() {
        return new BizEventPublisher();
    }

    @Bean
    public JpaTransactionManager transactionManager(@Autowired BizEventPublisher bizEventPublisher) throws PropertyVetoException {
        BizTransactionManager jpaTransactionManager = new BizTransactionManager();
        jpaTransactionManager.setEventPublisher(bizEventPublisher);
        return jpaTransactionManager;
    }

    @Bean
    public JedisConnectionFactory connectionFactory(@Autowired PropertiesConfiguration redisConfiguration) {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(redisConfiguration.getString("biz.redis.host"));
        jedisConnectionFactory.setPort(redisConfiguration.getInt("biz.redis.port"));
        jedisConnectionFactory.setClientName(redisConfiguration.getString("biz.redis.name"));
        jedisConnectionFactory.setPassword(redisConfiguration.getString("biz.redis.password"));
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(redisConfiguration.getInt("biz.redis.maxTotal"));
        jedisPoolConfig.setMaxIdle(redisConfiguration.getInt("biz.redis.maxIdle"));
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(redisConfiguration.getLong("biz.redis.timeBetweenEvictionRunsMillis"));
        jedisPoolConfig.setTestOnBorrow(redisConfiguration.getBoolean("biz.redis.testOnBorrow"));
        jedisPoolConfig.setMinEvictableIdleTimeMillis(redisConfiguration.getLong("biz.redis.minEvictableIdleTimeMillis"));
        jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
        return jedisConnectionFactory;
    }

    @Bean
    public OssConfig ossConfig() {
        OssConfig ossConfig = new OssConfig();
        ossConfig.setRemoteEndpoint(environment.getProperty("biz.oss.remoteEndpoint"));
        ossConfig.setLocalEndpoint(environment.getProperty("biz.oss.localEndpoint"));
        ossConfig.setAccessKeyId(environment.getProperty("biz.oss.accessKeyId"));
        ossConfig.setAccessSecret(environment.getProperty("biz.oss.accessKeySecret"));
        ossConfig.setBucketName(environment.getProperty("biz.oss.bucketName"));
        return ossConfig;
    }

    @Bean
    public OSSClient ossClient() {
        String endpoint = environment.getProperty("biz.oss.remoteEndpoint");
        String accessKeyId = environment.getProperty("biz.oss.accessKeyId");
        String secretAccessKey = environment.getProperty("biz.oss.accessKeySecret");
        return new OSSClient(endpoint, accessKeyId, secretAccessKey);
    }

}
