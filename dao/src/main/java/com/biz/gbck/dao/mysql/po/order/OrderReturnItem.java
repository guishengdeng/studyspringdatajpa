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
@Table(name = "ord_order_return_item")
public class OrderReturnItem extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "return_id")
    private OrderReturn orderReturn;

    //订单明细
    @OneToOne(optional = false)
    @JoinColumn(name = "item_id")
    private OrderItem item;

    /**
     * 退货数量(不超过商品购买数量)
     */
    private Integer returnQuantity;

    public OrderReturn getOrderReturn() {
        return orderReturn;
    }

    public void setOrderReturn(OrderReturn orderReturn) {
        this.orderReturn = orderReturn;
    }

    public OrderItem getItem() {
        return item;
    }

    public void setItem(OrderItem item) {
        this.item = item;
    }

    public Integer getReturnQuantity() {
        return returnQuantity;
    }

    public void setReturnQuantity(Integer returnQuantity) {
        this.returnQuantity = returnQuantity;
    }
}
