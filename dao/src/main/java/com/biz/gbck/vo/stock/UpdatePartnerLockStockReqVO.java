package com.biz.gbck.vo.stock;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * 更新合伙人锁定库存请求vo
 *
 * @author lei
 */
public class UpdatePartnerLockStockReqVO implements Serializable {
    private static final long serialVersionUID = 1740147389692009015L;

    public static final int DEFAULT_ALIVE_TIME = 60; //默认失效时间

    /**
     * 订单编号
     */
    private String orderCode;

    /**
     * 合伙人id(必选)
     */
    private Long partnerId;


    /**
     * 锁定库存明细
     */
    private List<StockItemVO> items = newArrayList();

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

    public UpdatePartnerLockStockReqVO(String orderCode, Long partnerId, List<StockItemVO> items) {
        this.orderCode = orderCode;
        this.partnerId = partnerId;
        this.items = items;
    }

    public Long getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }

    public List<StockItemVO> getItems() {
        return items;
    }

    public void setItems(List<StockItemVO> items) {
        this.items = items;
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
