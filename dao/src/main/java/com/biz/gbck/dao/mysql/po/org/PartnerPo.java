package com.biz.gbck.dao.mysql.po.org;

import com.biz.gbck.dao.mysql.po.geo.CityPo;
import com.biz.gbck.dao.mysql.po.geo.ProvincePo;
import com.biz.gbck.dao.mysql.po.security.Admin;
import com.biz.gbck.enums.org.CompanyLevel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.List;

/**
 * 合伙人
 *
 * @author: liubin
 * @date 4/20/17 09:19
 */
@Entity
@Table(name = "org_partner")
public class PartnerPo extends Company{


    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "provinceId")
    private ProvincePo province;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "cityId")
    private CityPo city;


    @ManyToMany(fetch = FetchType.LAZY) @JoinTable(name = "org_admin_partner",
            joinColumns = {@JoinColumn(name = "partner_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "username", referencedColumnName = "username")},
            uniqueConstraints = {@UniqueConstraint(columnNames = {"partner_id", "username"})})
    private List<Admin> admins;





}
