package com.biz.gbck.vo.order.resp;

import com.biz.gbck.enums.order.PaymentStatus;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.util.Date;

/**
 * OrderResponseVo
 *
 * @author guisheng.deng
 * @date 2017年05月18日
 * @reviewer
 * @description
 * @see
 */
public class OrderResponseVo {
     private Long id;
     private String platFormCompanyName;
     private String partnerName;
     private Timestamp orderDate;
    /**
     * 付款状态
     */
    private PaymentStatus payStatus;
    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    private String orderCode;
     private Integer orderAmount;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlatFormCompanyName() {
        return platFormCompanyName;
    }

    public void setPlatFormCompanyName(String platFormCompanyName) {
        this.platFormCompanyName = platFormCompanyName;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }



    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Integer getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    public PaymentStatus getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(PaymentStatus payStatus) {
        this.payStatus = payStatus;
    }
}