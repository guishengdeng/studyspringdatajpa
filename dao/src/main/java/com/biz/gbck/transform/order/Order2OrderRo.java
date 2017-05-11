package com.biz.gbck.transform.order;

import com.biz.gbck.dao.mysql.po.order.Order;
import com.biz.gbck.dao.redis.ro.order.OrderRo;
import com.biz.gbck.enums.order.OrderShowStatus;
import com.google.common.base.Function;

/**
 * order -> orderRo
 *
 * @author lei
 * @date 2017年05月10日
 * @reviewer
 * @see
 */
public class Order2OrderRo implements Function<Order, OrderRo>{
    @Override
    public OrderRo apply(Order input) {
        if (input != null) {
           OrderRo orderRo = new OrderRo();
           orderRo.setId(input.getId());
           orderRo.setUserId(input.getUserId());
           orderRo.setExpireTimestamp(input.getExpireTimestamp());
           orderRo.setOrderCode(input.getOrderCode());
           orderRo.setStatus(OrderShowStatus.valueOf(input.getStatus().getValue()));
           orderRo.setOrderReturnId(input.getOrderReturnId());
           return orderRo;
        }
        return null;
    }
}
