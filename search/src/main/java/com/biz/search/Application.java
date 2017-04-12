package com.biz.search;

import com.biz.support.es.esrepository.common.CommonESRepositoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author david-liu
 * @date 2017年04月12日
 * @reviewer
 */
@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "com.biz.search", repositoryFactoryBeanClass = CommonESRepositoryBean.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean(name = DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_BEAN_NAME)
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean(@Autowired DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(dispatcherServlet);
        registrationBean.getUrlMappings().clear();
        registrationBean.addUrlMappings("*.do");
        return registrationBean;
    }
}
