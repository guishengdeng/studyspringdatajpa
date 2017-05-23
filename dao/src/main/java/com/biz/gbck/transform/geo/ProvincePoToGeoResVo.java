package com.biz.gbck.transform.geo;

import com.biz.gbck.dao.mysql.po.geo.ProvincePo;
import com.biz.gbck.vo.geo.GeoResVo;
import com.google.common.base.Function;

import java.io.Serializable;

/**
 * 转换省
 * Created by dylan on 17-1-24.
 */
public class ProvincePoToGeoResVo implements Function<ProvincePo,GeoResVo>,Serializable{
    @Override
    public GeoResVo apply(ProvincePo po) {
        GeoResVo vo=null;
        if(null != po){
            vo=new GeoResVo();
            if(null == po.getId()){
                return null;
            }
            vo.setId(po.getId().toString());
            vo.setName(po.getName());
        }
        return vo;
    }
}
