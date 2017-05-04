package com.biz.gbck.dao.mysql.po.order;

import com.biz.gbck.enums.order.OrderStatus;
import com.biz.gbck.enums.order.PaymentStatus;
import com.biz.gbck.enums.order.PaymentType;
import com.biz.support.jpa.po.BaseEntity;
import javax.persistence.*;
import java.util.List;

/**
 * 订单
 *
 * @author lei
 * @date 2017年04月20日
 * @reviewer
 * @see
 */
@Entity
@Table(name = "ord_order", indexes = {@Index(columnList = "userId"), @Index(columnList = "orderCode", unique = true)})
public class Order extends BaseEntity {

    private static final long serialVersionUID = 4548084051968768185L;

    //订单编号
    @Column(length = 50, unique = true, nullable = false)
    private String orderCode;

    /**
     * 卖家Id
     */
    @Column(nullable = false)
    private Long sellerId;


    //用户(买家) id
    @Column(nullable = false)
    private Long userId;

    /**
     * 订单总金额
     */
    @Column(nullable = false)
    private Integer orderAmount = 0;

    //运费金额
    @Column(nullable = false)
    private Integer freightAmount = 0;

    /**
     * 促销优惠金额
     */
    @Column(nullable = false)
    private Integer freeAmount = 0;

    /**
     * 优惠券抵付金额
     */
    @Column(nullable = false)
    private Integer voucherFreeAmount = 0;

    /**
     * 支付金额
     */
    @Column(nullable = false)
    private Integer payAmount = 0;

    //订单应付款(orderAmount + freightAmount - voucherFreeAmount)
    @Column(nullable = false)
    private Integer payable = 0;


    /**
     * 订单状态
     */
    @Convert(converter = OrderStatus.Converter.class)
    @Column(nullable = false)
    private OrderStatus status = OrderStatus.CREATED;

    /**
     * 支付方式
     */
    @Convert(converter = PaymentType.Converter.class)
    private PaymentType paymentType;

    /**
     * 付款状态
     */
    @Column(nullable = false)
    private PaymentStatus payStatus = PaymentStatus.UN_PAY;

    /**
     * 支付单
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy(value = "id asc")
    private List<OrderPayment> payments;


    //订单明细
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items;

    //收货人信息
    @Embedded
    private OrderConsignee consignee;

    //配送信息
    @Embedded
    private OrderShipping shipping;

    //发票信息
    @Embedded
    private OrderInvoice invoice;

    /**
     * 订单备注
     */
    @Column
    private String description;

    /**
     * 总重量
     */
    private Integer totalWeight = 0;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Integer getFreightAmount() {
        return freightAmount;
    }

    public void setFreightAmount(Integer freightAmount) {
        this.freightAmount = freightAmount;
    }

    public Integer getFreeAmount() {
        return freeAmount;
    }

    public void setFreeAmount(Integer freeAmount) {
        this.freeAmount = freeAmount;
    }

    public Integer getVoucherFreeAmount() {
        return voucherFreeAmount;
    }

    public void setVoucherFreeAmount(Integer voucherFreeAmount) {
        this.voucherFreeAmount = voucherFreeAmount;
    }

    public Integer getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Integer payAmount) {
        this.payAmount = payAmount;
    }

    public Integer getPayable() {
        return payable;
    }

    public void setPayable(Integer payable) {
        this.payable = payable;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public PaymentStatus getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(PaymentStatus payStatus) {
        this.payStatus = payStatus;
    }

    public List<OrderPayment> getPayments() {
        return payments;
    }

    public void setPayments(List<OrderPayment> payments) {
        this.payments = payments;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public OrderInvoice getInvoice() {
        return invoice;
    }

    public void setInvoice(OrderInvoice invoice) {
        this.invoice = invoice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Integer totalWeight) {
        this.totalWeight = totalWeight;
    }

    public OrderConsignee getConsignee() {
        return consignee;
    }

    public void setConsignee(OrderConsignee consignee) {
        this.consignee = consignee;
    }

    public OrderShipping getShipping() {
        return shipping;
    }

    public void setShipping(OrderShipping shipping) {
        this.shipping = shipping;
    }
}
