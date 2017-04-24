package com.biz.gbck.dao.redis.ro.advertisement;

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
public class AdvertisementRo extends BaseRedisObject<String> {

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
}
