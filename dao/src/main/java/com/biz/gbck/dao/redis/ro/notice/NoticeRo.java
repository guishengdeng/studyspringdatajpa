package com.biz.gbck.dao.redis.ro.notice;


import com.biz.gbck.common.ro.AbstractRedisObj;

import java.sql.Timestamp;

/**
 * 缓存中的 用户消息
 *
 * @author gongshutao
 */
public class NoticeRo extends AbstractRedisObj {

    private Long id;
    private String title;
    private String url;
    private String content;
    private Timestamp createTime;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

}
