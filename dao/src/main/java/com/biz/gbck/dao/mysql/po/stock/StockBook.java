package com.biz.gbck.dao.mysql.po.stock;

import com.biz.support.jpa.po.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 库存流水表
 *
 * @author lei
 * @date 2017年04月24日
 * @reviewer
 * @see
 */
@Entity
@Table(name = "sto_stock_book")
public class StockBook extends BaseEntity {

    /**
     * 送货方Id
     */
    @Column(nullable = false)
    private Long shipperId;

    /**
     * 收货方Id
     */
    @Column(nullable = false)
    private Long receiverId;

    /**
     * 订单Id(出入库单Id)
     */
    private Long orderId;

    /**
     * 商品Id
     */
    @Column(nullable = false)
    private Long productId;

    /**
     * 商品编码
     */
    @Column(length = 20, nullable = false)
    private String productCode;

    /**
     * 行号
     */
    private Integer row;

    /**
     * 变动数量
     */
    @Column(nullable = false)
    private Integer quantity;

    /**
     * 变动时间
     */
    private Timestamp changeTime;

    public Long getShipperId() {
        return shipperId;
    }

    public void setShipperId(Long shipperId) {
        this.shipperId = shipperId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Timestamp getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Timestamp changeTime) {
        this.changeTime = changeTime;
    }
}
