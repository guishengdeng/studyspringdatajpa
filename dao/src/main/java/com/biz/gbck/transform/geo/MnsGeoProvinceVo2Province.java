package com.biz.gbck.transform.geo;


import com.biz.gbck.dao.mysql.po.geo.ProvincePo;
import com.biz.gbck.dao.mysql.po.geo.RegionPo;
import com.biz.gbck.vo.geo.MnsGeoProvinceVo;
import com.google.common.base.Function;

import java.util.Objects;

/**
 * Geo Province vo转换为Province
 * Created by lei
 */
public class MnsGeoProvinceVo2Province extends AbstractMnsGeoVo2AbstractAreaWithCode implements
        Function<MnsGeoProvinceVo, ProvincePo> {

    private ProvincePo province;

    public MnsGeoProvinceVo2Province(ProvincePo province) {
        super(province);
        this.province = province;
    }

    @Override
    public ProvincePo apply(MnsGeoProvinceVo vo) {
        if (vo == null) {
            return province;
        }

        ProvincePo province = (ProvincePo) super.apply(vo);
        province.setId(vo.getId());
        if (vo.getRegionId() != null && (province.getRegion() == null || !Objects.equals(vo.getRegionId(), province
                .getRegion().getId()))) {
            RegionPo region = new RegionPo();
            region.setId(vo.getRegionId());
            province.setRegion(region);
        }

        return province;
    }
}
