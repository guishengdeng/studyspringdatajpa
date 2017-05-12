package com.biz.gbck.transform.geo;


import com.biz.gbck.dao.redis.ro.geo.AbstractAreaRo;
import com.biz.gbck.vo.common.response.AreaResponseVo;
import com.google.common.base.Function;

/**
 * 将抽象地区ro转为返回的地区vo
 * Created by maoyikun on 16-12-20.
 */
public class AreaRo2AreaResponseVo implements Function<AbstractAreaRo, AreaResponseVo> {

    @Override
    public AreaResponseVo apply(AbstractAreaRo input) {
        AreaResponseVo resp = new AreaResponseVo();
        resp.setId(input.getId());
        resp.setLat(input.getLat());
        resp.setLon(input.getLon());
        resp.setName(input.getName());
        resp.setPrefix(input.getPrefix());
        return resp;
    }
}
