package com.biz.soa.promotion;

import com.biz.service.product.impl.BrandServiceImpl;
import com.biz.service.product.impl.CategoryServiceImpl;
import com.biz.service.system.InitManager;
import com.biz.support.jpa.repository.CommonRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
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
@ComponentScan(basePackages = "com.biz", excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = InitManager.class),
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = BrandServiceImpl.class),
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = CategoryServiceImpl.class)
})
@EnableJpaRepositories(basePackages = "com.biz", repositoryFactoryBeanClass = CommonRepositoryFactoryBean.class)
@EntityScan(basePackages = "com.biz")
@EnableFeignClients(basePackages = "com.biz.soa.feign")
@EnableTransactionManagement
@EnableEurekaClient
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableHystrix
public class SoaPromotionApplication {
    public static void main(String[] args) {
        SpringApplication.run(SoaPromotionApplication.class);
    }
}
