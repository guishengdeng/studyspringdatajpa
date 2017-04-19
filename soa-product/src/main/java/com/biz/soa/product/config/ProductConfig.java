package com.biz.soa.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by david-liu on 2017/04/18 14:09.
 */
@Configuration
public class ProductConfig {

    @Bean
    public RecConfig recConfig() {
        return new RecConfig();
    }

}
