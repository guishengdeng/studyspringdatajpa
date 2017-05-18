package com.biz.gbck.transform.geo;

import com.biz.gbck.dao.mysql.po.geo.DistrictPo;
import com.biz.gbck.vo.geo.GeoTreeVo;
import com.google.common.base.Function;

import java.io.Serializable;


public class DistrictPoToGeoTreeVo implements Function<DistrictPo, GeoTreeVo>, Serializable {
    @Override public GeoTreeVo apply(DistrictPo po) {
        if (po == null)
            return null;
        GeoTreeVo vo = new GeoTreeVo();
        vo.setId(po.getId().toString());
        vo.setParent(po.getParentId().toString());
        vo.setText(po.getName());
        vo.setState(po.getStatus());
        return vo;
    }
}
