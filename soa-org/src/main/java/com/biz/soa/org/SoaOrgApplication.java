package com.biz.soa.org;

import com.biz.core.event.AbstractBizEventListener;
import com.biz.service.system.InitManager;
import com.biz.soa.org.listener.ShopQualificationUpdateListener;
import com.biz.support.jpa.repository.CommonRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Soa Promotion入口
 *
 * Created by david-liu on 2017/04/24 12:15.
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.biz",
 excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = InitManager.class)
//        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = BrandServiceImpl.class),
//        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = CategoryServiceImpl.class)
}
)
@EnableJpaRepositories(basePackages = "com.biz", repositoryFactoryBeanClass = CommonRepositoryFactoryBean.class)
@EntityScan(basePackages = "com.biz")
@EnableTransactionManagement
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.biz.soa.feign")
@EnableEurekaClient
@EnableAutoConfiguration
@EnableCircuitBreaker
@EnableHystrix
public class SoaOrgApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SoaOrgApplication.class);
        application.addListeners();
        application.addListeners(new ShopQualificationUpdateListener());
        application.run(args);
    }
}
