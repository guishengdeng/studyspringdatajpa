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
        //查询用户表时，会加载其关联的role角色表（role类）
         Set<Role> roles=user.getRoles();
         Set<GrantedAuthority> grantedAuthoritySet=new HashSet<GrantedAuthority>();
         //增加access中配置的权限，实际上这里就是让所有登陆用户都具备该权限，
         //而真正的资源权限验证留给AccessDecisionManager来决定
         if(roles!=null){
             for(Role role:roles){
                 //grantedAuthorityList.add(new GrantAuthorityImpl(role.getName()));
                 //Role currentRole=roleService.getRoleByName(role.getName());
                 Set<Resource> set=role.getResources();
                 if(set!=null){
                     for(Resource resource:set){
                         //这个地方到时候还要改，因为到时候在资源表中，用户在做修改时，可能会对资源进行;的增添
                         String [] splitStr=resource.getResourcename().split(";");
                         if(splitStr.length>=2){
                             for(String item:splitStr){
                                 grantedAuthoritySet.add(new GrantAuthorityImpl(item));
                             }
                         }else{
                             grantedAuthoritySet.add(new GrantAuthorityImpl(resource.getResourcename()));
                         }
                     }
                 }
             }
         }

        //验证用户名和密码是否正确，以及是否权限正确
        LoginUserValidate userValidate=new LoginUserValidate(user.getUsername(),user.getPassword(),
                grantedAuthoritySet,true,true,
                true,true,roles);
         return userValidate;

    }
}