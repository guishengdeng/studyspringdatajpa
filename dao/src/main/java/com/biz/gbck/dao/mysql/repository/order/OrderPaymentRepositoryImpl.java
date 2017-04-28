package com.biz.gbck.dao.mysql.repository.order;

import com.biz.gbck.dao.mysql.po.order.OrderPayment;
import com.biz.support.jpa.repository.CommonJpaRepositoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

/**
 * @author yangzichun
 * @date 2017/4/24
 */
public class OrderPaymentRepositoryImpl extends CommonJpaRepositoryBean<OrderPayment, Long> implements OrderPaymentDao {
    @Autowired
    public OrderPaymentRepositoryImpl(EntityManager em) {
        super(OrderPayment.class, em);
    }
}
