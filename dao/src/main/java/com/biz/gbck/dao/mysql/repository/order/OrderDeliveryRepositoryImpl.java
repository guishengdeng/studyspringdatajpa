package com.biz.gbck.dao.mysql.repository.order;

import com.biz.gbck.dao.mysql.po.order.OrderDelivery;
import com.biz.support.jpa.repository.CommonJpaRepositoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

/**
 * @author yangzichun
 * @date 2017/4/24
 */
public class OrderDeliveryRepositoryImpl extends CommonJpaRepositoryBean<OrderDelivery, Long> implements
        OrderDeliveryDao {
    @Autowired
    public OrderDeliveryRepositoryImpl(EntityManager em) {
        super(OrderDelivery.class, em);
    }
}
