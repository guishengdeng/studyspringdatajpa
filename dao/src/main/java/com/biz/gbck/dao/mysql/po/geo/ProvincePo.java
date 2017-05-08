package com.biz.gbck.dao.mysql.po.geo;

import com.biz.gbck.common.model.geo.IArea;
import com.biz.gbck.common.model.geo.IProvince;
import com.biz.gbck.dao.mysql.po.org.PartnerPo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.List;

/**
 * 省
 *
 * @author defei
 */
@SuppressWarnings("serial") @Entity @Table(name = "geo_province") public class ProvincePo
    extends AbstractAreaWithCode implements IProvince {
    private Integer status;
    @JsonIgnore
    @ManyToOne(optional = true) @JoinColumn(name = "regionId") private RegionPo region;

    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy(value = "idx") @NotFound(action = NotFoundAction.IGNORE) private List<CityPo> cities;


    @OneToMany
    private List<PartnerPo> partnerPos;


    public List<CityPo> getCities() {
        return cities;
    }

    public void setCities(List<CityPo> cities) {
        this.cities = cities;
    }

    public RegionPo getRegion() {
        return region;
    }

    public void setRegion(RegionPo region) {
        this.region = region;
    }

    @JsonIgnore
    public Integer getParentId() {
        if (region != null) {
            return region.getId();
        } else
            return null;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public int getLevel() {
        return IArea.LEVEL_PROVINCE;
    }

    @JsonIgnore
    public RegionPo getParent() {
        return region;
    }

    @JsonIgnore
    public List getChildren() {
        return cities;
    }

    public List<PartnerPo> getPartnerPos() {
        return partnerPos;
    }

    public void setPartnerPos(List<PartnerPo> partnerPos) {
        this.partnerPos = partnerPos;
    }
}
