package com.biz.gbck.transform.geo;


import com.biz.core.util.PinyinUtil;
import com.biz.gbck.dao.mysql.po.geo.ProvincePo;
import com.biz.gbck.vo.geo.ExportOrImportGeoProvinceVo;
import com.google.common.base.Function;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zhangcheng
 * @date 2017/3/7
 * @reviewer
 * @see
 */
public class ExportOrImportGeoProvinceVo2Province implements Function<ExportOrImportGeoProvinceVo, ProvincePo> {

    @Override
    public ProvincePo apply(ExportOrImportGeoProvinceVo vo) {
        if (vo != null){
            ProvincePo province = new ProvincePo();
            province.setId(vo.getId());
            province.setIdx(vo.getIdx());
            province.setStatus(vo.getStatus());
            province.setBaiducode(vo.getBaiducode());
            province.setCode(vo.getCode());
            province.setDescription(vo.getDescription());
            province.setCoordinate(vo.getCoordinate());
            province.setLat(vo.getLat());
            province.setLon(vo.getLon());
            province.setName(vo.getName());
            province.setPost(vo.getPost());
            province.setWeight(vo.getWeight());
            if (StringUtils.isNotBlank(vo.getPrefix())){
                province.setPrefix(vo.getPrefix());
            }else {
                province.setPrefix(PinyinUtil.getFirstSpell(province.getName()).substring(0, 1).toUpperCase());
            }
            province.setBaiduname(vo.getBaiduname());
            return province;
        }
        return null;
    }
}
