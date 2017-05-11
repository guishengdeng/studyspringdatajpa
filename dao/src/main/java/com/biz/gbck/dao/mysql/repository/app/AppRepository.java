package com.biz.gbck.dao.mysql.repository.app;

import com.biz.gbck.dao.mysql.po.app.App;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author lzz
 * @date 2017年04月18日
 * @reviewer
 */

public interface AppRepository extends CommonJpaRepository<App, Long>, JpaSpecificationExecutor<App> {

      @Transactional
      @Query("SELECT app.id FROM App app  ORDER BY create_timestamp DESC")
      List<App> findLastData();
}

















