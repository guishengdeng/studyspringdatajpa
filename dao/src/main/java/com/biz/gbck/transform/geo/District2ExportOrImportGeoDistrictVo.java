package com.biz.gbck.transform.geo;


import com.biz.gbck.dao.mysql.po.geo.DistrictPo;
import com.biz.gbck.vo.geo.ExportOrImportGeoDistrictVo;
import com.google.common.base.Function;

/**
 * @author zhangcheng
 * @date 2017/3/7
 * @reviewer
 * @see
 */
public class District2ExportOrImportGeoDistrictVo implements Function<DistrictPo, ExportOrImportGeoDistrictVo> {

    @Override
    public ExportOrImportGeoDistrictVo apply(DistrictPo district) {
        if (district != null){
            ExportOrImportGeoDistrictVo vo = new ExportOrImportGeoDistrictVo();
            vo.setId(district.getId());
            vo.setIdx(district.getIdx());
            vo.setPrefix(district.getPrefix());
            vo.setStatus(district.getStatus());
            vo.setBaiducode(district.getBaiducode());
            vo.setBaiduname(district.getBaiduname());
            vo.setCode(district.getCode());
            vo.setCoordinate(district.getCoordinate());
            vo.setDescription(district.getDescription());
            vo.setLat(district.getLat());
            vo.setLon(district.getLon());
            vo.setName(district.getName());
            vo.setPost(district.getPost());
            vo.setWeight(district.getWeight());
            vo.setCity(district.getCity().getId());
            if (district.getProvinceId() != null){
                vo.setProvince(district.getProvinceId());
            }
            return vo;
        }
        return null;
    }
}
