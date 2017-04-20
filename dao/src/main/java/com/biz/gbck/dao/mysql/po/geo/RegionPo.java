package com.biz.gbck.dao.mysql.po.geo;

import com.biz.gbck.model.geo.IArea;
import com.biz.gbck.model.geo.IRegion;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.List;

@SuppressWarnings("serial") @Entity @Table(name = "geo_region") public class RegionPo
    extends AbstractAreaWithCode implements IRegion {

    private Integer status;
    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy(value = "idx") @NotFound(action = NotFoundAction.IGNORE) private List<ProvincePo>
        provinces;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ProvincePo> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<ProvincePo> provinces) {
        this.provinces = provinces;
    }

    public Integer getParentId() {
        return null;
    }

    public int getLevel() {
        return IArea.LEVEL_REGION;
    }

    public IArea getParent() {
        return null;
    }

    public List getChildren() {
        return provinces;
    }
}
