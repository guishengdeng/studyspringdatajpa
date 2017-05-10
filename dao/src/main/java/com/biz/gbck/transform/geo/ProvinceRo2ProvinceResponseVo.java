package com.biz.gbck.transform.geo;


import com.biz.gbck.dao.redis.ro.geo.ProvinceRo;
import com.biz.gbck.vo.common.response.ProvinceResponseVo;
import com.google.common.base.Function;

/**
 * 将省份ro转换为vo
 * Created by maoyikun on 16-12-20.
 */
public class ProvinceRo2ProvinceResponseVo implements Function<ProvinceRo, ProvinceResponseVo> {
    @Override
    public ProvinceResponseVo apply(ProvinceRo input) {
        if(input == null){
            return null;
        }
        ProvinceResponseVo resp = (ProvinceResponseVo) new AbstractAreaRo2AbstractResponseVo(new ProvinceResponseVo()).apply(input);
        resp.setRegionId(input.getRegionId());
        return resp;
    }
}
