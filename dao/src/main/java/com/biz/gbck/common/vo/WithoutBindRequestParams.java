package com.biz.gbck.common.vo;

import com.biz.gbck.common.exception.CommonRuntimeException;

import javax.servlet.http.HttpServletRequest;


public class WithoutBindRequestParams extends CommonReqVo {

    public void bindRequestParams(HttpServletRequest request) throws CommonRuntimeException {
        //do nothing
    }

}
