package com.biz.gbck.dao.mysql.repository.info;


import com.biz.gbck.dao.mysql.po.info.PromotionPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PromotionRepository
    extends JpaRepository<PromotionPo, Long>, PromotionDao {


    @Query("FROM PromotionPo p WHERE p.status = ?1 order by idx ")
    List<PromotionPo> findAllNormal(Integer status);

}
