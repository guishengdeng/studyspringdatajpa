package com.biz.gbck.transform.geo;

import com.biz.gbck.dao.mysql.po.geo.RegionPo;
import com.biz.gbck.vo.geo.GeoTreeVo;
import com.google.common.base.Function;

import java.io.Serializable;


public class RegionPoToGeoTreeVo implements Function<RegionPo, GeoTreeVo>, Serializable {
    @Override public GeoTreeVo apply(RegionPo po) {
        if (po == null)
            return null;
        GeoTreeVo vo = new GeoTreeVo();
        vo.setParent("1_global");
        vo.setId(po.getId().toString());
        vo.setText(po.getName());
        vo.setState(po.getStatus());
        return vo;
    }
}
