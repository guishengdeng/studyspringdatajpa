package com.depotnextdoor.manage.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author david-liu
 * @date 2017年04月10日
 * @reviewer
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/favicon.ico", "/manifest*", "/login", "/msg", "/static-resources/**").permitAll()
//                .antMatchers("/**").authenticated()
//                .and().exceptionHandling().accessDeniedPage("/WEB-INF/views/manage/accessDenied.jsp");
        http
                .authorizeRequests()
                .antMatchers("/favicon.ico", "/manifest*", "/login", "/msg", "/static-resource/*/*").permitAll()
                .antMatchers("/**").authenticated()
                .and().exceptionHandling().accessDeniedPage("/WEB-INF/views/manage/accessDenied.jsp")
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
        http.csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }


}
