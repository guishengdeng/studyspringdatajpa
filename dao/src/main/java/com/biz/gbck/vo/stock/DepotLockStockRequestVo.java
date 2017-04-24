package com.biz.gbck.vo.stock;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 门店锁定库存请求vo
 *
 * @author lei
 */
public class DepotLockStockRequestVo implements Serializable {
    public static final int DEFAULT_ALIVE_TIME = 24 * 60; //默认失效时间
    private static final long serialVersionUID = -6059851300298157347L;
    /**
     * 订单编号(必选)
     */
    private String orderCode;

    /**
     * 门店编号(必选)
     */
    private String depotCode;

    /**
     * 明细
     */
    private List<LockStockItemRequestVo> itemVos;

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

    public String getDepotCode() {
        return depotCode;
    }

    public void setDepotCode(String depotCode) {
        this.depotCode = depotCode;
    }

    public List<LockStockItemRequestVo> getItemVos() {
        return itemVos;
    }

    public void setItemVos(List<LockStockItemRequestVo> itemVos) {
        this.itemVos = itemVos;
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
