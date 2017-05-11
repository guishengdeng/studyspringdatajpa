package com.biz.gbck.transform.geo;


import com.biz.core.util.PinyinUtil;
import com.biz.gbck.dao.mysql.po.geo.CityPo;
import com.biz.gbck.vo.geo.ExportOrImportGeoCityVo;
import com.google.common.base.Function;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zhangcheng
 * @date 2017/3/7
 * @reviewer
 * @see
 */
public class ExportOrImportGeoCityVo2City implements Function<ExportOrImportGeoCityVo, CityPo> {

    @Override
    public CityPo apply(ExportOrImportGeoCityVo vo) {
        if (vo != null){
            CityPo city = new CityPo();
            city.setId(vo.getId());
            city.setIdx(vo.getIdx());
            city.setStatus(vo.getStatus());
            city.setBaiducode(vo.getBaiducode());
            if (StringUtils.isNotBlank(vo.getCode())){
                city.setCode(vo.getCode());
            }else {
                city.setCode(PinyinUtil.getFullSpell(vo.getName().substring(0 , 2)));
            }
            city.setDescription(vo.getDescription());
            city.setCoordinate(vo.getCoordinate());
            city.setLat(vo.getLat());
            city.setLon(vo.getLon());
            city.setName(vo.getName());
            city.setPost(vo.getPost());
            city.setWeight(vo.getWeight());
            if (StringUtils.isNotBlank(vo.getPrefix())){
                city.setPrefix(vo.getPrefix());
            }else {
                city.setPrefix(PinyinUtil.getFirstSpell(city.getName()).substring(0, 1).toUpperCase());
            }
            city.setBaiduname(vo.getBaiduname());
            return city;
        }
        return null;
    }
}
