package com.biz.gbck.dao.redis.ro.geo;


import com.biz.gbck.dao.mysql.po.geo.ProvincePo;
import com.biz.redis.annotation.Ro;
import com.biz.redis.annotation.RoSortedSet;

/**
 * @author shenjiahao
 * @since 2016年9月1日
 */
@Ro(key = "geo:province")
@RoSortedSet(key = "list", score = "id")
public class ProvinceRo extends AbstractAreaRo {
    /*
     * 区域id
     */
    private Integer regionId;

    private Integer status;

    public ProvinceRo() {}

    public ProvinceRo(ProvincePo po) {
        this.setRegionId(po.getRegion() == null ? 0 : po.getRegion().getId());
        this.status = po.getStatus();

    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


}

