package com.biz.gbck.dao.redis.ro.advertisement;

import com.biz.gbck.dao.mysql.po.info.PromotionPo;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.redis.annotation.Ro;
import com.biz.redis.annotation.RoSortedSet;
import com.biz.redis.bean.BaseRedisObject;

import java.sql.Timestamp;

/**
 * 广告ro
 *
 * Created by xys on 2017/4/18.
 */
@Ro(key = "ad:AdvertisementRo")
@RoSortedSet(key = "list", score = "createTimestamp")
public class AdvertisementRo extends BaseRedisObject<String> implements Comparable<AdvertisementRo>{

    /**
     * 图片链接
     */
    private String picturesLink;

    /**
     * 点击链接
     */
    private String clickLink;

    /**
     * 广告生效时间
     */
    private Timestamp beginTimestamp;

    /**
     * 广告过期时间
     */
    private Timestamp endTimestamp;

    /**
     * 停留(毫秒)
     */
    private Long residenceTime;

    /**
     * 优先级
     */
    private Integer priority;

    private Integer status = CommonStatusEnum.ENABLE.getValue();

    /**
     * 提供默认活动排序功能
     */
    @Override
    public int compareTo(AdvertisementRo o) {
        int compareTo = this.getPriority().compareTo(o.getPriority());
        if (compareTo != 0) {
            return compareTo;
        }else {
            return o.getCreateTimestamp().compareTo(this.getCreateTimestamp());
        }
    }

    public String getPicturesLink() {
        return picturesLink;
    }

    public void setPicturesLink(String picturesLink) {
        this.picturesLink = picturesLink;
    }

    public String getClickLink() {
        return clickLink;
    }

    public void setClickLink(String clickLink) {
        this.clickLink = clickLink;
    }

    public Timestamp getBeginTimestamp() {
        return beginTimestamp;
    }

    public void setBeginTimestamp(Timestamp beginTimestamp) {
        this.beginTimestamp = beginTimestamp;
    }

    public Timestamp getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(Timestamp endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    public Long getResidenceTime() {
        return residenceTime;
    }

    public void setResidenceTime(Long residenceTime) {
        this.residenceTime = residenceTime;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
