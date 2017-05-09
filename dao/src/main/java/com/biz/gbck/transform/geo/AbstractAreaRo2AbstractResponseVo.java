package com.biz.gbck.transform.geo;

import com.biz.gbck.dao.redis.ro.geo.AbstractAreaRo;
import com.biz.gbck.vo.common.AbstractAreaResponseVo;

/**
 * 抽象区域ro转vo
 * @author maoyikun
 * @date 16-12-22
 */
public class AbstractAreaRo2AbstractResponseVo {

    protected AbstractAreaResponseVo areaResponseVo;

    public AbstractAreaRo2AbstractResponseVo(AbstractAreaResponseVo areaResponseVo) {
        this.areaResponseVo = areaResponseVo;
    }

    public AbstractAreaResponseVo apply(AbstractAreaRo input) {
        if(input == null){
            return null;
        }
        areaResponseVo.setId(input.getId());
        areaResponseVo.setBaiducode(input.getBaiducode());
        areaResponseVo.setBaiduname(input.getBaiduname());
        areaResponseVo.setCode(input.getCode());
        areaResponseVo.setCoordinate(input.getCoordinate());
        areaResponseVo.setDescription(input.getDescription());
        areaResponseVo.setIdx(input.getIdx());
        areaResponseVo.setLat(input.getLat());
        areaResponseVo.setLon(input.getLon());
        areaResponseVo.setName(input.getName());
        areaResponseVo.setPost(input.getPost());
        areaResponseVo.setPrefix(input.getPrefix());
        areaResponseVo.setWeight(input.getWeight());
        return areaResponseVo;
    }
}
