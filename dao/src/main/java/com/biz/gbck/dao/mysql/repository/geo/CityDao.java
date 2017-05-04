package com.biz.gbck.dao.mysql.repository.geo;


import com.biz.gbck.dao.mysql.po.geo.CityPo;

import java.util.List;

public interface CityDao {

    public List<CityPo> findAll();

    public List<CityPo> findAllCitysByProvinceId(int provinceId);


}
