package com.biz.gbck.dao.redis.ro.info;


import com.biz.gbck.common.ro.AbstractRedisObj;
import com.biz.gbck.dao.redis.ro.advertisement.AdvertisementRo;
import com.biz.redis.annotation.Ro;
import com.biz.redis.annotation.RoSortedSet;
import com.biz.redis.bean.BaseRedisObject;

/**
 * 缓存中的促销活动
 *
 * Created by xys on 2017/4/18.
 */
@Ro(key = "pr:PromotionRo")
@RoSortedSet(key = "list", score = "createTimestamp")
public class PromotionRo extends BaseRedisObject<Long> {

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
