package com.biz.gbck.dao.mysql.po.info;


import com.biz.gbck.enums.CommonStatusEnum;
import org.springframework.data.annotation.Persistent;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.sql.Timestamp;

/**
 * 活动中心的活动
 *
 * @author gongshutao
 */
@SuppressWarnings("serial") @Entity @Table(name = "info_promotion") public class PromotionPo
        implements Serializable {

    @Id private Long id;

    /**
     * 消息标题
     */
    @Column(length = 255, nullable = false) private String title;


    /**
     * 点击消息跳转地址
     */
    @Column(length = 255) private String url;

    /**
     * logo
     */
    @Column(length = 255) private String logo;

    /**
     * 创建人
     */
    @Column(length = 50, nullable = false) private String adminId;

    /**
     * 创建日期
     */
    @Column(nullable = false) private Timestamp createTime;

    /**
     * 显示顺序
     */
    @Column(nullable = false) private Integer idx = 0;


    /**
     * 是否发布到客服端
     */
    @Column(nullable = false) private Boolean showInApp = false;


    /**
     * 状态(0：删除,1:正常)
     */
    @Column(nullable = false) private Integer status = CommonStatusEnum.ENABLE.getValue();

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
