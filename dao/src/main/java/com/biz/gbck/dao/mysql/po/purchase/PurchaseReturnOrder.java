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
@Table(name = "pur_order_return", indexes = {@Index(columnList = "buyerId"), @Index(columnList = "orderCode", unique = true)})
public class PurchaseReturnOrder extends BaseEntity {

    private static final long serialVersionUID = 4548084051968768185L;

    /**
     * 申请者Id
     */
    private Long applicantId;

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


}
