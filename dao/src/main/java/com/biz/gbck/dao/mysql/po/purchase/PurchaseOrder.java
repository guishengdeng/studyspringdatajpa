package com.biz.gbck.dao.mysql.po.purchase;

import com.biz.gbck.dao.mysql.po.order.OrderConsignee;
import com.biz.gbck.dao.mysql.po.order.OrderShipping;
import com.biz.gbck.enums.purchase.PurchaseOrderStatus;
import com.biz.support.jpa.po.BaseEntity;

import javax.persistence.*;
import java.util.List;

/**
 * 采购单
 *
 * @author lei
 * @date 2017年04月21日
 * @reviewer
 * @see
 */
@Entity
@Table(name = "pur_order", indexes = {@Index(columnList = "buyerId"), @Index(columnList = "orderCode", unique = true)})
public class PurchaseOrder extends BaseEntity {

    private static final long serialVersionUID = 4548084051968768185L;

    //采购单号
    @Column(length = 50, unique = true, nullable = false)
    private String orderCode;

    /**
     * 卖家Id
     */
    @Column(nullable = false)
    private Long sellerId;


    // 采购者 Id
    @Column(nullable = false)
    private Long buyerId;

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

    //订单应付款(orderAmount + freightAmount - voucherFreeAmount)
    @Column(nullable = false)
    private Integer payable = 0;

    /**
     * 采购单状态
     */
    @Convert(converter = PurchaseOrderStatus.Converter.class)
    @Column(nullable = false)
    private PurchaseOrderStatus status = PurchaseOrderStatus.NEW;


    //采购单明细
    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PurchaseOrderItem> items;

    //收货人信息
    @Embedded
    private OrderConsignee consignee;

    //配送信息
    @Embedded
    private OrderShipping shipping;

    @Embedded
    private PurchaseAudit audit;

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

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
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

    public Integer getPayable() {
        return payable;
    }

    public void setPayable(Integer payable) {
        this.payable = payable;
    }

    public PurchaseOrderStatus getStatus() {
        return status;
    }

    public void setStatus(PurchaseOrderStatus status) {
        this.status = status;
    }

    public List<PurchaseOrderItem> getItems() {
        return items;
    }

    public void setItems(List<PurchaseOrderItem> items) {
        this.items = items;
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

    public PurchaseAudit getAudit() {
        return audit;
    }

    public void setAudit(PurchaseAudit audit) {
        this.audit = audit;
    }
}
