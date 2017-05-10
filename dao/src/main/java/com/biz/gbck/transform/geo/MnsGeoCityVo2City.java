package com.biz.gbck.transform.geo;


import com.biz.gbck.dao.mysql.po.geo.CityPo;
import com.biz.gbck.dao.mysql.po.geo.ProvincePo;
import com.biz.gbck.vo.geo.MnsGeoCityVo;
import com.google.common.base.Function;

/**
 * Geo City vo转换为City
 * Created by lei
 */
public class MnsGeoCityVo2City extends AbstractMnsGeoVo2AbstractAreaWithCode implements Function<MnsGeoCityVo, CityPo> {

    private CityPo city;

    public MnsGeoCityVo2City(CityPo city) {
        super(city);
        this.city = city;
    }

    @Override
    public CityPo apply(MnsGeoCityVo vo) {
        if(vo == null){
            return null;
        }
        CityPo city = (CityPo) super.apply(vo);
        city.setId(vo.getId());
        if(vo.getProvinceId() != null && (city.getProvince() == null || vo.getProvinceId() != city.getProvince().getId())){
            city.setProvince(new ProvincePo(vo.getProvinceId()));
        }
        city.setStatus(vo.getStatus());
        return  city;
    }
}
