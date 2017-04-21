package com.biz.gbck.dao.mysql.po.purchase;

import com.biz.gbck.enums.product.ProductUnit;
import com.biz.support.jpa.po.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

/**
 * 采购单明细
 *
 * @author lei
 * @date 2017年04月20日
 * @reviewer
 * @see
 */
@Entity
@Table(name = "pur_order_item")
public class PurchaseOrderItem extends BaseEntity {

    private static final long serialVersionUID = 8892140517297834694L;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_order_id")
    private PurchaseOrder purchaseOrder;

    /**
     * 商品Id
     */
    private Long productId;

    /**
     * 中台商品编号
     */
    @Column(length = 50)
    private String productCode;//商品编码

    /**
     * 商品名称
     */
    @Column(length = 50, nullable = false)
    private String name;

    /**
     * 规格
     */
    @Column(length = 50, nullable = false)
    private String standard;

    /**
     * 单位
     */
    @Column
    @Enumerated(EnumType.STRING)
    private ProductUnit unit;

    /**
     * 包装
     */
    @Column(length = 20)
    private String pack;

    /**
     * 商品图片
     */
    @Column(length = 100)
    private String logo;

    /**
     * 数量
     */
    @Column(nullable = false)
    private Integer quantity;

    /**
     * 价格
     */
    @Column(nullable = false)
    private Integer price;

    /**
     * 促销优惠价格
     */
    @Column(nullable = false)
    private Integer freeAmount;

    /**
     * 成本价
     */
    @Column(nullable = false)
    private Integer costPrice;


    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public ProductUnit getUnit() {
        return unit;
    }

    public void setUnit(ProductUnit unit) {
        this.unit = unit;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getFreeAmount() {
        return freeAmount;
    }

    public void setFreeAmount(Integer freeAmount) {
        this.freeAmount = freeAmount;
    }

    public Integer getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Integer costPrice) {
        this.costPrice = costPrice;
    }
}
