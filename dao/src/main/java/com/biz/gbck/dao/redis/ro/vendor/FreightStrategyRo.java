package com.biz.gbck.dao.redis.ro.vendor;

import com.biz.redis.annotation.Ro;
import com.biz.redis.annotation.RoSortedSet;
import com.biz.redis.bean.BaseRedisObject;
import java.sql.Timestamp;

@Ro(key = "ro:FreightStrategyRo")
@RoSortedSet(key = "list", score = "id")
public class FreightStrategyRo extends BaseRedisObject<Long> implements Comparable<FreightStrategyRo> {
    /** 创建日期 */
    private Timestamp createTimestamp;

    //店铺id
    private Long vendorId;

    private String template;

    // 是否启用,ture:是,false:否
    private boolean enable = true;

    // 首重,单位克
    private Integer firstWeight;

    // 续重,单位克
    private Integer nextWeight;

    // 首费,单位分
    private Integer firstPrice;

    // 续费,单位分
    private Integer nextPrice;

    // 满 xxx 包邮,如果设置为0,则无条件包邮,如果设置为负数,则不包邮;单位分
    private Integer freeIfExceedPrice;

    // 是否逻辑删除,true:是(用户看不到),false:否
    private boolean deleted = false;

    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }


    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }


    public Integer getNextWeight() {
        return nextWeight;
    }

    public void setNextWeight(Integer nextWeight) {
        this.nextWeight = nextWeight;
    }


    public Integer getFirstWeight() {
        return firstWeight;
    }

    public void setFirstWeight(Integer firstWeight) {
        this.firstWeight = firstWeight;
    }

    public Integer getFirstPrice() {
        return firstPrice;
    }

    public void setFirstPrice(Integer firstPrice) {
        this.firstPrice = firstPrice;
    }

    public Integer getNextPrice() {
        return nextPrice;
    }

    public void setNextPrice(Integer nextPrice) {
        this.nextPrice = nextPrice;
    }

    public Integer getFreeIfExceedPrice() {
        return freeIfExceedPrice;
    }

    public void setFreeIfExceedPrice(Integer freeIfExceedPrice) {
        this.freeIfExceedPrice = freeIfExceedPrice;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public int compareTo(FreightStrategyRo o) {
        return -this.getId().compareTo(o.getId());
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

}
