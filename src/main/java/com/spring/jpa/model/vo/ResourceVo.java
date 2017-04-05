package com.spring.jpa.model.vo;

/**
 * ResourceVo
 *
 * @author guisheng.deng
 * @date 2017年03月24日
 * @reviewer
 * @description
 * @see
 */
public class ResourceVo {

    private Long id;

    private String resourcename;

    private String description;

    private String linkedaddress;

    private Long menuitem_id;

    public Long getMenuitem_id() {
        return menuitem_id;
    }

    public void setMenuitem_id(Long menuitem_id) {
        this.menuitem_id = menuitem_id;
    }

    public String getLinkedaddress() {
        return linkedaddress;
    }

    public void setLinkedaddress(String linkedaddress) {
        this.linkedaddress = linkedaddress;
    }

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
}