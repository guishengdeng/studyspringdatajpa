package com.biz.gbck.transform.geo;


import com.biz.gbck.dao.mysql.po.geo.ProvincePo;
import com.biz.gbck.vo.geo.ExportOrImportGeoProvinceVo;
import com.google.common.base.Function;

/**
 * @author zhangcheng
 * @date 2017/3/7
 * @reviewer
 * @see
 */
public class Province2ExportOrImportGeoProvinceVo implements Function<ProvincePo, ExportOrImportGeoProvinceVo> {
    @Override
    public ExportOrImportGeoProvinceVo apply(ProvincePo province) {
        if (province != null){
            ExportOrImportGeoProvinceVo vo = new ExportOrImportGeoProvinceVo();
            vo.setId(province.getId());
            vo.setIdx(province.getIdx());
            vo.setPrefix(province.getPrefix());
            vo.setStatus(province.getStatus());
            vo.setBaiducode(province.getBaiducode());
            vo.setBaiduname(province.getBaiduname());
            vo.setCode(province.getCode());
            vo.setCoordinate(province.getCoordinate());
            vo.setDescription(province.getDescription());
            vo.setLat(province.getLat());
            vo.setLon(province.getLon());
            vo.setName(province.getName());
            vo.setPost(province.getPost());
            vo.setWeight(province.getWeight());
            if (province.getRegion() != null){
                vo.setRegion(province.getRegion().getId());
            }else {
                vo.setRegion(20);
            }
            return vo;
        }
        return null;
    }
}
