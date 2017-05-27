package com.biz.gbck.vo.order.req;

import com.biz.gbck.enums.order.OrderStatus;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by lzz on 2017/5/23.
 */
public class SalesQueryVo implements Serializable {

    private static final long serialVersionUID = -2593111130654695209L;

    /**
     * 订单号
     */
    private String orderCode;

    /**
     * 订单日期
     */
    private Timestamp orderTime;

    /**
     *发货时间
     */
    private Date deliveryTime;

    /**
     * 销售金额
     */
    private Integer orderAmount;

    /**
     * 状态
     */
    private OrderStatus status;

    /**
     * 原单号
     */
    private String code;

    /**
     * 会员账号（店铺手机号）
     */
    private String mobile;

    /**
     * 会员地址(收货地址)
     */
    private String address;

    /**
     * 对应店铺Id
     */
    private Long shopId;

    @Min(1)
    private Integer page =1;

    @Max(100)
    private Integer pageSize = 20;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Integer getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

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
}
