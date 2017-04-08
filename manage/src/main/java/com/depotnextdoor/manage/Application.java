package com.depotnextdoor.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Management启动入口
 *
 * @author david-liu
 * @date 2017年04月06日
 * @reviewer
 */
@SpringBootApplication
public class Application {
    private static final Class appClz = Application.class;

    public static void main(String[] args) {
        SpringApplication.run(appClz);
    }

}
