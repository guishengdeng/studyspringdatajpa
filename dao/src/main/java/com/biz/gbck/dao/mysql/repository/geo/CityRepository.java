package com.biz.gbck.dao.mysql.repository.geo;

import com.biz.gbck.dao.mysql.po.geo.CityPo;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends CommonJpaRepository<CityPo, Integer>, CityDao, JpaSpecificationExecutor<CityPo> {

    public CityPo findByCode(String code);

    @Query("SELECT id FROM CityPo")
    List<Integer> findCityIds();

    @Modifying
    @Query("Update CityPo set status=:status where id=:id") void disableCityById(
            @Param("id") Integer id, @Param("status") Integer status);


    List<CityPo> findByProvinceIdAndName(Integer provinceId, String name);

    List<CityPo> findByProvinceIdAndStatus(Integer provinceId, Integer status);


}


