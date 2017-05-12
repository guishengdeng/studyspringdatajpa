package com.biz.gbck.transform.geo;


import com.biz.gbck.dao.redis.ro.geo.DistrictRo;
import com.biz.gbck.vo.common.response.DistrictResponseVo;
import com.google.common.base.Function;

/**
 * 将区/县ro转换为vo
 * Created by maoyikun on 16-12-20.
 */
public class DistrictRo2DistrictResponseVo implements Function<DistrictRo, DistrictResponseVo> {
    @Override
    public DistrictResponseVo apply(DistrictRo input) {
        if(input == null){
            return null;
        }
        DistrictResponseVo resp = (DistrictResponseVo) new AbstractAreaRo2AbstractResponseVo(new DistrictResponseVo()).apply(input);
        resp.setProvinceId(input.getProvinceId());
        resp.setCityId(input.getCityId());
        return resp;
    }
}
