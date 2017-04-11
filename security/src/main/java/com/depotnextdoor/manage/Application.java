package com.depotnextdoor.manage;

import javax.servlet.Filter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = "com.depotnextdoor")
@EnableJpaRepositories(basePackages = "com.depotnextdoor")
@EntityScan(basePackages = "com.depotnextdoor")
@EnableTransactionManagement
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

    @Bean
    public Filter openEntityManagerInViewFilter() {
        return new OpenEntityManagerInViewFilter();
    }


}
