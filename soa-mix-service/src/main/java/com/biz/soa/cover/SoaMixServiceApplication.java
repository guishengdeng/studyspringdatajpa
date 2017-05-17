package com.biz.soa.cover;

import com.biz.support.jpa.repository.CommonRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.ManagedBean;

/**
 * SOA-Product Application
 *
 * Created by david-liu on 2017/04/26 10:37.
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.biz.soa", excludeFilters = {
})
//@EnableJpaRepositories(basePackages = "com.biz", repositoryFactoryBeanClass = CommonRepositoryFactoryBean.class)
//@EntityScan(basePackages = "com.biz")
@EnableTransactionManagement
@EnableDiscoveryClient
@EnableFeignClients
public class SoaMixServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoaMixServiceApplication.class);
    }

}
