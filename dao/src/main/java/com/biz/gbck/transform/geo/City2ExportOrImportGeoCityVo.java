package com.biz.gbck.transform.geo;


import com.biz.gbck.dao.mysql.po.geo.CityPo;
import com.biz.gbck.vo.geo.ExportOrImportGeoCityVo;
import com.google.common.base.Function;

/**
 * @author zhangcheng
 * @date 2017/3/7
 * @reviewer
 * @see
 */
public class City2ExportOrImportGeoCityVo implements Function<CityPo, ExportOrImportGeoCityVo> {

    @Override
    public ExportOrImportGeoCityVo apply(CityPo city) {
        if (city != null){
            ExportOrImportGeoCityVo vo = new ExportOrImportGeoCityVo();
            vo.setId(city.getId());
            vo.setIdx(city.getIdx());
            vo.setPrefix(city.getPrefix());
            vo.setStatus(city.getStatus());
            vo.setBaiducode(city.getBaiducode());
            vo.setBaiduname(city.getBaiduname());
            vo.setCode(city.getCode());
            vo.setCoordinate(city.getCoordinate());
            vo.setDescription(city.getDescription());
            vo.setLat(city.getLat());
            vo.setLon(city.getLon());
            vo.setName(city.getName());
            vo.setPost(city.getPost());
            vo.setWeight(city.getWeight());
            vo.setProvince(city.getProvince().getId());
            return vo;
        }
        return null;
    }
}
