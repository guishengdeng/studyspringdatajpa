package com.biz.gbck.dao.mysql.po.purchase;

import com.biz.support.jpa.po.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * 采购退货单明细
 *
 * @author lei
 * @date 2017年04月20日
 * @reviewer
 * @see
 */
@Entity
@Table(name = "pur_order_return_item")
public class PurchaseOrderReturnItem extends BaseEntity {

    private static final long serialVersionUID = 8892140517297834694L;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_return_id")
    private PurchaseReturnOrder purchaseReturnOrder;

    //订单明细
    @OneToOne(optional = false)
    @JoinColumn(name = "item_id")
    private PurchaseOrderItem item;

    /**
     * 退货数量(不超过商品购买数量)
     */
    private Integer returnQuantity;

     /**
     * 退货单价
     */
    private Integer returnPrice ;


    public PurchaseReturnOrder getPurchaseReturnOrder() {
        return purchaseReturnOrder;
    }

    public void setPurchaseReturnOrder(PurchaseReturnOrder purchaseReturnOrder) {
        this.purchaseReturnOrder = purchaseReturnOrder;
    }

    public PurchaseOrderItem getItem() {
        return item;
    }

    public void setItem(PurchaseOrderItem item) {
        this.item = item;
    }

    public Integer getReturnQuantity() {
        return returnQuantity;
    }

    public void setReturnQuantity(Integer returnQuantity) {
        this.returnQuantity = returnQuantity;
    }

    public Integer getReturnPrice() {
        return returnPrice;
    }

    public void setReturnPrice(Integer returnPrice) {
        this.returnPrice = returnPrice;
    }
}
