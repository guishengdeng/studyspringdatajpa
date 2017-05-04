package com.biz.gbck.dao.mysql.po.purchase;

import com.biz.gbck.enums.product.ProductUnit;
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

    @Embedded
    private ProductItem productItem;

    public PurchaseReturnOrder getPurchaseReturnOrder() {
        return purchaseReturnOrder;
    }

    public void setPurchaseReturnOrder(PurchaseReturnOrder purchaseReturnOrder) {
        this.purchaseReturnOrder = purchaseReturnOrder;
    }

    public ProductItem getProductItem() {
        return productItem;
    }

    public void setProductItem(ProductItem productItem) {
        this.productItem = productItem;
    }
}
