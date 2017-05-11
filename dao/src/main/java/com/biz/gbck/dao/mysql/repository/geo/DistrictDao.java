package com.biz.gbck.dao.mysql.repository.geo;

import com.biz.gbck.dao.mysql.po.geo.DistrictPo;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface DistrictDao {


    public List<DistrictPo> findAll();

    public List<DistrictPo> findAllDistrictByCityId(int cityId);
}
