package com.biz.gbck.dao.mysql.po.security;

import com.biz.core.model.Identifiable;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "adm_mainmenu")
public class MainMenu implements Identifiable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Integer code;

    @Column(length = 200)
    private String name;

    @Column(length = 200)
    private String nameEn;

    @Column
    private String description;

    @Column(length = 20)
    private String icon = "fa fa-list";

    @Column(length = 200)
    private String companyType;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "mainMenu")
    @OrderBy(value = "code")
    private List<MenuItem> menuItems;

    public MainMenu() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public Integer getCode() {
        return code;
    }

    public Long getId() {
        return id;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String toString() {
        return name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }
}
