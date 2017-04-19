package com.biz.gbck.vo.depot;

import com.biz.gbck.enums.depot.DepotType;
import com.biz.gbck.enums.depot.PeriodsOfDeliveriesTime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;

/**
 * 根据门店经纬度获取最近门店或者省仓门店的返回Vo
 *
 * @author zhangcheng
 * @date 2017/1/23
 * @reviewer
 * @see
 */
public class SearchDepotResVo implements Serializable {

    private static final long serialVersionUID = -1995364088798846799L;

    /**
     * 最近门店id
     */
    private String id;

    /**
     * 附近门店编码
     */
    private String depotCode;

    /**
     * 附近门店名称
     */
    private String name;

    /**
     * 附近门店预计送达时间
     * (如果附近有门店，则该字段为：19分钟，29分钟，59分钟，24小时内发货
     * 如果没有门店则该字段直接为24小时内发货)
     */
    private PeriodsOfDeliveriesTime deliveriesTime;

    /**
     * 附近门店经度
     * 无论附近门店还是省仓门店必填
     */
    private BigDecimal depotLongitude;

    /**
     * 附近门店纬度
     * 无论附近门店还是省仓门店必填
     */
    private BigDecimal depotLatitude;

    /**
     * 附近是否有门店（false：附近没有门店，true：附近有门店）
     */
    private Boolean lackNearDepot = Boolean.TRUE;

    /**
     * 门店开始营业时间
     */
    private Time beginBusinessTime;

    /**
     * 门店结束营业时间
     */
    private Time endBusinessTime;

    /**
     * 门店类型
     */
    private DepotType type;

    /**
     * 距离
     */
    private Double distance;

    public Time getEndBusinessTime() {
        return endBusinessTime;
    }

    public void setEndBusinessTime(Time endBusinessTime) {
        this.endBusinessTime = endBusinessTime;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public DepotType getType() {
        return type;
    }

    public void setType(DepotType type) {
        this.type = type;
    }

    public Time getBeginBusinessTime() {
        return beginBusinessTime;
    }

    public void setBeginBusinessTime(Time beginBusinessTime) {
        this.beginBusinessTime = beginBusinessTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepotCode() {
        return depotCode;
    }

    public void setDepotCode(String depotCode) {
        this.depotCode = depotCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PeriodsOfDeliveriesTime getDeliveriesTime() {
        return deliveriesTime;
    }

    public void setDeliveriesTime(PeriodsOfDeliveriesTime deliveriesTime) {
        this.deliveriesTime = deliveriesTime;
    }

    public String getDeliveriesTimeText() {
        return deliveriesTime.getTitle();
    }

    public BigDecimal getDepotLongitude() {
        return depotLongitude;
    }

    public void setDepotLongitude(BigDecimal depotLongitude) {
        this.depotLongitude = depotLongitude;
    }

    public BigDecimal getDepotLatitude() {
        return depotLatitude;
    }

    public void setDepotLatitude(BigDecimal depotLatitude) {
        this.depotLatitude = depotLatitude;
    }

    public Boolean getLackNearDepot() {
        return lackNearDepot;
    }

    public void setLackNearDepot(Boolean lackNearDepot) {
        this.lackNearDepot = lackNearDepot;
    }
}
