package com.biz.gbck.dao.mysql.po.order;

import com.biz.support.jpa.po.BaseEntity;
import javax.persistence.*;

/**
 * 订单退货单明细
 *
 * @author yanweijin
 * @date 2016/12/27
 */
@Entity
@Table(name = "ord_order_refund_item")
public class OrderRefundItem extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "refund_id")
    private OrderRefund orderRefund;

    //订单明细
    @OneToOne(optional = false)
    @JoinColumn(name = "item_id")
    private OrderItem item;

    /**
     * 退货数量(不超过商品购买数量)
     */
    private Integer refundQuantity;

    public OrderRefund getOrderRefund() {
        return orderRefund;
    }

    public void setOrderRefund(OrderRefund orderRefund) {
        this.orderRefund = orderRefund;
    }

    public OrderItem getItem() {
        return item;
    }

    public void setItem(OrderItem item) {
        this.item = item;
    }

    public Integer getRefundQuantity() {
        return refundQuantity;
    }

    public void setRefundQuantity(Integer refundQuantity) {
        this.refundQuantity = refundQuantity;
    }
}
