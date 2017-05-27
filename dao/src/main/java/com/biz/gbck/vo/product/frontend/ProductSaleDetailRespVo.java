package com.biz.gbck.vo.product.frontend;

import com.biz.gbck.enums.product.ProductUnit;
import com.biz.gbck.enums.purchase.PurchaseOrderStatus;

import java.sql.Timestamp;

/**
 * ProductSaleDetailRespVo
 *
 * @author guisheng.deng
 * @date 2017年05月26日
 * @reviewer
 * @description
 * @see
 */
public class ProductSaleDetailRespVo {

    private String orderCode;
    private PurchaseOrderStatus status;
    private Timestamp createTimeStamp;
    /**
     * 出库单号
     */
    private String sn;
    private Timestamp deliveryTime;
    private String productCode;
    //商品名称
    private String name;
    //单位
    private ProductUnit unit;
    //数量
    private Integer quantity;

    private Integer price;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public PurchaseOrderStatus getStatus() {
        return status;
    }

    public void setStatus(PurchaseOrderStatus status) {
        this.status = status;
    }

    public Timestamp getCreateTimeStamp() {
        return createTimeStamp;
    }

    public void setCreateTimeStamp(Timestamp createTimeStamp) {
        this.createTimeStamp = createTimeStamp;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Timestamp getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Timestamp deliveryTime) {
        this.deliveryTime = deliveryTime;
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

    public ProductUnit getUnit() {
        return unit;
    }

    public void setUnit(ProductUnit unit) {
        this.unit = unit;
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
    public ProductSaleDetailRespVo(){

    }
}