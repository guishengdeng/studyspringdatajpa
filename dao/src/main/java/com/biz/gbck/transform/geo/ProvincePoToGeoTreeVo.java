package com.biz.gbck.transform.geo;

import com.biz.gbck.dao.mysql.po.geo.ProvincePo;
import com.biz.gbck.vo.geo.GeoTreeVo;
import com.google.common.base.Function;

import java.io.Serializable;

/**
 */
public class ProvincePoToGeoTreeVo implements Function<ProvincePo, GeoTreeVo>, Serializable {
    @Override public GeoTreeVo apply(ProvincePo po) {
        if (po == null)
            return null;
        GeoTreeVo vo = new GeoTreeVo();
        vo.setId(po.getId().toString());
        if (po.getParentId() == null) {
            vo.setParent("8");
        } else {
            vo.setParent(po.getParentId().toString());
        }
        vo.setText(po.getName());
        vo.setState(po.getStatus());
        return vo;
    }
}
