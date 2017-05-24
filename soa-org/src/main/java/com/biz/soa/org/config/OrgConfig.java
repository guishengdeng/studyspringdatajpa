package com.biz.soa.org.config;

import com.biz.core.ali.oss.config.OssConfig;
import com.biz.core.event.BizEventMulticaster;
import com.biz.core.event.BizEventPublisher;
import com.biz.core.transaction.BizTransactionManager;
import com.biz.service.IdService;
import java.beans.PropertyVetoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;


/**
 * 促销服务Config
 *
 * Created by david-liu on 2017/04/24 15:11.
 */
@Configuration
public class OrgConfig {

    private Environment environment;

    public OrgConfig(@Autowired Environment environment) {
        this.environment = environment;
    }

    @Bean
    public IdService idService(@Autowired Environment environment) {
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
    public OssConfig ossConfig() {
        return new OssConfig(
                environment.getProperty("biz.oss.remoteEndpoint"),
                environment.getProperty("biz.oss.localEndpoint"),
                environment.getProperty("biz.oss.accessKeyId"),
                environment.getProperty("biz.oss.accessKeySecret"),
                environment.getProperty("biz.oss.productBucketName"),
                environment.getProperty("biz.oss.auditBucketName"),
                environment.getProperty("biz.oss.userId")
        );
    }

}
