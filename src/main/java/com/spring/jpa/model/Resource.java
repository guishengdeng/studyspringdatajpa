package com.spring.jpa.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Resource
 *
 * @author guisheng.deng
 * @date 2017年03月23日
 * @reviewer
 * @description
 * @see
 */
@Entity
@Table(name="resource")
public class Resource {
    @Id
    @Column(name="id")
    private Long id;
    @Column(name="resourcename")
    private String resourcename;
    @Column(name="description")
    private String description;
    //mappedBy = "resources" 代表Role实体Resource实体之间的关系由Role实体类来维护（关系表也就是中间表）
    //如果在上面ManyToMany注解中使用mappedBy,就成单向的了.因为mappedBy出现的位置所在的类,
    // 这个类是被维护端,它只能被别人级联,不能去保存别人
    @ManyToMany(mappedBy = "resources", cascade = CascadeType.PERSIST)
    private Set<Role> role=new HashSet<Role>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResourcename() {
        return resourcename;
    }

    public void setResourcename(String resourcename) {
        this.resourcename = resourcename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }
}