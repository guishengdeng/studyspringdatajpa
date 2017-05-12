package com.biz.gbck.dao.redis.ro.notice;


import com.biz.redis.annotation.Ro;
import com.biz.redis.annotation.RoSortedSet;
import com.biz.redis.bean.BaseRedisObject;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 缓存中的 用户消息
 */

@Ro(key = "noticeRo")
@RoSortedSet(key = "list", score = "createTimestamp")
public class NoticeRo  extends BaseRedisObject<String> implements Serializable {

    private String title;
    private String url;
    private String content;
    private Timestamp createTime;


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
