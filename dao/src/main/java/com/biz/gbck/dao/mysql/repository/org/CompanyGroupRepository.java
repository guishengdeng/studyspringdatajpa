package com.biz.gbck.dao.mysql.repository.org;

import com.biz.gbck.dao.mysql.po.org.CompanyGroupPo;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author: liubin
 * @date 4/26/17 09:45
 */
@Repository
public interface CompanyGroupRepository extends CommonJpaRepository<CompanyGroupPo, Long>, CompanyGroupDao, JpaSpecificationExecutor<CompanyGroupPo> {

    List<CompanyGroupPo> findByStatus(CommonStatusEnum status);


    @Modifying
    @Query("UPDATE CompanyGroupPo c SET c.status = :status WHERE c.id = :id")
    Integer updateStatus(@Param("id") Long id, @Param("status") CommonStatusEnum status);

}
