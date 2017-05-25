package com.biz.gbck.dao.mysql.repository.org;

import com.biz.gbck.dao.mysql.po.org.PlatformPo;
import com.biz.gbck.enums.org.CompanyLevel;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: liubin
 * @date 4/26/17 09:52
 */
@Repository
public interface PlatformRepository extends CommonJpaRepository<PlatformPo, Long>, PlatformDao, JpaSpecificationExecutor<PlatformPo> {

    @Query("SELECT pp FROM PlatformPo pp WHERE pp.name like %?1% ")
    List<PlatformPo> getIdsByNameLike(String name);

    @Query("SELECT DISTINCT pp.name,pp.id FROM PlatformPo pp")
    List<PlatformPo> removeDuplicateName();

    List<PlatformPo> findByCompanyLevel(CompanyLevel companyLevel);
}
