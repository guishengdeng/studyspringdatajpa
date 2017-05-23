package com.biz.gbck.dao.mysql.po.purchase;

import com.biz.support.jpa.po.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * 收货单明细
 *
 * @author lei
 * @date 2017年04月20日
 * @reviewer
 * @see
 */
@Entity
@Table(name = "pur_recipient_item")
public class RecipientOrderItem extends BaseEntity {

    private static final long serialVersionUID = 8892140517297834694L;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_order_id")
    private RecipientOrder recipientOrder;

    @Embedded
    private ProductItem productItem;

    public RecipientOrder getRecipientOrder() {
        return recipientOrder;
    }

    public void setRecipientOrder(RecipientOrder recipientOrder) {
        this.recipientOrder = recipientOrder;
    }

    public ProductItem getProductItem() {
        return productItem;
    }

    public void setProductItem(ProductItem productItem) {
        this.productItem = productItem;
    }
}
