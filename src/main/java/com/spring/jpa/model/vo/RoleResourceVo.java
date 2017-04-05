package com.spring.jpa.model.vo;

import java.util.List;

/**
 * RoleResourceVo
 *
 * @author guisheng.deng
 * @date 2017年03月24日
 * @reviewer
 * @description
 * @see
 */
public class RoleResourceVo {
    private String role_id;
    private String name;
    private String description;
    private String [] id;//这是资源类（Resource类里的id）
    private Long []  menuitem_id;//这是子目录类（MenuItem类里的id）

    public Long [] getMenuitem_id() {
        return menuitem_id;
    }

    public void setMenuitem_id(Long [] menuitem_id) {
        this.menuitem_id = menuitem_id;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
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

    public String[] getId() {
        return id;
    }

    public void setId(String[] id) {
        this.id = id;
    }
}