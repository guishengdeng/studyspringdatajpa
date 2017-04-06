package com.spring.jpa.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.spring.jpa.security.GrantAuthorityImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User
 *
 * @author guisheng.deng
 * @date 2017年03月08日
 * @reviewer
 * @see
 */
@Entity
@Table(name="user")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","roles"})
public class User implements Serializable,UserDetails{
    private static final long serialVersionUID = 1702697991615493989L;
    @Id
    @Column(name="id")
    private Long id;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="email")
    private String email;
    @Column(name="sex")
    private String sex;
    @Column(name="age")
    private Integer age;
    @ManyToMany( cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)//之前是没有LAZY的    //一个用户可以有多个角色。
    @JoinTable(name="user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns =@JoinColumn(name="role_id"))
    private Set<Role> roles;
    @Transient
    private Set<Role> roleDirectory;

    public Set<Role> getRoleDirectory() {
        return roleDirectory;
    }

    public void setRoleDirectory(Set<Role> roleDirectory) {
        this.roleDirectory = roleDirectory;
    }

    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
        if (roles != null) {
            for (Role role : roles) {
                List<MenuItem> menuItems = role.getMenuItems();
                for(MenuItem menuItem:menuItems){
                    if (StringUtils.isNotBlank(menuItem.getSymbol())) {
                        String[] roleSymbol = menuItem.getSymbol().split(";");
                        for (String sybmol : roleSymbol) {
                            auths.add(new GrantAuthorityImpl(sybmol));
                        }
                    }
                }
                Set<Resource> resources = role.getResources();
                for (Resource resource : resources) {
                    if (StringUtils.isNotBlank(resource.getResourcename())) {
                        String[] roleSymbol = resource.getResourcename().split(";");
                        for (String sybmol : roleSymbol) {
                            auths.add(new GrantAuthorityImpl(sybmol));
                        }
                    }
                }
            }

        }
        return auths;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User() {
    }

}