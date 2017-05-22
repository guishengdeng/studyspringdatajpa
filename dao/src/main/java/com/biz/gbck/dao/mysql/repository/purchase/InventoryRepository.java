package com.biz.gbck.dao.mysql.repository.purchase;

import com.biz.gbck.dao.mysql.po.purchase.Inventory;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author lei
 * @date 2017/5/22
 */
@Repository
public interface InventoryRepository extends CommonJpaRepository<Inventory, Long>, InventoryDao,
        JpaSpecificationExecutor<Inventory> {
}
