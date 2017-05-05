package com.biz.gbck.vo.org;


import com.biz.gbck.common.model.InitGlobalParams;
import com.biz.gbck.common.vo.CommonReqVoBindUserId;
import com.biz.support.web.assist.GlobalParams;

/**
 * Created by defei on 3/16/16.
 */
public class UserChangeMobileReqVo extends CommonReqVoBindUserId implements InitGlobalParams {

    /**
     * 密码
     */
    private String mobile;

    /**
     * 短信验证码
     */
    private String smsCode;

    private GlobalParams globalParams;

    @Override public void setGlobalParams(GlobalParams globalParams) {
        this.globalParams = globalParams;
    }

    @Override public GlobalParams getGlobalParams() {
        return globalParams;
    }


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }
}
