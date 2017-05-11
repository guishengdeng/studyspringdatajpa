package com.biz.gbck.common.vo;


import com.biz.gbck.common.exception.CommonRuntimeException;

import javax.servlet.http.HttpServletRequest;


public abstract class CommonReqVo implements java.io.Serializable {

    /**
     * 绑定post参数中md5
     */
    private String md5;

    public abstract void bindRequestParams(HttpServletRequest request)
        throws CommonRuntimeException;

    /**
     * 是否分组
     *
     * @return maliang
     * 2014-9-5上午10:49:59
     */
    public Class<?>[] groupClazz() {
        return null;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

}
