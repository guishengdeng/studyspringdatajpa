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

    private Long id;

    private String username;

    private String password;

    private String email;

    private String sex;

    private Integer age;
   /* @OneToMany( cascade = CascadeType.ALL)    //一个用户可以有多个角色。
    @JoinColumn(name = "user_id")
    private Set<Role> roles=new HashSet<Role>();
    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }*/

    private String roles;
    public String getRoles() {
        return roles;
    }

    public void setRole(String roles) {
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
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", roles='" + roles + '\'' +
                '}';
    }

    public User() {
    }
}