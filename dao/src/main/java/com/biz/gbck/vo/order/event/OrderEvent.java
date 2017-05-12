package com.biz.gbck.vo.order.event;

import com.biz.core.event.BizEvent;

/**
 * 订单事件基类
 *
 * @author lei
 * @date 2017年05月10日
 * @reviewer
 * @see
 */
public abstract class OrderEvent extends BizEvent {

    private static final long serialVersionUID = 8281723473115048025L;

    private final Long orderId;

    public OrderEvent(Object source, Long orderId) {
        super(source);
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }
}
