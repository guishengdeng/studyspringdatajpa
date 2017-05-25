package com.biz.gbck.dao.mysql.repository.org;

import com.biz.gbck.dao.mysql.po.org.WarehousePo;
import com.biz.gbck.enums.org.CompanyLevel;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * WareHouseRepository
 *
 * @author guisheng.deng
 * @date 2017年05月24日
 * @reviewer
 * @description
 * @see
 */
@Repository
public interface WareHouseRepository extends CommonJpaRepository<WarehousePo,Long>,WareHouseDao,JpaSpecificationExecutor<WarehousePo> {

    List<WarehousePo> findWareHousesByCompanyLevel(CompanyLevel companyLevel);
}
