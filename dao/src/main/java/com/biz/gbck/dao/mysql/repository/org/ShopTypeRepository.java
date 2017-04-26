package com.biz.gbck.dao.mysql.repository.org;


import com.biz.gbck.dao.mysql.po.org.ShopTypePo;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ShopTypeRepository
    extends CommonJpaRepository<ShopTypePo, Long>, ShopTypeDao, JpaSpecificationExecutor<ShopTypePo> {


    @Query(value = "FROM ShopTypePo p WHERE p.status=:status ORDER BY idx")
    List<ShopTypePo> findAllByStatus(@Param("status") Integer status);

    @Modifying
    @Query("UPDATE ShopTypePo SET status = 0 WHERE id = :id") void disableShopType(
            @Param("id") Long shopTypeId);
}
