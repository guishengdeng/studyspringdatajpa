package com.biz.gbck.dao.mysql.po.org;

import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.enums.org.CompanyLevel;
import com.biz.support.jpa.po.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * 隔壁仓库、省公司(平台公司)、合伙人基类
 *
 * @author: liubin
 * @date 4/20/17 09:17
 */
@Entity
@Table(name = "org_company")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Company extends BaseEntity {

    /**
     * 公司名称
     */
    @Column(length = 40) private String name;

    /**
     * 公司描述
     */
    @Column(columnDefinition = "MEDIUMTEXT") private String description;


    @Column
    @Enumerated(EnumType.STRING)
    private CommonStatusEnum status;

    @Enumerated(value = EnumType.STRING)
    @Column
    private CompanyLevel companyLevel;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CompanyGroupPo> parentGroup;


    @ManyToOne
    @JoinColumn(name = "child_group_id")
    private CompanyGroupPo childGroup;




}
