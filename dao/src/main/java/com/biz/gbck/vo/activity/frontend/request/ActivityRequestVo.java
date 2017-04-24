package com.biz.gbck.vo.activity.frontend.request;

import java.io.Serializable;

/**
 * Created by xys on 2017/4/20.
 */
public class ActivityRequestVo implements Serializable {

    private static final long serialVersionUID = 5462603895128701236L;

    private String id;

    private String activityTitle;

    private String activityLink;

    private String activityPicture;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getActivityLink() {
        return activityLink;
    }

    public void setActivityLink(String activityLink) {
        this.activityLink = activityLink;
    }

    public String getActivityPicture() {
        return activityPicture;
    }

    public void setActivityPicture(String activityPicture) {
        this.activityPicture = activityPicture;
    }
}
