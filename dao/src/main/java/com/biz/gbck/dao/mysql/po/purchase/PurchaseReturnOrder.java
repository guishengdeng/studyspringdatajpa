package com.biz.gbck.dao.mysql.po.purchase;

import com.biz.gbck.enums.purchase.PurchaseOrderStatus;
import com.biz.support.jpa.po.BaseEntity;

import javax.persistence.*;
import java.util.List;

/**
 * 采购退货单
 *
 * @author lei
 * @date 2017年04月21日
 * @reviewer
 * @see
 */
@Entity
@Table(name = "pur_order_return", indexes = {@Index(columnList = "idx_returnCode", unique = true)})
public class PurchaseReturnOrder extends BaseEntity {

    private static final long serialVersionUID = 4548084051968768185L;

    @ManyToOne(optional = false)
    @JoinColumn(name = "purchase_order_id")
    private PurchaseOrder purchaseOrder;

    //退货单编号
    @Column(length = 50, unique = true, nullable = false)
    private String returnCode;

    /**
     * 状态
     */
    @Convert(converter = PurchaseOrderStatus.Converter.class)
    private PurchaseOrderStatus status = PurchaseOrderStatus.NEW;

    /**
     * 退款单明细
     */
    @OneToMany(mappedBy = "purchaseReturnOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PurchaseOrderReturnItem> items;

    /**
     * 审核
     */
    @Embedded
    private PurchaseAudit audit;


    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public PurchaseOrderStatus getStatus() {
        return status;
    }

    public void setStatus(PurchaseOrderStatus status) {
        this.status = status;
    }

    public List<PurchaseOrderReturnItem> getItems() {
        return items;
    }

    public void setItems(List<PurchaseOrderReturnItem> items) {
        this.items = items;
    }

    public PurchaseAudit getAudit() {
        return audit;
    }

    public void setAudit(PurchaseAudit audit) {
        this.audit = audit;
    }
}
