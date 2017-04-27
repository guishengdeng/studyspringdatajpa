package com.biz.vo.org;


import com.biz.gbck.common.vo.CommonReqVoBindUserId;

public class ValidateUserLoginPwdReqVo extends CommonReqVoBindUserId {
    private String password;

    public ValidateUserLoginPwdReqVo(String password) {
        this.password = password;
    }

    public ValidateUserLoginPwdReqVo() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
