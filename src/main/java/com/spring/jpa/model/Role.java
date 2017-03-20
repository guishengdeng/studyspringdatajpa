package com.spring.jpa.model;

import javax.persistence.*;

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

     private Long id;

     private String name;

     private String description;
     @ManyToOne(cascade = CascadeType.ALL)
     private User user;
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

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}