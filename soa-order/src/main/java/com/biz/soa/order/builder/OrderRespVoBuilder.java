package com.biz.soa.order.builder;

import com.biz.gbck.dao.mysql.po.order.Order;
import com.biz.gbck.dao.mysql.po.order.OrderConsignee;
import com.biz.gbck.dao.mysql.po.order.OrderItem;
import com.biz.gbck.vo.order.resp.OrderItemRespVo;
import com.biz.gbck.vo.order.resp.OrderRespVo;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * 订单返回vo Builder
 *
 * @author lei
 * @date 2017年05月03日
 * @reviewer
 * @see
 */
public class OrderRespVoBuilder {

    private OrderRespVo respVo;

    public static OrderRespVoBuilder createBuilder(Order order) {
        OrderRespVoBuilder builder = new OrderRespVoBuilder();
        builder.respVo = new OrderRespVo(order);

        OrderConsignee consignee = order.getConsignee();
        builder.respVo.setBuyerName(consignee.getName());
        builder.respVo.setBuyerMobile(consignee.getMobile());
        builder.respVo.setBuyerAddress(consignee.getAddress());
        //TODO 地址详情
        return builder;
    }

    public OrderRespVoBuilder setItems(List<OrderItem> orderItems) {
        List<OrderItemRespVo> itemRespVos = Lists.transform(orderItems, OrderItemRespVo::new);
        this.respVo.setItems(itemRespVos);
        return this;
    }

    public OrderRespVo build() {
        return this.respVo;
    }

}
