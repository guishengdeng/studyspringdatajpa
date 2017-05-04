package com.biz.gbck.dao.mysql.repository.purchase;

import com.biz.gbck.dao.mysql.po.purchase.PurchaseOrderReturnItem;
import com.biz.support.jpa.repository.CommonJpaRepositoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

/**
 * @author yangzichun
 * @date 2017/4/24
 */
public class PurchaseOrderReturnItemRepositoryImpl extends CommonJpaRepositoryBean<PurchaseOrderReturnItem, Long>
        implements PurchaseOrderReturnItemDao {
    @Autowired
    public PurchaseOrderReturnItemRepositoryImpl(EntityManager em) {
        super(PurchaseOrderReturnItem.class, em);
    }
}
