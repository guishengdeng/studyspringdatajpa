package com.biz.soa.order.builder;

import com.biz.gbck.dao.mysql.po.order.Order;
import com.biz.gbck.dao.mysql.po.order.OrderItem;
import com.biz.gbck.dao.mysql.po.order.OrderReturn;
import com.biz.gbck.dao.mysql.po.order.OrderReturnItem;
import com.biz.gbck.enums.order.ReturnCause;
import com.biz.gbck.enums.order.ReturnType;
import com.biz.gbck.vo.order.req.OrderApplyReturnReqVo;
import com.biz.service.IdService;
import org.apache.commons.lang.StringUtils;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * 订单Builder
 *
 * @author lei
 * @date 2017年05月03日
 * @reviewer
 * @see
 */
public class OrderReturnBuilder {

    private IdService idService;

    private OrderReturn orderReturn;

    public static OrderReturnBuilder createBuilder(OrderApplyReturnReqVo reqVo, IdService idService){
        OrderReturnBuilder builder = new OrderReturnBuilder();
        builder.orderReturn = new OrderReturn();
        builder.idService = idService;
        if (reqVo.getType() != null) {
            if (reqVo.getType() == ReturnType.RETURN.getValue()) {
                builder.orderReturn.setReturnType(ReturnType.RETURN);
            } else if (reqVo.getType() == ReturnType.EXCHANGE.getValue()) {
                builder.orderReturn.setReturnType(ReturnType.EXCHANGE);
            }
        }
        builder.orderReturn.setReason(ReturnCause.valueOf(reqVo.getCause()).getDesc());
        builder.orderReturn.setDescription(StringUtils.trim(reqVo.getDescription()));
        builder.orderReturn.setImages(reqVo.getImages());
        return builder;
    }

    public OrderReturnBuilder setOrder(Order order) {
        this.orderReturn.setOrder(order);
        List<OrderReturnItem> items = newArrayList();
        for (OrderItem orderItem : order.getItems()) {
            OrderReturnItem item = new OrderReturnItem();
            item.setId(idService.nextId());
            item.setItem(orderItem);
            item.setReturnQuantity(orderItem.getQuantity());
            items.add(item);
        }
        this.orderReturn.setItems(items);
        this.orderReturn.setReturnAmount(order.getPayAmount());
        return this;
    }


    public OrderReturn build(Long id, String returnCode){
        this.orderReturn.setId(id);
        this.orderReturn.setReturnCode(returnCode);
        return this.orderReturn;
    }



}
