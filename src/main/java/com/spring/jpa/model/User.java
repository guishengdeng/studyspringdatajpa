package com.spring.jpa.model;

import javax.persistence.*;
import java.util.HashSet;
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
public class User {
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
    @ManyToMany( cascade = CascadeType.ALL)    //一个用户可以有多个角色。
    @JoinTable(name="user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns =@JoinColumn(name="role_id"))
    private Set<Role> roles;
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



    public User() {
    }
}