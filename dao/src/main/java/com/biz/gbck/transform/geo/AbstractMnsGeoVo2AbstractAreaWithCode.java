package com.biz.gbck.transform.geo;


import com.biz.core.util.PingYinUtil;
import com.biz.gbck.dao.mysql.po.geo.AbstractAreaWithCode;
import com.biz.gbck.vo.geo.AbstractMnsGeoVo;
import org.apache.commons.lang3.StringUtils;

/**
 * Geo vo转换为po
 * Created by lei
 */
public class AbstractMnsGeoVo2AbstractAreaWithCode {

    private AbstractAreaWithCode po;

    public AbstractMnsGeoVo2AbstractAreaWithCode(AbstractAreaWithCode po) {
        this.po = po;
    }

    public AbstractAreaWithCode apply(AbstractMnsGeoVo vo) {
        if (vo == null) {
            return null;
        }
        if (StringUtils.isNotEmpty(vo.getCode()))
            po.setCode(vo.getCode());
        if (StringUtils.isNotEmpty(vo.getName()))
            po.setName(vo.getName());
        if (StringUtils.isNotEmpty(vo.getBaiduname()))
            po.setBaiduname(vo.getBaiduname());
        if (vo.getWeight() != null)
            po.setWeight(vo.getWeight());
        if (vo.getLat() != null)
            po.setLat(vo.getLat());
        if (vo.getLon() != null)
            po.setLon(vo.getLon());
        if (vo.getIdx() != null)
            po.setIdx(vo.getIdx());
        if (StringUtils.isNotEmpty(vo.getDescription()))
            po.setDescription(vo.getDescription());
        if (StringUtils.isNotEmpty(vo.getCoordinate()))
            po.setCoordinate(vo.getCoordinate());
        po.setPrefix(PingYinUtil.getFirstLetter(vo.getName()));

        return po;
    }
}
