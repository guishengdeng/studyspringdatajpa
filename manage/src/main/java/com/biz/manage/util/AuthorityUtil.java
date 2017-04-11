package com.biz.manage.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 验证工具类
 *
 * @author defei
 * @date 2017年04月06日
 * @reviewer
 */
public class AuthorityUtil {

    private static final Logger logger = LoggerFactory.getLogger(AuthorityUtil.class);

    public static String getLoginUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal != null) {
            if (principal instanceof UserDetails) {
                return ((UserDetails) principal).getUsername();
            } else {
                return principal.toString();
            }
        } else {
            return null;
        }
    }

    /**
     * 获取当前登录用户
     *
     * @return
     * @author defei
     * @date 2015-4-13
     */
    // TODO 切换到真实返回用户
    public static Object getLoginUser() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


    /**
     * 判断当前用户是否拥有角色
     *
     * @param roleName 角色名称
     * @return
     * @author defei
     * @date 2015-4-13
     */
    // TODO 切换到真实变量类型
    public static boolean hasRole(String roleName) {
        if (roleName == null) {
            logger.warn("roleName is null");
            return false;
        }

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        /*
        if (principal == null || !(principal instanceof Admin)) {
            return false;
        }

        List<Role> roleList = ((Admin) principal).getRoles();
        if (roleList == null || roleList.isEmpty()) {
            return false;
        }

        for (Role role : roleList) {
            if (role != null && roleName.equals(role.getName())) {
                return true;
            }
        }
        */

        return false;
    }

    /**
     * 判断用户是否拥有这个权限
     */

    // TODO 切换到真实变量类型
    public static boolean hasAuthentication(/*Admin admin*/ Object admin, String authenticationName){
        if (authenticationName == null) {
            logger.warn("roleName is null");
            return false;
        }

        if (admin == null) {
            return false;
        }

        /*
        Collection<GrantedAuthority> authorities = admin.getAuthorities();
        if (authorities == null || authorities.isEmpty()) {
            return false;
        }

        for (GrantedAuthority authority : authorities) {
            if (authority != null && authenticationName.equals(authority.getAuthority())) {
                return true;
            }
        }
        */

        return false;
    }

}
