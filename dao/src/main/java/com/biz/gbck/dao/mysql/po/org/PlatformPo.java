package com.biz.gbck.dao.mysql.po.org;

import com.biz.gbck.dao.mysql.po.geo.ProvincePo;
import com.biz.gbck.dao.mysql.po.security.Admin;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.List;

/**
 * 省公司(平台公司)
 *
 * @author: liubin
 * @date 4/20/17 09:20
 */
@Entity
@Table(name = "org_platform")
public class PlatformPo extends Company{

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "provinceId")
    private ProvincePo province;


    @ManyToMany(fetch = FetchType.LAZY) @JoinTable(name = "org_admin_platform",
            joinColumns = {@JoinColumn(name = "platform_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "username", referencedColumnName = "username")},
            uniqueConstraints = {@UniqueConstraint(columnNames = {"platform_id", "username"})})
    private List<Admin> admins;



}
