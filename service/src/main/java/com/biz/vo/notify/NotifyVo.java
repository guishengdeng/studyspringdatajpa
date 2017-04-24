package com.biz.vo.notify;


import com.biz.gbck.common.ro.AbstractRedisObj;

/**
 * Created by david-liu on 2016/03/30 14:51.
 */
public class NotifyVo extends AbstractRedisObj {
    private String title;

    private String notifyContent;

    private String mobile;

    private String shopTypeId;

    public NotifyVo() {
    }

    public NotifyVo(String title, String notifyContent, String mobile, String shopTypeId) {
        this.title = title;
        this.notifyContent = notifyContent;
        this.mobile = mobile;
        this.shopTypeId = shopTypeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotifyContent() {
        return notifyContent;
    }

    public void setNotifyContent(String notifyContent) {
        this.notifyContent = notifyContent;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getShopTypeId() {
        return shopTypeId;
    }

    public void setShopTypeId(String shopTypeId) {
        this.shopTypeId = shopTypeId;
    }
}
