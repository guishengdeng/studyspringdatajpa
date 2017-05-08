package com.biz.gbck.dao.mysql.repository.geo;

import com.biz.gbck.dao.mysql.po.geo.RegionPo;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository
        extends CommonJpaRepository<RegionPo, Integer>, RegionDao, JpaSpecificationExecutor<RegionPo> {

    //findByOrderByCodeAsc
    public List<RegionPo> findByOrderByIdxAsc();

    @Modifying
    @Query("Update RegionPo set status=:status where id=:id") void disableRegionById(
            @Param("id") Integer id, @Param("status") Integer status);

    @Query("From RegionPo where status=:status") List<RegionPo> findRegionsBystatus(
            @Param("status") Integer status);
}
