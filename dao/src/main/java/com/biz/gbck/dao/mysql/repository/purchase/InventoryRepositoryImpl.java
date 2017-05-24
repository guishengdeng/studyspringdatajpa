package com.biz.gbck.dao.mysql.repository.purchase;

import com.biz.gbck.dao.mysql.po.purchase.Inventory;
import com.biz.support.jpa.repository.CommonJpaRepositoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

/**
 * @author lei
 * @date 2017/5/22
 */
public class InventoryRepositoryImpl extends CommonJpaRepositoryBean<Inventory, Long> implements InventoryDao {
    @Autowired
    public InventoryRepositoryImpl(EntityManager em) {
        super(Inventory.class, em);
    }
}
