//package com.depotnextdoor.manage.configuration;
//
//import com.depotnextdoor.manage.security.ManageLogoutSuccessHandler;
//import com.depotnextdoor.service.IdService;
//import com.depotnextdoor.service.security.AdminServiceImpl;
//import com.mchange.v2.c3p0.ComboPooledDataSource;
//import java.beans.PropertyVetoException;
//import javax.sql.DataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
//import org.springframework.security.core.userdetails.UserDetailsService;
//
///**
// * @author david-liu
// * @date 2017年04月08日
// * @reviewer
// */
//@Configuration
//public class ManageConfig {
//
//    private Environment environment;
//
//    public ManageConfig(@Autowired Environment environment) {
//        this.environment = environment;
//    }
//
//    @Bean
//    public ManageLogoutSuccessHandler manageLogoutSuccessHandler() {
//        return new ManageLogoutSuccessHandler();
//    }
//
//    @Bean
//    public Md5PasswordEncoder md5PasswordEncoder() {
//        return new Md5PasswordEncoder();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new AdminServiceImpl();
//    }
//
//    @Bean
//    public IdService idService() {
//        Integer soaIdx = Integer.valueOf(environment.getProperty("biz.soa.idx"));
//        return new IdService(soaIdx);
//    }
//
//    @Bean
//    public DataSource dataSource() throws PropertyVetoException {
//        String driverClz = environment.getProperty("spring.datasource.driver-class-name");
//        String username = environment.getProperty("spring.datasource.username");
//        String password = environment.getProperty("spring.datasource.password");
//        String url = environment.getProperty("spring.datasource.url");
//        ComboPooledDataSource dataSource = new ComboPooledDataSource();
//        dataSource.setDriverClass(driverClz);
//        dataSource.setJdbcUrl(url);
//        dataSource.setUser(username);
//        dataSource.setPassword(password);
//        dataSource.setMaxPoolSize(100);
//        dataSource.setMinPoolSize(50);
//        dataSource.setInitialPoolSize(20);
//        dataSource.setMaxIdleTime(300);
//        dataSource.setIdleConnectionTestPeriod(60);
//        dataSource.setAcquireIncrement(5);
//        return dataSource;
//    }
//}
