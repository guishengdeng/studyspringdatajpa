package com.biz.soa.org.transformer.geo;

import com.biz.gbck.common.model.geo.IArea;
import com.biz.gbck.dao.mysql.po.geo.AbstractAreaWithCode;
import com.biz.gbck.dao.mysql.po.geo.CityPo;
import com.biz.gbck.dao.mysql.po.geo.DistrictPo;
import com.biz.gbck.vo.geo.SimpleRegionVo;
import com.google.common.base.Function;

import java.io.Serializable;

/**
 * Created by defei on 3/28/16.
 */
public class RegionToSimpleRegionVo implements Function<Object, SimpleRegionVo>, Serializable {

    @Override public SimpleRegionVo apply(Object source) {

        if (source instanceof AbstractAreaWithCode) {
            AbstractAreaWithCode abstractAreaWithCode = (AbstractAreaWithCode) source;
            Integer level = IArea.LEVEL_PROVINCE;
            if (abstractAreaWithCode instanceof CityPo) {
                level = IArea.LEVEL_CITY;
            } else if (abstractAreaWithCode instanceof DistrictPo) {
                level = IArea.LEVEL_DISTRICT;
            }
            return new SimpleRegionVo(abstractAreaWithCode.getId(), abstractAreaWithCode.getName(),
                level);
        }
        return null;
    }

}