package com.biz.gbck.dao.mysql.po.org;

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


    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "warehouseId")
    private WarehousePo warehouse;


    @OneToMany(mappedBy = "platform", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PartnerPo> partners;

    /**
     * 手机号
     */
    private String mobile;


    public ProvincePo getProvince() {
        return province;
    }

    public void setProvince(ProvincePo province) {
        this.province = province;
    }

    public WarehousePo getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(WarehousePo warehouse) {
        this.warehouse = warehouse;
    }

    public List<PartnerPo> getPartners() {
        return partners;
    }

    public void setPartners(List<PartnerPo> partners) {
        this.partners = partners;
    }
}
