package com.biz.gbck.transform.geo;


import com.biz.gbck.dao.mysql.po.geo.CityPo;
import com.biz.gbck.dao.mysql.po.geo.DistrictPo;
import com.biz.gbck.vo.geo.MnsGeoDistrictVo;
import com.google.common.base.Function;

import java.util.Objects;

/**
 * Geo  district vo转换为District
 * Created by lei
 */
public class MnsGeoDistrictVo2District extends AbstractMnsGeoVo2AbstractAreaWithCode implements
        Function<MnsGeoDistrictVo, DistrictPo> {

    private DistrictPo district;

    public MnsGeoDistrictVo2District(DistrictPo district) {
        super(district);
        this.district = district;
    }

    @Override
    public DistrictPo apply(MnsGeoDistrictVo vo) {
        DistrictPo district = (DistrictPo) super.apply(vo);

        if (vo.getCityId() != null && (district.getCity() == null || !Objects.equals(vo.getCityId(), district.getCity
                ().getId()))) {
            district.setCity(new CityPo(vo.getCityId()));
        }
        if (vo.getProvinceid() != null && !Objects.equals(vo.getProvinceid(), district.getProvinceId())) {
            district.setProvinceId(vo.getProvinceid());
        }
        district.setStatus(vo.getStatus());
        return district;
    }
}
