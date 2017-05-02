package com.biz.gbck.dao.mysql.repository.geo;

import com.biz.gbck.dao.mysql.po.geo.ProvincePo;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinceRepository
        extends CommonJpaRepository<ProvincePo, Integer>, ProvinceDao, JpaSpecificationExecutor<ProvincePo> {

    @Modifying
    @Query("Update ProvincePo set status=:status where id=:id")
    void disableProvinceById(@Param("id") Integer id, @Param("status") Integer status);

    @Modifying
    @Query("From ProvincePo Where name like :name and status=:status")
    List<ProvincePo> findProvinceByName(@Param("name") String name, @Param("status") Integer status);

    ProvincePo findByName(String name);

    List<ProvincePo> findAll();
}
