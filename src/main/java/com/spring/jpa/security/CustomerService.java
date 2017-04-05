package com.spring.jpa.security;

import com.spring.jpa.model.Resource;
import com.spring.jpa.model.Role;
import com.spring.jpa.model.User;
import com.spring.jpa.security.accessdenied.LoginUserValidate;
import com.spring.jpa.service.RoleService;
import com.spring.jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

/**
 * CustomerService
 *
 * @author guisheng.deng
 * @date 2017年03月16日
 * @reviewer
 * @description
 * @see
 */
public class CustomerService implements UserDetailsService{
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Override

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         User user=null;
        if(userService.userList(username)!=null){
              user=userService.userList(username).get(0);
         }
         if(user==null)
             throw new UsernameNotFoundException("The user name " + username
                     + " can not be found!");
        Set<Role> roles=user.getRoles();
        //备注：在po类User里面已经做了权限验证
        user.setRoleDirectory(roles);
         return user;

    }
}