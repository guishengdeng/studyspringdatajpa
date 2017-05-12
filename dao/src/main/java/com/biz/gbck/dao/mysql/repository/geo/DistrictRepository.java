package com.biz.gbck.dao.mysql.repository.geo;

import com.biz.gbck.dao.mysql.po.geo.CityPo;
import com.biz.gbck.dao.mysql.po.geo.DistrictPo;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository
        extends CommonJpaRepository<DistrictPo, Integer>, DistrictDao, JpaSpecificationExecutor<DistrictPo> {


    @Modifying
    @Query("update DistrictPo c set c.code = ?2 where c.id = ?1")
    public int updateCodeById(int id, String code);

    @Modifying
    @Query("update DistrictPo r set r.weight =?2 ,r.idx=?3 where r.id=?1")
    public int updateWeigthAndIdx(int id, int weigth, int idx);

    @Modifying
    @Query("Update DistrictPo set status=:status where id=:id") void disableDistrict(
            @Param("id") Integer id, @Param("status") Integer status);

    DistrictPo findByCityAndName(CityPo cityPo, String name);

    DistrictPo findByCityAndBaiduname(CityPo city, String receiveCountry);

    List<DistrictPo> findByCityIdAndName(Integer cityId, String name);
}
