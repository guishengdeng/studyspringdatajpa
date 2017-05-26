package com.biz.gbck.vo.order.event;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 创建订单事件
 *
 * @author lei
 * @date 2017年05月10日
 * @reviewer
 * @see
 */
public class OrderCreateEvent extends OrderEvent {

    private static final long serialVersionUID = -1461375148118062376L;

    public OrderCreateEvent(Object source, Long orderId) {
        super(source, orderId);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).toString();
    }
}
