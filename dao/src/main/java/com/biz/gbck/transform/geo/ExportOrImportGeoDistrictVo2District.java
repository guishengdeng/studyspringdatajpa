package com.biz.gbck.transform.geo;


import com.biz.core.util.PinyinUtil;
import com.biz.gbck.dao.mysql.po.geo.DistrictPo;
import com.biz.gbck.vo.geo.ExportOrImportGeoDistrictVo;
import com.google.common.base.Function;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zhangcheng
 * @date 2017/3/7
 * @reviewer
 * @see
 */
public class ExportOrImportGeoDistrictVo2District implements Function<ExportOrImportGeoDistrictVo, DistrictPo> {

    @Override
    public DistrictPo apply(ExportOrImportGeoDistrictVo vo) {
        if (vo != null){
            DistrictPo district = new DistrictPo();
            district.setId(vo.getId());
            district.setIdx(vo.getIdx());
            district.setStatus(vo.getStatus());
            district.setBaiducode(vo.getBaiducode());
            if (StringUtils.isNotBlank(vo.getCode())){
                district.setCode(vo.getCode());
            }else {
                if (StringUtils.isNotBlank(vo.getName())){
                    district.setCode(PinyinUtil.getFullSpell(vo.getName()));
                }
                if (StringUtils.isNotBlank(vo.getBaiduname())){
                    district.setCode(PinyinUtil.getFullSpell(vo.getBaiduname()));
                }
            }
            district.setDescription(vo.getDescription());
            district.setCoordinate(vo.getCoordinate());
            district.setLat(vo.getLat());
            district.setLon(vo.getLon());
            if (StringUtils.isNotBlank(vo.getName())){
                district.setName(vo.getName());
            }else {
                if (StringUtils.isNotBlank(vo.getBaiduname())){
                   district.setName(vo.getBaiduname());
                }
            }
            district.setPost(vo.getPost());
            district.setWeight(vo.getWeight());
            if (StringUtils.isNotBlank(vo.getPrefix())){
                district.setPrefix(vo.getPrefix());
            }
            if (StringUtils.isNotBlank(vo.getBaiduname())){
                district.setBaiduname(vo.getBaiduname());
            }else {
                if (StringUtils.isNotBlank(vo.getName())){
                    district.setBaiduname(vo.getName());
                }
            }
            return district;
        }
        return null;
    }
}
