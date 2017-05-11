package com.biz.gbck.dao.mysql.repository.purchase;

import com.biz.gbck.dao.mysql.po.purchase.PurchaseOrder;
import com.biz.support.jpa.repository.CommonJpaRepositoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

/**
 * @author yangzichun
 * @date 2017/4/24
 */
public class PurchaseOrderRepositoryImpl extends CommonJpaRepositoryBean<PurchaseOrder, Long> implements
        PurchaseOrderDao {
    @Autowired
    public PurchaseOrderRepositoryImpl(EntityManager em) {
        super(PurchaseOrder.class, em);
    }
}
