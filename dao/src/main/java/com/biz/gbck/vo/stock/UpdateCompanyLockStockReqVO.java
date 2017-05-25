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
public class UpdateCompanyLockStockReqVO implements Serializable {
    private static final long serialVersionUID = 1740147389692009015L;

    public static final int DEFAULT_ALIVE_TIME = 60; //默认失效时间

    /**
     * 订单编号
     */
    private String orderCode;

    /**
     * 合伙人id(必选)
     */
    private Long companyId;


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

    public UpdateCompanyLockStockReqVO() {
    }

    public UpdateCompanyLockStockReqVO(String orderCode, Long companyId, List<StockItemVO> items) {
        this();
        this.orderCode = orderCode;
        this.companyId = companyId;
        this.items = items;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
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
