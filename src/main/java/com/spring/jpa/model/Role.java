package com.spring.jpa.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
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
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","resources","users"})
public class Role implements Serializable{
     @Id
     @Column(name="id")
     private Long role_id;
     @Column(name="name")
     private String name;
     @Column(name="descn")
     private String description;
    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<User> users;
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name="role_resource", joinColumns = @JoinColumn(name="role_id")
            ,inverseJoinColumns = @JoinColumn(name="resource_id"))

    private Set<Resource> resources=new HashSet<Resource>();
    public Set<Resource> getResources() {
        return resources;
    }

    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }



    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
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