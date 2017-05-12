package com.biz.gbck.dao.mysql.repository.geo;


import com.biz.gbck.dao.mysql.po.geo.RegionPo;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository extends CommonJpaRepository<RegionPo, Integer>, RegionDao, JpaSpecificationExecutor<RegionPo> {

    /**
     * 根据status查询region信息
     *
     * @param status
     * @return region po集合
     */
    @Query("from RegionPo region where region.status = ?1")
    List<RegionPo> findRegionsBystatus(Integer status);
}
