package com.biz.gbck.vo.stock;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * 更新合伙人锁定库存请求vo
 *
 * @author lei
 */
public class UpdatePartnerLockStockReqVO implements Serializable {
    private static final long serialVersionUID = 1740147389692009015L;

    public static final int DEFAULT_ALIVE_TIME = 24 * 60; //默认失效时间

    /**
     * 订单编号
     */
    private String orderCode;

    /**
     * 商品id(必选)
     */
    private Long productId;

    /**
     * 合伙人id(必选)
     */
    private Long partnerId;

    /**
     * 库存更新数量(正数即加库存, 负数即减库存)
     */
    private int quantity = 0;

    /**
     * 有效时间(分钟)
     */
    private int aliveTime = DEFAULT_ALIVE_TIME;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public UpdatePartnerLockStockReqVO() {
    }

    public UpdatePartnerLockStockReqVO(String orderCode, Long productId, Long partnerId, int quantity) {
        this.orderCode = orderCode;
        this.productId = productId;
        this.partnerId = partnerId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAliveTime() {
        return aliveTime;
    }

    public void setAliveTime(int aliveTime) {
        this.aliveTime = aliveTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
