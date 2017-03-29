package com.spring.jpa.security.accessdenied;

import com.spring.jpa.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * LoginUserValidate
 *
 * @author guisheng.deng
 * @date 2017年03月28日
 * @reviewer
 * @description
 * @see
 */
public class LoginUserValidate implements UserDetails {
    private  String password;
    private final String username;
    private final Set<GrantedAuthority> authorities;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;
    //添加我自己的成员变量
    private Set<Role> roleDirectory;
    public Set<Role> getRoleDirectory() {
        return roleDirectory;
    }
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }

    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    public boolean isEnabled() {
        return this.enabled;
    }
    public LoginUserValidate(String username,String password, Set<GrantedAuthority> authorities,
                             boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired,
                             boolean enabled,Set<Role> roleDirectory) {
        this.password=password;
        this.username = username;
        this.authorities = authorities;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        this.roleDirectory=roleDirectory;
    }


    /*public LoginUserValidate() {

    }*/
}