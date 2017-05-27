package com.biz.soa.order.config;

import com.biz.core.event.BizEventMulticaster;
import com.biz.core.event.BizEventPublisher;
import com.biz.core.transaction.BizTransactionManager;
import com.biz.pay.alipay.AlipayFactory;
import com.biz.pay.wechat.WeChatPayFactory;
import com.biz.service.IdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;

import java.beans.PropertyVetoException;
import java.util.Properties;

/**
 * Created by david-liu on 2017/04/18 14:09.
 */
@Configuration
public class OrderConfig {

    private Environment environment;

    public OrderConfig(@Autowired Environment environment) {
        this.environment = environment;
        this.initAlipayConfig();
        this.initWechatConfig();
    }

    private void initAlipayConfig() {
        Properties properties = new Properties();
        String notifyUrl = environment.getProperty("payment.alipay.notify-url");
        properties.setProperty("notify.url", notifyUrl);
        AlipayFactory.setConf(properties);
    }

    private void initWechatConfig() {
        Properties properties = new Properties();
        String defaultAppId = environment.getProperty("payment.wechat.appid.default");
        String mchId = environment.getProperty("payment.wechat.mch_id." + defaultAppId);
        String key = environment.getProperty("payment.wechat.key." + defaultAppId);
        String identification = environment.getProperty("payment.wechat.LOAD_IDENTIFICATION." + defaultAppId);
        String createIp = environment.getProperty("payment.wechat.spbill_create_ip");
        String notifyUrl = environment.getProperty("payment.wechat.notify-url");
        properties.setProperty("appid.default", defaultAppId);
        properties.setProperty("mch_id", mchId);
        properties.setProperty("key", key);
        properties.setProperty("key." + defaultAppId, key);
        properties.setProperty("LOAD_IDENTIFICATION", identification);
        properties.setProperty("spbill_create_ip", createIp);
        properties.setProperty("notify_url", notifyUrl);
        WeChatPayFactory.setConf(properties);
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


}
