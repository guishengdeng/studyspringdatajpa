package com.biz.manage.vo;

import com.biz.gbck.enums.CommonStatusEnum;


import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.sql.Timestamp;

/**
 * 活动中心的活动
 *
 * @author dylan
 */
 public class PromotionVo  implements Serializable {

private Long id;

    /**
     * 消息标题
     */
 private String title;


    /**
     * 点击消息跳转地址
     */
private String url;

    /**
     * logo
     */
private String logo;

    /**
     * 创建人
     */
private String adminId;

    /**
     * 创建日期
     */
private Timestamp createTime;

    /**
     * 显示顺序
     */
 private Integer idx = 0;


    /**
     * 是否发布到客服端
     */
private Boolean showInApp = false;


    /**
     * 状态(0：删除,1:正常)
     */
 private Integer status = CommonStatusEnum.ENABLE.getValue();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public Boolean getShowInApp() {
        return showInApp;
    }

    public void setShowInApp(Boolean showInApp) {
        this.showInApp = showInApp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


}
