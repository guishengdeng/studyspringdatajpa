package com.biz.gbck.vo.org;


import com.biz.gbck.common.model.InitGlobalParams;
import com.biz.gbck.common.vo.CommonReqVoBindUserId;
import com.biz.support.web.assist.GlobalParams;

/**
 * Created by JKLiues on 2016/5/24.
 */
public class ChangePwdVo extends CommonReqVoBindUserId implements InitGlobalParams {

    /**
     * 原密码（旧密码）md5加密
     */
    private String originPassword;
    /**
     * 新密码(md5加密)
     */
    private String newPassword;
    /**
     * 新密码确认密码(md5加密)
     */
    private String confirmPassword;

    private GlobalParams globalParams;

    @Override public void setGlobalParams(GlobalParams globalParams) {
        this.globalParams = globalParams;
    }

    @Override public GlobalParams getGlobalParams() {
        return globalParams;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOriginPassword() {
        return originPassword;
    }

    public void setOriginPassword(String originPassword) {
        this.originPassword = originPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
