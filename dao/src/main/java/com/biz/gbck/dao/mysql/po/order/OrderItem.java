package com.biz.gbck.dao.mysql.po.order;

import com.biz.gbck.enums.order.ItemType;
import com.biz.support.jpa.po.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

/**
 * 订单明细
 *
 * @author lei
 * @date 2017年04月20日
 * @reviewer
 * @see
 */
@Entity
@Table(name = "ord_order_item")
public class OrderItem extends BaseEntity {

    private static final long serialVersionUID = 8892140517297834694L;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

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
    @Column(nullable = false)
    private String name;

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
     * 成本价
     */
    @Column(nullable = false)
    private Integer costPrice;

    /**
     * 商品类型
     */
    @Enumerated
    private ItemType itemType = ItemType.NORMAL;


    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Integer costPrice) {
        this.costPrice = costPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }
}
