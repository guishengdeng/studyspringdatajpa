package com.biz.gbck.dao.mysql.repository.org;

import com.biz.gbck.dao.mysql.po.org.ShopPo;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by dylan on 16-5-12.
 */
@Repository
public interface ShopPoRepository
        extends CommonJpaRepository<ShopPo, Long>, ShopTypeDao,JpaSpecificationExecutor<ShopPo> {

}
