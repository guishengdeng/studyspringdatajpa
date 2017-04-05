package com.spring.jpa.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * MenuItem
 *
 * @author guisheng.deng
 * @date 2017年03月31日
 * @reviewer
 * @description
 * @see
 */
@Entity
@Table(name="menuitem")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","resources","roles"})
public class MenuItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long menuitem_id;
    @Column(length=50,nullable=false)
    private String name;
    @Column(length=200)
    private String link;//目录里的链接地址
    @Column(length=255)
    private String description;
    @Column(length=255)
    private String symbol;
    @ManyToOne(cascade = CascadeType.ALL)
    private MainMenu mainMenu;
    @OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="menuItem")
    private List<Resource> resources;
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name = "role_menuitem",
            joinColumns = @JoinColumn(name = "menuitem_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
    public MenuItem() {

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

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public void setMainMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}