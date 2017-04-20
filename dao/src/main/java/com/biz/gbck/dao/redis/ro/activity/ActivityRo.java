package com.biz.gbck.dao.redis.ro.activity;

import com.biz.redis.annotation.Ro;
import com.biz.redis.annotation.RoSortedSet;
import com.biz.redis.bean.BaseRedisObject;

/**
 * Created by xys on 2017/4/18.
 */
@Ro(key = "ac:ActivityRo")
@RoSortedSet(key = "list", score = "createTimestamp")
public class ActivityRo extends BaseRedisObject<String> {

    /**
     * 活动标题
     */
    private String activityTitle;

    /**
     * 活动链接
     */
    private String activityLink;

    /**
     * 活动图片
     */
    private String activityPicture;

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
