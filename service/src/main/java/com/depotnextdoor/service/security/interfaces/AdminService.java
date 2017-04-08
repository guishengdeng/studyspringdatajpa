package com.depotnextdoor.service.security.interfaces;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author david-liu
 * @date 2017年04月07日
 * @reviewer
 */
public interface AdminService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
