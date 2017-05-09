package com.biz.gbck.dao.mysql.po.geo;

import com.biz.gbck.model.geo.IArea;
import com.biz.gbck.model.geo.ICity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.List;
import java.util.Set;

/**
 * 城市
 *
 * @author defei
 */
@SuppressWarnings("serial") @Entity @Table(name = "geo_city") public class CityPo
    extends AbstractAreaWithCode implements ICity {

    private Integer status;
    @JsonIgnore
    @ManyToOne(optional = true) @JoinColumn(name = "provinceId") private ProvincePo
        province;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy(value = "idx") @NotFound(action = NotFoundAction.IGNORE) private List<DistrictPo>
        districts;

//    @ManyToMany(fetch = FetchType.LAZY) @JoinTable(name = "org_company_city",
//        joinColumns = {@JoinColumn(name = "city_id", referencedColumnName = "id")},
//        inverseJoinColumns = {@JoinColumn(name = "company_id", referencedColumnName = "id")},
//        uniqueConstraints = {@UniqueConstraint(columnNames = {"city_id", "company_id"})})
//    private Set<CompanyPo> companies;

    public CityPo() {

    }

    public CityPo(Integer id) {
        this.setId(id);
    }

    public ProvincePo getProvince() {
        return province;
    }

    public void setProvince(ProvincePo province) {
        this.province = province;
    }


    public List<DistrictPo> getDistricts() {
        return districts;
    }

    public void setDistricts(List<DistrictPo> districts) {
        this.districts = districts;
    }

    @JsonIgnore
    public Integer getParentId() {
        if (province != null) {
            return province.getId();
        } else
            return null;
    }

    public int getLevel() {
        return IArea.LEVEL_CITY;
    }

    @JsonIgnore
    public ProvincePo getParent() {
        return province;
    }

    @JsonIgnore
    public List getChildren() {
        return districts;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
