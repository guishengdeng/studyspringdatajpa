package com.depotnextdoor.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Management启动入口
 *
 * @author david-liu
 * @date 2017年04月06日
 * @reviewer
 */
@SpringBootApplication
//@ComponentScan(basePackages = "com.depotnextdoor")
//@EntityScan(basePackages = "com.depotnextdoor")
//@EnableJpaRepositories(basePackages = "com.depotnextdoor")
//@EnableTransactionManagement
public class Application extends SpringBootServletInitializer {
    private static final Class appClz = Application.class;

    public static void main(String[] args) {
        SpringApplication.run(appClz);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(appClz);
    }

    //    @Bean(name = DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_BEAN_NAME)
    //    public DispatcherServlet dispatcherServlet() {
    //        return new DispatcherServlet();
    //    }
    //
    //    @Bean
    //    public ServletRegistrationBean dispatcherRegistration(@Autowired DispatcherServlet dispatcherServlet) {
    //        ServletRegistrationBean registrationBean = new ServletRegistrationBean(dispatcherServlet);
    //        registrationBean.getUrlMappings().clear();
    //        registrationBean.addUrlMappings("*.do");
    //        return registrationBean;
    //    }
}
