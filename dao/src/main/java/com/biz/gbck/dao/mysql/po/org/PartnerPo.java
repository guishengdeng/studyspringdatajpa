package com.biz.gbck.dao.mysql.po.org;

import com.biz.gbck.dao.mysql.po.geo.CityPo;
import com.biz.gbck.dao.mysql.po.geo.ProvincePo;
import com.biz.gbck.dao.mysql.po.security.Admin;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.List;

/**
 * 合伙人(合作者)
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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plateformId")
    private PlatformPo platform;

    @OneToMany(mappedBy = "partner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ShopPo> shopPos;


    public ProvincePo getProvince() {
        return province;
    }

    public void setProvince(ProvincePo province) {
        this.province = province;
    }

    public CityPo getCity() {
        return city;
    }

    public void setCity(CityPo city) {
        this.city = city;
    }

    public List<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(List<Admin> admins) {
        this.admins = admins;
    }

    public PlatformPo getPlatform() {
        return platform;
    }

    public void setPlatform(PlatformPo platform) {
        this.platform = platform;
    }

    public List<ShopPo> getShopPos() {
        return shopPos;
    }

    public void setShopPos(List<ShopPo> shopPos) {
        this.shopPos = shopPos;
    }
}
