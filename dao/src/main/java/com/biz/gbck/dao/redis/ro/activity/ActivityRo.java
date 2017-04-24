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

    /**
     * 创建人
     */
    private String userId;

    /**
     * 显示排序
     */
    private Integer order = 0;

    /**
     * 是否发布到服务器
     */
    private Boolean showInApp = false;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Boolean getShowInApp() {
        return showInApp;
    }

    public void setShowInApp(Boolean showInApp) {
        this.showInApp = showInApp;
    }
}
