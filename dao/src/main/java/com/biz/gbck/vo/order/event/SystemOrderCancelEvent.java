package com.biz.gbck.vo.order.event;

/**
 * 系统取消订单事件
 *
 * @author lei
 * @date 2017年05月10日
 * @reviewer
 * @see
 */
public class SystemOrderCancelEvent extends OrderCancelEvent {

    private static final long serialVersionUID = -1491375148118062376L;

    public SystemOrderCancelEvent(Object source, Long orderId) {
        super(source, orderId);
    }
}
