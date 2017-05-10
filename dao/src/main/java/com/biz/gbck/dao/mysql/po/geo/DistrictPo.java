package com.biz.gbck.dao.mysql.po.geo;

import com.biz.gbck.model.geo.IArea;
import com.biz.gbck.model.geo.IDistrict;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.List;

/**
 * 地区
 *
 * @author defei
 */
@SuppressWarnings("serial") @Entity @Table(name = "geo_district") public class DistrictPo
    extends AbstractAreaWithCode implements IDistrict {
    private Integer status;
    @JsonIgnore
    @ManyToOne(optional = true) @JoinColumn(name = "cityId") private CityPo city;

    @JsonIgnore
    @Column(nullable=false, name = "provinceid")
    private Integer provinceId;

    @JsonIgnore
    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy(value = "idx") @NotFound(action = NotFoundAction.IGNORE) private List<BusinessPo>
        businesses;

    public CityPo getCity() {
        return city;
    }

    public void setCity(CityPo city) {
        this.city = city;
    }

    public List<BusinessPo> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(List<BusinessPo> businesses) {
        this.businesses = businesses;
    }

    @JsonIgnore
    public Integer getParentId() {
        if (city != null) {
            return city.getId();
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
        return IArea.LEVEL_DISTRICT;
    }

    @JsonIgnore
    public CityPo getParent() {
        return city;
    }

    @JsonIgnore
    public List getChildren() {
        return businesses;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

}
