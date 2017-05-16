package com.biz.rest;

import com.biz.support.jpa.repository.CommonRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Rest Application
 * Created by david-liu on 2017/05/04 11:30.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = "com.biz", repositoryFactoryBeanClass = CommonRepositoryFactoryBean.class)
@EntityScan(basePackages = "com.biz")
@EnableFeignClients(basePackages = "com.biz.soa.feign")
@EnableEurekaClient
@ComponentScan(basePackages = {"com.biz", "com.biz.rest.controller"})
@EnableCircuitBreaker
@ComponentScan(basePackages = {"com.biz.rest.controller"})
public class RestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class);
    }

    @Bean(name = DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_BEAN_NAME)
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

    @Bean
    public ServletRegistrationBean dispatcherRegistration(@Autowired DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(dispatcherServlet);
        registrationBean.getUrlMappings().clear();
        registrationBean.addUrlMappings("*.do");
        registrationBean.addUrlMappings("*.json");
        return registrationBean;
    }
}
