package com.biz.gbck.dao.mysql.repository.order;

import com.biz.gbck.dao.mysql.po.order.Order;
import com.biz.support.jpa.repository.CommonJpaRepositoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

/**
 * @author yangzichun
 * @date 2017/4/24
 */
public class OrderRepositoryImpl extends CommonJpaRepositoryBean<Order, String> implements OrderDao {

    @Autowired
    public OrderRepositoryImpl(EntityManager em) {
        super(Order.class, em);
    }


}