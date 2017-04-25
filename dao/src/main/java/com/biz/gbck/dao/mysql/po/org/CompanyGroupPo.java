package com.biz.gbck.dao.mysql.po.org;

import com.biz.gbck.enums.org.CompanyLevel;
import com.biz.support.jpa.po.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.List;

/**
 * 客户组
 *
 * @author: liubin
 * @date 4/20/17 09:28
 */
@Entity
@Table(name = "org_group")
public class CompanyGroupPo extends BaseEntity {

    /**
     * 客户组编码
     */
    private String code;

    /**
     * 客户组名称
     */
    private String name;

    /**
     * 上级
     */
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Company parent;

    /**
     * 下级
     */
    @OneToMany(mappedBy = "childGroup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Company> children;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
