package com.biz.gbck.dao.mysql.repository.org;

import com.biz.gbck.dao.mysql.po.org.PartnerPo;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: liubin
 * @date 4/26/17 09:47
 */
@Repository
public interface PartnerRepository extends CommonJpaRepository<PartnerPo, Long>, PartnerDao, JpaSpecificationExecutor<PartnerPo> {

    @Query("SELECT pp FROM PartnerPo pp WHERE pp.name like %?1% ")
    List<PartnerPo> getIdsByNameLike(String name);

    @Query("SELECT  DISTINCT pp.name FROM PartnerPo pp")
    List<String>  removeDuplicatedName();


}
