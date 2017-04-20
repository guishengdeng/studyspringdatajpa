package com.biz.gbck.dao.redis.ro.info;


import com.biz.gbck.common.ro.AbstractRedisObj;

/**
 * 缓存中的促销活动
 *
 * @author gongshutao
 */
public class PromotionRo extends AbstractRedisObj {

    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * logo
     */
    private String logo;

    /**
     * 点击进入地址
     */
    private String url;

    /**
     * 显示顺序
     */
    private Integer idx;


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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }



}
