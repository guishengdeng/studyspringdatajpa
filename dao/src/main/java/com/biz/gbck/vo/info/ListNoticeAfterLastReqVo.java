package com.biz.gbck.vo.info;


import com.biz.gbck.common.vo.CommonReqVoBindUserId;

public class ListNoticeAfterLastReqVo extends CommonReqVoBindUserId {


    private Long lastNoticeId;

    public Long getLastNoticeId() {
        return lastNoticeId;
    }

    public void setLastNoticeId(Long lastNoticeId) {
        this.lastNoticeId = lastNoticeId;
    }
}
