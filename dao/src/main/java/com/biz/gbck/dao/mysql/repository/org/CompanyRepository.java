package com.biz.gbck.dao.mysql.repository.org;

import com.biz.gbck.dao.mysql.po.org.Company;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author: liubin
 * @date 4/26/17 09:42
 */
@Repository
public interface CompanyRepository extends CommonJpaRepository<Company, Long>, CompanyDao, JpaSpecificationExecutor<Company> {


}
