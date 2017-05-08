package com.biz.soa;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Biz Soa 服务注册中心
 *
 * Created by david-liu on 2017/04/26 16:50.
 */
@SpringBootApplication
@EnableEurekaServer
public class SoaServiceCenter {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SoaServiceCenter.class).web(true).run(args);
    }

}
