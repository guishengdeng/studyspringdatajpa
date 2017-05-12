package com.biz.gbck.dao.mysql.repository.order;

import com.biz.gbck.dao.mysql.po.order.OrderReturnItem;
import com.biz.support.jpa.repository.CommonJpaRepositoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

/**
 * @author yangzichun
 * @date 2017/4/24
 */
public class OrderReturnItemRepositoryImpl extends CommonJpaRepositoryBean<OrderReturnItem, Long> implements
        OrderReturnItemDao {
    @Autowired
    public OrderReturnItemRepositoryImpl(EntityManager em) {
        super(OrderReturnItem.class, em);
    }
}
