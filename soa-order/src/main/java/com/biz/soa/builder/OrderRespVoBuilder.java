package com.biz.soa.builder;

import com.biz.core.asserts.SystemAsserts;
import com.biz.gbck.dao.mysql.po.order.Order;
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

    public static OrderRespVoBuilder createBuider(Order order) {
        OrderRespVoBuilder builder = new OrderRespVoBuilder();
        builder.respVo = new OrderRespVo(order);
        return builder;
    }

    public OrderRespVoBuilder setSellerInfo(String sellerName) {
        SystemAsserts.notNull(sellerName, "发货人名称不能为空");
        this.respVo.setSellerName(sellerName);
        return this;
    }

    public OrderRespVoBuilder setBuyerInfo(String buyerName, String buyerMobile, String address) {
        SystemAsserts.notNull(buyerName, "收货人姓名不能为空");
        SystemAsserts.notNull(buyerMobile, "收货人手机号不能为空");
        SystemAsserts.notNull(address,  "收货人地址不能为空");
        this.respVo.setBuyerName(buyerName);
        this.respVo.setBuyerMobile(buyerMobile);
        this.respVo.setBuyerAddress(address);
        return this;
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
