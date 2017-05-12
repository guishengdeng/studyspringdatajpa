package com.biz.gbck.dao.mysql.repository.order;

import com.biz.gbck.dao.mysql.po.order.OrderReturn;
import com.biz.support.jpa.repository.CommonJpaRepositoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

/**
 * @author yangzichun
 * @date 2017/4/24
 */
public class OrderReturnRepositoryImpl extends CommonJpaRepositoryBean<OrderReturn, Long> implements OrderReturnDao {
    @Autowired
    public OrderReturnRepositoryImpl(EntityManager em) {
        super(OrderReturn.class, em);
    }
}
