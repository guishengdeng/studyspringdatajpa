package com.biz.gbck.dao.mysql.repository.org;

import com.biz.gbck.dao.mysql.po.org.CompanyGroupPo;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author: liubin
 * @date 4/26/17 09:45
 */
@Repository
public interface CompanyGroupRepository extends CommonJpaRepository<CompanyGroupPo, Long>, CompanyGroupDao, JpaSpecificationExecutor<CompanyGroupPo> {

}
