package com.biz.gbck.vo.order.resp;

import java.io.Serializable;

/**
 * 订单支付类型Vo
 *
 * @author lei
 * @date 2017年05月03日
 * @reviewer
 * @see
 */
public class OrderPaymentTypeRespVo implements Serializable {

    private static final long serialVersionUID = -7211038111581086451L;

    /**
     * 支付类型Id {@link com.biz.gbck.enums.order.PaymentType}
     */
    private Integer paymentId;

    /**
     * 可用余额
     */
    private Long balance = -1L;

    public OrderPaymentTypeRespVo() {
    }

    public OrderPaymentTypeRespVo(Integer paymentId) {
        this();
        this.paymentId = paymentId;
    }

    public OrderPaymentTypeRespVo(Integer paymentId, Long balance) {
        this();
        this.paymentId = paymentId;
        this.balance = balance;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
}
