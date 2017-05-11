package com.biz.gbck.dao.mysql.po.security;

import com.biz.support.jpa.po.BasePO;
import com.biz.gbck.enums.CommonStatusEnum;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "adm_mainmenu")
public class MainMenu extends BasePO<Long> {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Integer code;

    @Column(length = 200)
    @NotNull(message = "名称不能为空")
    private String name;

    @Column
    private String description;

    @Column(length = 20)
    @NotNull(message = "图标不能为空")
    @NotBlank(message = "图标不能为空")
    private String icon = "fa fa-list";

    @Column(length = 200)
    private String companyType;
    //mappedBy出现的位置所在的类,这个类是被维护端,它只能被别人级联,不能去保存别人
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "mainMenu")
    @OrderBy(value = "code")
    /**
     * Where这个条件的意思是进行(MainMenu与MenuItem)关联查询的时候,我只把MenuItem中status = ENABLE的查询出来.
     */
    @Where(clause = "status='ENABLE'")
    private List<MenuItem> menuItems;

    @Column
    @Enumerated(EnumType.STRING)
    private CommonStatusEnum status=CommonStatusEnum.ENABLE;
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
    public CommonStatusEnum getStatus() {

        return status;
    }

    public void setStatus(CommonStatusEnum status) {

        this.status = status;
    }
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
