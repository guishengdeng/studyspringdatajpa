package com.biz.gbck.dao.mysql.po.security;

import com.biz.core.model.Identifiable;
import com.biz.support.jpa.po.BasePO;
import com.biz.gbck.dao.mysql.po.demo.CatPO;
import com.biz.gbck.enums.CommonStatusEnum;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "adm_menuitem")
public class MenuItem extends BasePO<Long> implements Identifiable<Long> {

    private static final long serialVersionUID = -5767627457894527720L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    @Column
    private Integer code;

    @Column(length = 200, nullable = false)
    @NotNull(message = "名字不能为空")
    private String name;

    @Column(length = 20)
    @NotNull(message = "图标不能为空")
    @NotBlank(message = "图标不能为空")
    private String icon = "fa fa-list";

    @Column(length = 200)
    @NotNull(message = "连接地址不能为空")
    @NotBlank(message = "连接地址不能为空")
    private String link;

    @Column
    private String description;

    @Column(columnDefinition = "MEDIUMTEXT")
    @NotBlank(message = "权限不能为空")
    //^(((ROLE_[A-Z]+)|(OPT(_[A-Z]+)+));?)+$
    @NotNull(message = "权限不能为空")
    @Pattern(regexp = "(((ROLE_[A-Z]+)|(OPT(_[A-Z]+)+));?)+", message = "无效的权限")
    private String symbol;

    @ManyToOne(fetch = FetchType.LAZY)
    private MainMenu mainMenu;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "menuItem")
    @Where(clause = "status='ENABLE'")
    private List<Resource> resources;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "adm_role_menuitem",
            joinColumns = {@JoinColumn(name = "menuitem_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            uniqueConstraints = {@UniqueConstraint(columnNames = {"menuitem_id", "role_id"})})
    private List<Role> roles;
    @Column
    @Enumerated(EnumType.STRING)
    private CommonStatusEnum status=CommonStatusEnum.ENABLE;
    public MenuItem() {

    }

    public MenuItem(Long id, Integer code, String name, String icon, String link, String description, String symbol, MainMenu mainMenu, List<Resource> resources, List<Role> roles) {
        setId(id);
        this.code = code;
        this.name = name;
        this.icon = icon;
        this.link = link;
        this.description = description;
        this.symbol = symbol;
        this.mainMenu = mainMenu;
        this.resources = resources;
        this.roles = roles;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public void setMainMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
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
    public CommonStatusEnum getStatus() {

        return status;
    }

    /**
     * {@linkplain CatPO#status}
     */
    public void setStatus(CommonStatusEnum status) {

        this.status = status;
    }
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

}
