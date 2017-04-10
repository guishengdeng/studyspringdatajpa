package com.depotnextdoor.manage.configuration;

//
///**
// * Spring Security Configuration
// *
// * @author david-liu
// * @date 2017年04月08日
// * @reviewer
// */
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private ManageLogoutSuccessHandler manageLogoutSuccessHandler;
//
//    private Md5PasswordEncoder md5PasswordEncoder;
//
//    private UserDetailsService adminService;
//
//    public SecurityConfig(@Autowired ManageLogoutSuccessHandler manageLogoutSuccessHandler, @Autowired Md5PasswordEncoder md5PasswordEncoder, @Autowired UserDetailsService adminServiceImpl) {
//        this.manageLogoutSuccessHandler = manageLogoutSuccessHandler;
//        this.md5PasswordEncoder = md5PasswordEncoder;
//        this.adminService = adminServiceImpl;
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/login").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
////        http.csrf().disable();
////        http.authorizeRequests()
////                .antMatchers("/favicon.ico", "/manifest*", "/login", "/msg", "/static-resources/**").permitAll()
////                .antMatchers("/**").authenticated()
////                .and().exceptionHandling().accessDeniedPage("/WEB-INF/views/manage/accessDenied.jsp");
////        http.formLogin()
////                .usernameParameter("username")
////                .passwordParameter("password")
////                .loginPage("/login")
////                .loginProcessingUrl("/login/check")
////                .failureUrl("/login?error=true")
////                .defaultSuccessUrl("/welcome");
////        http.logout().logoutUrl("/logout").invalidateHttpSession(true).logoutSuccessHandler(manageLogoutSuccessHandler).and()
////                .sessionManagement().maximumSessions(1);
//
//    }
//
//
//
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(adminService).passwordEncoder(md5PasswordEncoder);
////    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
//    }
//}
