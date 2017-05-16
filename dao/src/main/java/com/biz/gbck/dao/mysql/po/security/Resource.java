package com.biz.gbck.dao.mysql.po.security;

import com.biz.core.model.Identifiable;
import com.biz.support.jpa.po.BasePO;
import com.biz.gbck.enums.CommonStatusEnum;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "adm_resource")
public class Resource extends BasePO<Long> implements Identifiable<Long> {

    public final static int TYPE_MENU = 0;
    public final static int TYPE_URL = 1;
    private static final long serialVersionUID = 5242502895214313530L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50, nullable = false)
    @NotNull(message = "名字不能为空")
    @NotBlank(message = "名字不能为空")
    private String name;

    @Column(columnDefinition = "MEDIUMTEXT")
    @NotBlank(message = "权限不能为空")
    //^(((ROLE_[A-Z]+)|(OPT(_[A-Z]+)+));?)+$
    @NotNull(message = "权限不能为空")
    @Pattern(regexp = "(((ROLE_[A-Z]+)|(OPT(_[A-Z]+)+))(;|,)?)+", message = "无效的权限")
    private String symbol;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private MenuItem menuItem;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "adm_role_resource",
            joinColumns = {@JoinColumn(name = "resource_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            uniqueConstraints = {@UniqueConstraint(columnNames = {"resource_id", "role_id"})})
    private List<Role> roles;
    @Column
    @Enumerated(EnumType.STRING)
    private CommonStatusEnum status=CommonStatusEnum.ENABLE;
    public Resource() {

    }

    public Resource(Long id, String name, String symbol, String description, MenuItem menuItem, List<Role> roles) {
        setId(id);
        this.name = name;
        this.symbol = symbol;
        this.description = description;
        this.menuItem = menuItem;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    public CommonStatusEnum getStatus() {

        return status;
    }

    public void setStatus(CommonStatusEnum status) {

        this.status = status;
    }
    public void copy(Resource res) {
        this.name = res.name;
        this.description = res.description;
        this.symbol = res.symbol;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
