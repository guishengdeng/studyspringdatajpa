package com.biz.gbck.vo.stock;

import com.biz.gbck.enums.product.GeoLevelEnum;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 区域库存锁定请求vo
 *
 * @author lei
 */
public class UpdateGeoLockStockRequestVo implements Serializable {
    private static final long serialVersionUID = 7929078907667438874L;

    public static final int DEFAULT_ALIVE_TIME = 24 * 60; //默认失效时间(分钟)

    /**
     * 订单编号
     */
    private String orderCode;

    private List<LockStockItemRequestVo> itemVos;

    /**
     * 省市区Id(必选)
     */
    private Integer geoId;

    /**
     * 锁定级别(默认:省级, 可选, 当前有且只可选省级)
     */
    private GeoLevelEnum geoLevel = GeoLevelEnum.GEO_PROVINCE;

    /**
     * 有效时间(分钟)
     */
    private int aliveTime = DEFAULT_ALIVE_TIME;

    public Integer getGeoId() {
        return geoId;
    }

    public void setGeoId(Integer geoId) {
        this.geoId = geoId;
    }

    public GeoLevelEnum getGeoLevel() {
        return geoLevel;
    }

    public void setGeoLevel(GeoLevelEnum geoLevel) {
        this.geoLevel = geoLevel;
    }

    public List<LockStockItemRequestVo> getItemVos() {
        return itemVos;
    }

    public void setItemVos(List<LockStockItemRequestVo> itemVos) {
        this.itemVos = itemVos;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
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
