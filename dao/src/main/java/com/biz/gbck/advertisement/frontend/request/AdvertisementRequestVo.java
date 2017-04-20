package com.biz.gbck.advertisement.frontend.request;

import java.io.Serializable;

/**
 * Created by xys on 2017/4/18.
 */
public class AdvertisementRequestVo implements Serializable {

    private static final long serialVersionUID = 6693905559390118477L;

    private String id;

    private String picturesLink;

    private String clickLink;

    private String beginTimestamp;

    private String endTimestamp;

    private Long residenceTime;

    private Integer priority;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getBeginTimestamp() {
        return beginTimestamp;
    }

    public void setBeginTimestamp(String beginTimestamp) {
        this.beginTimestamp = beginTimestamp;
    }

    public String getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(String endTimestamp) {
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
