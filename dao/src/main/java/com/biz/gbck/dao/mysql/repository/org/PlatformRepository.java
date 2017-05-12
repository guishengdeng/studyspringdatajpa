package com.biz.gbck.dao.mysql.repository.org;

import com.biz.gbck.dao.mysql.po.org.PlatformPo;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author: liubin
 * @date 4/26/17 09:52
 */
@Repository
public interface PlatformRepository extends CommonJpaRepository<PlatformPo, Long>, PlatformDao, JpaSpecificationExecutor<PlatformPo> {

}
