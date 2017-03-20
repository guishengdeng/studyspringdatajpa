package com.spring.jpa.security;

import com.spring.jpa.model.User;
import com.spring.jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

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
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         User user=null;
         if(!userService.userList(username).isEmpty()){
              user=userService.userList(username).get(0);
         }
         if(user==null)
             throw new UsernameNotFoundException("The user name " + username
                     + " can not be found!");
         String [] roles=user.getRoles().split(",");

         List<GrantedAuthority> grantedAuthorityList=new ArrayList<GrantedAuthority>();
         //增加access中配置的权限，实际上这里就是让所有登陆用户都具备该权限，
         //而真正的资源权限验证留给AccessDecisionManager来决定
         for(String role :roles)
             grantedAuthorityList.add(new GrantAuthorityImpl(role));
        //验证用户名和密码是否正确，以及是否权限正确

        return new org.springframework.security.core.userdetails.User
                (user.getUsername(),user.getPassword(),true,
                        true,true,
                        true,grantedAuthorityList);
    }
}