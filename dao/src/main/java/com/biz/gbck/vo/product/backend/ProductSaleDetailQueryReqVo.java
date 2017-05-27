package com.biz.gbck.vo.product.backend;

import com.biz.gbck.enums.purchase.PurchaseOrderStatus;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * ProductSaleDetailQueryReqVo
 *
 * @author guisheng.deng
 * @date 2017年05月26日
 * @reviewer
 * @description
 * @see
 */
public class ProductSaleDetailQueryReqVo implements Serializable {
    /**
     *订单号
     */
    private String orderCode;
    /**
     *制单开始日期
     */
    private String  startTimeStamp;
    /**
     *制单结束日期
     */
    private String  endTimeStamp;
    /**
     *出库开始日期
     */
    private String  deliveryStartTimeStamp;
    /**
     *出库结束日期
     */
    private String deliveryEndTimeStamp;
    /**
     *商品编号
     */
    private String productCode;
    /**
     *商品名称
     */
    private String productName;
    /**
     *订单状态
     */
    private PurchaseOrderStatus status;
    @Min(1)
    private Integer page = 1;

    @Max(100)
    private Integer pageSize = 20;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getStartTimeStamp() {
        return startTimeStamp;
    }

    public void setStartTimeStamp(String startTimeStamp) {
        this.startTimeStamp = startTimeStamp;
    }

    public String getEndTimeStamp() {
        return endTimeStamp;
    }

    public void setEndTimeStamp(String endTimeStamp) {
        this.endTimeStamp = endTimeStamp;
    }

    public String getDeliveryStartTimeStamp() {
        return deliveryStartTimeStamp;
    }

    public void setDeliveryStartTimeStamp(String deliveryStartTimeStamp) {
        this.deliveryStartTimeStamp = deliveryStartTimeStamp;
    }

    public String getDeliveryEndTimeStamp() {
        return deliveryEndTimeStamp;
    }

    public void setDeliveryEndTimeStamp(String deliveryEndTimeStamp) {
        this.deliveryEndTimeStamp = deliveryEndTimeStamp;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public PurchaseOrderStatus getStatus() {
        return status;
    }

    public void setStatus(PurchaseOrderStatus status) {
        this.status = status;
    }
}