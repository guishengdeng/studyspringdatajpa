package com.biz.manage.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private Md5PasswordEncoder md5PasswordEncoder;

    private UserDetailsService userDetailsService;

    public WebSecurityConfig(@Autowired UserDetailsService adminServiceImpl, @Autowired Md5PasswordEncoder md5PasswordEncoder) {
        this.md5PasswordEncoder = md5PasswordEncoder;
        this.userDetailsService = adminServiceImpl;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //        http
        //                .authorizeRequests()
        //                .antMatchers("/favicon.ico", "/manifest*", "/login", "/msg", "/static-resource/**").permitAll()
        //                .antMatchers("/**").authenticated()
        //                .and().exceptionHandling().accessDeniedPage("/WEB-INF/views/manage/accessDenied.jsp")
        //                .and()
        //                .formLogin()
        //                .loginPage("/login")
        //                .usernameParameter("username")
        //                .passwordParameter("password")
        //                .defaultSuccessUrl("/home")
        //                .permitAll()
        //                .and()
        //                .logout()
        //                .permitAll();
        http
                .authorizeRequests()
                .antMatchers("/static-resource/**", "/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedPage("/WEB-INF/views/manage/accessDenied.jsp")
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
                .and()
                .logout()
                .permitAll();
        http.csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(md5PasswordEncoder);
        authenticationProvider.setUserDetailsService(userDetailsService);
        ReflectionSaltSource saltSource = new ReflectionSaltSource();
        saltSource.setUserPropertyToUse("username");
        authenticationProvider.setSaltSource(saltSource);
        auth.authenticationProvider(authenticationProvider);
    }
}
