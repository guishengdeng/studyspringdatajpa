package com.biz.gbck.dao.redis.ro.sms;


import com.biz.redis.annotation.Ro;
import com.biz.redis.annotation.RoSortedSet;
import com.biz.redis.bean.BaseRedisObject;

import java.io.Serializable;

@Ro(key = "SMSRo")
@RoSortedSet(key = "list", score = "createTimestamp")
public class SMSRo extends BaseRedisObject<String> implements Serializable {

    private String code;

    private Long createTime;

    public SMSRo() {
    }

    public SMSRo(String code, Long createTime) {
        this.code = code;
        this.createTime = createTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
