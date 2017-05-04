package com.biz.gbck.dao.mysql.repository.purchase;

import com.biz.gbck.dao.mysql.po.purchase.PurchaseOrderItem;
import com.biz.support.jpa.repository.CommonJpaRepositoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

/**
 * @author yangzichun
 * @date 2017/4/24
 */
public class PurchaseOrderItemRepositoryImpl extends CommonJpaRepositoryBean<PurchaseOrderItem, Long> implements
        PurchaseOrderItemDao {
    @Autowired
    public PurchaseOrderItemRepositoryImpl(EntityManager em) {
        super(PurchaseOrderItem.class, em);
    }
}
