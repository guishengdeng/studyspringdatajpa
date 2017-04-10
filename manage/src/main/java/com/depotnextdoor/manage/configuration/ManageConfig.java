package com.depotnextdoor.manage.configuration;

import com.depotnextdoor.manage.security.ManageLogoutSuccessHandler;

/**
 * @author david-liu
 * @date 2017年04月08日
 * @reviewer
 */
//@Configuration
public class ManageConfig {
//    @Bean
    public ManageLogoutSuccessHandler manageLogoutSuccessHandler() {
        return new ManageLogoutSuccessHandler();
    }
}
