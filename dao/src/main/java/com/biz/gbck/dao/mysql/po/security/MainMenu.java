package com.biz.gbck.dao.mysql.po.security;

import com.biz.core.model.Identifiable;
import com.biz.gbck.dao.mysql.po.BasePo;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "adm_mainmenu")
public class MainMenu extends BasePo<Long> {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Integer code;

    @Column(length = 200)
    private String name;

    @Column
    private String description;

    @Column(length = 20)
    private String icon = "fa fa-list";

    @Column(length = 200)
    private String companyType;
    //mappedBy出现的位置所在的类,这个类是被维护端,它只能被别人级联,不能去保存别人
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "mainMenu")
    @OrderBy(value = "code")
    private List<MenuItem> menuItems;

    public MainMenu() {

    }

    public MainMenu(Long id, Integer code, String name, String description, String icon, String companyType, List<MenuItem> menuItems) {
        setId(id);
        this.code = code;
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.companyType = companyType;
        this.menuItems = menuItems;
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

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String toString() {
        return name;
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

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
