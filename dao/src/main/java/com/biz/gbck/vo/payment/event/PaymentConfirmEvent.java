package com.biz.gbck.vo.payment.event;

/**
 * 支付确认事件基类
 *
 * @author lei
 * @date 2017年05月10日
 * @reviewer
 * @see
 */
public class PaymentConfirmEvent extends PaymentEvent {

    private static final long serialVersionUID = 8281723473115048025L;

    private Long orderId;

    public PaymentConfirmEvent(Object source, Long paymentId, Long orderId) {
        super(source, paymentId);
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
