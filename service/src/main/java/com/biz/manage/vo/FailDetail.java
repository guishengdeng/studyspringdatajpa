package com.biz.manage.vo;

/**
 * Created by death on 2016/8/3.
 */
public class FailDetail {

    private String mobile;

    private String msg;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public FailDetail(String mobile, String msg) {
        this.mobile = mobile;
        this.msg = msg;
    }
}
