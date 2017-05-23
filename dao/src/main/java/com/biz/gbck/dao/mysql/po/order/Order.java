package com.biz.gbck.dao.mysql.po.order;

import com.biz.core.util.DateUtil;
import com.biz.gbck.enums.order.OrderStatus;
import com.biz.gbck.enums.order.PaymentStatus;
import com.biz.gbck.enums.order.PaymentType;
import com.biz.support.jpa.po.BaseEntity;

import javax.persistence.*;
import java.sql.Timestamp;
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

    /**
     * 对应店铺Id
     */
    @Column(nullable = false)
    private Long shopId;

    /**
     * 平台公司Id
     */
    @Column(nullable = false)
    private Long companyId;


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
    private Integer freight = 0;

    /**
     * 促销优惠金额
     */
    @Column(nullable = false)
    private Integer freeAmount = 0;

    /**
     * 优惠券抵付金额
     */
    @Column(nullable = false)
    private Integer voucherAmount = 0;

    /**
     * 支付金额
     */
    @Column(nullable = false)
    private Integer payAmount = 0;

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

    /**
     * 付款单详情(支付单使用)
     */
    @Column
    private String subject;

    /**
     * 过期时间
     */
    private Timestamp expireTimestamp;


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

    /**
     * 冗余退货单Id
     */
    private Long orderReturnId;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
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

    public Integer getFreight() {
        return freight;
    }

    public void setFreight(Integer freightAmount) {
        this.freight = freightAmount;
    }

    public Integer getFreeAmount() {
        return freeAmount;
    }

    public void setFreeAmount(Integer freeAmount) {
        this.freeAmount = freeAmount;
    }

    public Integer getVoucherAmount() {
        return voucherAmount;
    }

    public void setVoucherAmount(Integer voucherFreeAmount) {
        this.voucherAmount = voucherFreeAmount;
    }

    public Integer getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Integer payAmount) {
        this.payAmount = payAmount;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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

    public Timestamp getExpireTimestamp() {
        return expireTimestamp;
    }

    public void setExpireTimestamp(Timestamp expireTimestamp) {
        this.expireTimestamp = expireTimestamp;
    }

    public Long getOrderReturnId() {
        return orderReturnId;
    }

    public void setOrderReturnId(Long orderReturnId) {
        this.orderReturnId = orderReturnId;
    }

    /**
     * 是否可支付
     * @return
     */
    public boolean isPayable() {
        return this.status == OrderStatus.PRE_PAY && this.payStatus == PaymentStatus.CREATE_PAYMENT && DateUtil
                .isBefore(expireTimestamp);
    }


    /**
     * 判断是否 超过付款期限（服务端使用，不给客户端返回）
     */
    public boolean isPayTimeout() {
        return status == OrderStatus.CREATED && payStatus == PaymentStatus.UN_PAY && System.currentTimeMillis() >
                (this.expireTimestamp == null ? getCreateTimestamp() == null ? 0 : getCreateTimestamp().getTime() :
                        this.expireTimestamp.getTime());
    }

    /**
     * 是否可取消
     * @param isAdmin 是否管理人员
     * @return
     */
    public boolean isCancelable(boolean isAdmin) {
        switch (this.status) {
            case CREATED:
                return true;
            case PRE_PAY:
                return true;
        }
        return false;
    }

    /**
     * 是否可联系客服
     *
     * @return
     */
    public boolean isContactable() {
        return status == OrderStatus.ORDERED || status == OrderStatus.DELIVERED || status == OrderStatus.FINISHED ||
                payStatus == PaymentStatus.PAYED;
    }

    /**
     * 是否可以再次购买
     */
    public boolean isBuyAgain() {
        return status == OrderStatus.FINISHED;
    }

    /**
     * 是否可以申请售后
     */
    public boolean isReturnable(boolean isAdmin) {
        if (isAdmin)
            return status == OrderStatus.DELIVERED || status == OrderStatus.FINISHED;
        return status == OrderStatus.FINISHED;
    }
}
