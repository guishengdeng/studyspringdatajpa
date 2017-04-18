package com.biz.gbck.vo.stock;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 独立商品库存锁定请求vo
 *
 * @author lei
 */
public class UpdateLockStockRequestVo implements Serializable {
    private static final long serialVersionUID = -1255509936241623105L;

    public static final int DEFAULT_ALIVE_TIME = 24 * 60; //默认失效时间

    /**
     * 订单编号
     */
    private String orderCode;

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
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
