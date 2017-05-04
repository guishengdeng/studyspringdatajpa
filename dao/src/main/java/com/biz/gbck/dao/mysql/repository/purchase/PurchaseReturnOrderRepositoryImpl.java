package com.biz.gbck.dao.mysql.repository.purchase;

import com.biz.gbck.dao.mysql.po.purchase.PurchaseReturnOrder;
import com.biz.support.jpa.repository.CommonJpaRepositoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

/**
 * @author yangzichun
 * @date 2017/4/24
 */
public class PurchaseReturnOrderRepositoryImpl extends CommonJpaRepositoryBean<PurchaseReturnOrder, Long> implements
        PurchaseReturnOrderDao {
    @Autowired
    public PurchaseReturnOrderRepositoryImpl(EntityManager em) {
        super(PurchaseReturnOrder.class, em);
    }
}
