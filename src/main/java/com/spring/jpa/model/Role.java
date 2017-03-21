package com.spring.jpa.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Role
 *
 * @author guisheng.deng
 * @date 2017年03月18日
 * @reviewer
 * @description 用于映射数据库的中的role表
 * @see
 */
@Entity
@Table(name="role")
public class Role {
     @Id
     @Column(name="id")
     private Long id;
     @Column(name="name")
     private String name;
     @Column(name="descn")
     private String description;
    @ManyToMany(mappedBy = "roles",cascade = CascadeType.ALL)
    private Set<User> users;
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Role() {

    }


}