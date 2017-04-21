package com.biz.gbck.activity.frontend;

import java.io.Serializable;

/**
 * Created by xys on 2017/4/18.
 */
public class ActivityVo implements Serializable {

    private static final long serialVersionUID = 810810602964154699L;

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
