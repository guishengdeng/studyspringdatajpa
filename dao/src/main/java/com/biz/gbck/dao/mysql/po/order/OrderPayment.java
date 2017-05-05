package com.biz.gbck.dao.mysql.po.order;

import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.enums.order.PaymentStatus;
import com.biz.gbck.enums.order.PaymentType;
import com.biz.support.jpa.po.BaseEntity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * 订单支付单
 *
 * @author lei
 * @date 2017年04月20日
 * @reviewer
 * @see
 */
@Entity
@Table(name = "ord_order_payment")
public class OrderPayment extends BaseEntity {

    private static final long serialVersionUID = 3603128522862210059L;

    @ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    protected Order order;

    /**
     * 支付方式
     */
    @Convert(converter = PaymentType.Converter.class)
    private PaymentType PaymentType;

    /**
     * 付款状态
     */
    @Column(nullable = false)
    @Convert(converter = PaymentStatus.Converter.class)
    private PaymentStatus payStatus = PaymentStatus.UN_PAY;

    /**
     * 支付金额
     */
    @Column(nullable = false)
    private Integer payAmount = 0;

    // 微信／支付宝／其他 支付回调后 获取到的交易单编号
    private String tradeNo;

    /**
     * 订单名称(支付单使用)
     */
    @Column(length = 50)
    private String subject;

    /**
     * 付款单详情(支付单使用)
     */
    @Column
    protected String detail;

    /**
     * 支付成功时间(支付回调时间）
     */
    private Date successDate;


    /**
     * 过期时间
     */
    private Timestamp expireTimestamp;


    /**
     * 支付单状态
     */
    @Column
    @Enumerated(value = EnumType.STRING)
    private CommonStatusEnum status = CommonStatusEnum.ENABLE;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public com.biz.gbck.enums.order.PaymentType getPaymentType() {
        return PaymentType;
    }

    public void setPaymentType(com.biz.gbck.enums.order.PaymentType paymentType) {
        PaymentType = paymentType;
    }

    public PaymentStatus getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(PaymentStatus payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Integer payAmount) {
        this.payAmount = payAmount;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getSuccessDate() {
        return successDate;
    }

    public void setSuccessDate(Date successDate) {
        this.successDate = successDate;
    }

    public Timestamp getExpireTimestamp() {
        return expireTimestamp;
    }

    public void setExpireTimestamp(Timestamp expireTimestamp) {
        this.expireTimestamp = expireTimestamp;
    }

    public CommonStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CommonStatusEnum status) {
        this.status = status;
    }
}
