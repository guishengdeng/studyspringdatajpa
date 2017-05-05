package com.biz.gbck.vo.org;


import com.biz.gbck.common.model.InitGlobalParams;
import com.biz.gbck.common.vo.CommonReqVoBindUserId;
import com.biz.support.web.assist.GlobalParams;

/**
 * Created by defei on 3/16/16.
 */
public class UserChangeAvatarReqVo extends CommonReqVoBindUserId implements InitGlobalParams {

    /**
     * 头像
     */
    private String avatar;

    private GlobalParams globalParams;

    @Override public void setGlobalParams(GlobalParams globalParams) {
        this.globalParams = globalParams;
    }

    @Override public GlobalParams getGlobalParams() {
        return globalParams;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
