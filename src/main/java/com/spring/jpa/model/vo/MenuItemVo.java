package com.spring.jpa.model.vo;

/**
 * MenuItemVo
 *
 * @author guisheng.deng
 * @date 2017年04月02日
 * @reviewer
 * @description
 * @see
 */
public class MenuItemVo {
   private Long menuitem_id;
   private String name;
   private String link;
   private String description;
   private String symbol;
   private Long  id;//这是MainMenu类里的id
   private Long [] role_id;

    public MenuItemVo() {
    }

    public Long getMenuitem_id() {
        return menuitem_id;
    }

    public void setMenuitem_id(Long menuitem_id) {
        this.menuitem_id = menuitem_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long[] getRole_id() {
        return role_id;
    }

    public void setRole_id(Long[] role_id) {
        this.role_id = role_id;
    }
}