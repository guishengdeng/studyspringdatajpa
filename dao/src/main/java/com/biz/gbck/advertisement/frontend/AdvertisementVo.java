package com.biz.gbck.advertisement.frontend;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by xys on 2017/4/18.
 */
public class AdvertisementVo implements Serializable {

    private static final long serialVersionUID = 6693905559390118477L;

    private String id;

    private String picturesLink;

    private String clickLink;

    private Timestamp beginTimestamp;

    private Timestamp endTimestamp;

    private String residenceTime;

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

    public String getResidenceTime() {
        return residenceTime;
    }

    public void setResidenceTime(String residenceTime) {
        this.residenceTime = residenceTime;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
