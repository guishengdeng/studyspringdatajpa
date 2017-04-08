package com.depotnextdoor.dao.rdb.po.security;

import com.depotnextdoor.core.model.Identifiable;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "adm_menuitem")
public class MenuItem implements Identifiable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Integer code;

    @Column(length = 200, nullable = false)
    private String name;

    @Column(length = 20)
    private String icon = "fa fa-list";

    @Column(length = 200)
    private String link;

    @Column
    private String description;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String symbol;

    @ManyToOne(fetch = FetchType.LAZY)
    private MainMenu mainMenu;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "menuItem")
    private List<Resource> resources;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "adm_role_menuitem",
            joinColumns = {@JoinColumn(name = "menuitem_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            uniqueConstraints = {@UniqueConstraint(columnNames = {"menuitem_id", "role_id"})})
    private List<Role> roles;

    public MenuItem() {

    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@linkplain MenuItem#icon}
     */
    public String getIcon() {

        return icon;
    }

    /**
     * {@linkplain MenuItem#icon}
     */
    public void setIcon(String icon) {

        this.icon = icon;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public Long getId() {
        return id;
    }

    public void setMainMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String toString() {
        return name;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public String getSymbol() {
        return symbol != null ? symbol.replaceAll(";", ",") : null;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void copy(MenuItem item) {
        this.code = item.code;
        this.name = item.name;
        this.link = item.link;
        this.description = item.description;
        this.symbol = item.symbol;
        this.icon = item.icon;
    }

}
