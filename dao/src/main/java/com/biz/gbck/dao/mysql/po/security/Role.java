package com.biz.gbck.dao.mysql.po.security;

import com.biz.core.model.Identifiable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 * 管理员角色
 *
 * @author david-liu
 * @date 2017年04月07日
 * @reviewer
 */
@Entity
@Table(name = "adm_role")
public class Role implements Identifiable<Long>, Serializable {

    private static final long serialVersionUID = 2033192329314085870L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "adm_role_menuitem",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "menuitem_id", referencedColumnName = "id")},
            uniqueConstraints = {@UniqueConstraint(columnNames = {"role_id", "menuitem_id"})})
    private List<MenuItem> menuItems;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "adm_role_resource",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "resource_id", referencedColumnName = "id")},
            uniqueConstraints = {@UniqueConstraint(columnNames = {"role_id", "resource_id"})})
    private List<Resource> resources;

    public void addMenuItem(MenuItem menuItem) {
        if (menuItems == null) {
            menuItems = new ArrayList<>();
        }
        if (!menuItems.contains(menuItem)) {
            menuItems.add(menuItem);
        }
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
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

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
}
