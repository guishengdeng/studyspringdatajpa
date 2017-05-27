package com.biz.gbck.vo.payment.event;

import com.biz.core.event.BizEvent;

/**
 * 支付事件基类
 *
 * @author lei
 * @date 2017年05月10日
 * @reviewer
 * @see
 */
public abstract class PaymentEvent extends BizEvent {

    private static final long serialVersionUID = 8281723473115048025L;

    private final Long paymentId;

    public PaymentEvent(Object source, Long paymentId) {
        super(source);
        this.paymentId = paymentId;
    }

    public Long getPaymentId() {
        return paymentId;
    }
}
