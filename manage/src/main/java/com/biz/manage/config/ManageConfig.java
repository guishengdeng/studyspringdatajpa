package com.biz.manage.config;

import com.biz.manage.security.ManageLogoutSuccessHandler;
import com.biz.service.IdService;
import com.biz.service.security.AdminServiceImpl;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.support.MergingPersistenceUnitManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author david-liu
 * @date 2017年04月08日
 * @reviewer
 */
@Configuration
public class ManageConfig {

    private Environment environment;

    public ManageConfig(@Autowired Environment environment) {
        this.environment = environment;
    }

    @Bean
    public ManageLogoutSuccessHandler manageLogoutSuccessHandler() {
        return new ManageLogoutSuccessHandler();
    }

    @Bean
    public Md5PasswordEncoder md5PasswordEncoder() {
        return new Md5PasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new AdminServiceImpl();
    }

    @Bean
    public IdService idService() {
        Integer soaIdx = Integer.valueOf(environment.getProperty("biz.soa.idx"));
        String soaIdxZooNodePath = environment.getProperty("biz.zookeeper.soaIdx.path");
        String zookeeperUrl = environment.getProperty("biz.zookeeper.url");
        return new IdService(soaIdx, soaIdxZooNodePath, zookeeperUrl);
    }

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        String driverClz = environment.getProperty("spring.datasource.driver-class-name");
        String username = environment.getProperty("spring.datasource.username");
        String password = environment.getProperty("spring.datasource.password");
        String url = environment.getProperty("spring.datasource.url");
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(driverClz);
        dataSource.setJdbcUrl(url);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setMaxPoolSize(100);
        dataSource.setMinPoolSize(50);
        dataSource.setInitialPoolSize(20);
        dataSource.setMaxIdleTime(300);
        dataSource.setIdleConnectionTestPeriod(60);
        dataSource.setAcquireIncrement(5);
        return dataSource;
    }

    //    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws PropertyVetoException {
        PersistenceUnitManager persistenceUnitManager = this.persistenceUnitManager();
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        jpaProperties.setProperty("hibernate.ejb.naming_strategy", "org.hibernate.cfg.DefaultComponentSafeNamingStrategy");
        jpaProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        jpaProperties.setProperty("hibernate.enable_lazy_load_no_trans", "true");
        jpaProperties.setProperty("hibernate.cache.use_query_cache", "false");
        jpaProperties.setProperty("hibernate.cache.use_second_level_cache", "false");
        jpaProperties.setProperty("hibernate.transaction.flush_before_completion", "true");
        localContainerEntityManagerFactoryBean.setPackagesToScan("com.biz");
        localContainerEntityManagerFactoryBean.setJpaProperties(jpaProperties);
        localContainerEntityManagerFactoryBean.setPersistenceUnitManager(persistenceUnitManager);
        localContainerEntityManagerFactoryBean.setPersistenceUnitName("biz");
        return localContainerEntityManagerFactoryBean;
    }

    //    @Bean
    public PersistenceUnitManager persistenceUnitManager() throws PropertyVetoException {
        DataSource dataSource = this.dataSource();
        MergingPersistenceUnitManager persistenceUnitManager = new MergingPersistenceUnitManager();
        persistenceUnitManager.setPersistenceXmlLocation("classpath:META-INF/persistence.xml");
        persistenceUnitManager.setDefaultDataSource(dataSource);
        return persistenceUnitManager;
    }

    //    @Bean
    public JpaTransactionManager transactionManager() throws PropertyVetoException {
        return new JpaTransactionManager((EntityManagerFactory) entityManagerFactory());
    }
}
