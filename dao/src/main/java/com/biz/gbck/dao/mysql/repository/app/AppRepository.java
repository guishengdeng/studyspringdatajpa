package com.biz.gbck.dao.mysql.repository.app;

import com.biz.gbck.dao.mysql.po.app.App;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author lzz
 * @date 2017年04月18日
 * @reviewer
 */

public interface AppRepository extends CommonJpaRepository<App, Long>, JpaSpecificationExecutor<App> {


}

















