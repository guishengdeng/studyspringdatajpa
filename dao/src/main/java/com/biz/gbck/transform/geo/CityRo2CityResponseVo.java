package com.biz.gbck.transform.geo;


import com.biz.gbck.dao.redis.ro.geo.CityRo;
import com.biz.gbck.vo.common.response.CityResponseVo;
import com.google.common.base.Function;

/**
 * 将城市ro转换为vo
 * Created by maoyikun on 16-12-20.
 */
public class CityRo2CityResponseVo implements Function<CityRo, CityResponseVo> {

    @Override
    public CityResponseVo apply(CityRo input) {
        if (input == null) {
            return null;
        }
        CityResponseVo resp = (CityResponseVo) new AbstractAreaRo2AbstractResponseVo(new CityResponseVo()).apply(input);
        resp.setProvinceId(input.getProvinceId());
        return resp;
    }
}
