package com.biz.gbck.dao.mysql.po.order;

import com.biz.support.jpa.po.BaseEntity;
import javax.persistence.*;

/**
 * 订单使用的优惠券
 *
 * @author lei
 * @date 2017年05月20日
 * @reviewer
 * @see
 */
@Entity
@Table(name = "ord_order_coupon")
public class OrderCoupon extends BaseEntity {

    private static final long serialVersionUID = 3603128522862210059L;

    @ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    protected Order order;

    //优惠券id
    @Column(nullable = false)
    private Long voucherId;

    //优惠券类型id
    @Column(nullable = false)
    private Long voucherTypeId;

    //使用数量
    @Column(nullable = false)
    private Integer quantity;

    //优惠券抵付金额(不一定是面值，是优惠券抵的金额，如果优惠券是5元，用户订单 是3元 这里 3元)
    @Column(nullable = false)
    private Integer offsetAmount = 0;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Long getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Long voucherId) {
        this.voucherId = voucherId;
    }

    public Long getVoucherTypeId() {
        return voucherTypeId;
    }

    public void setVoucherTypeId(Long voucherTypeId) {
        this.voucherTypeId = voucherTypeId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getOffsetAmount() {
        return offsetAmount;
    }

    public void setOffsetAmount(Integer offsetAmount) {
        this.offsetAmount = offsetAmount;
    }
}
