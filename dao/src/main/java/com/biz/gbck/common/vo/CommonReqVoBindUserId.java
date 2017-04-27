package com.biz.gbck.common.vo;



import com.biz.gbck.common.exception.CommonRuntimeException;
import com.biz.gbck.common.exception.ExceptionCode;
import com.biz.gbck.common.model.order.ICommonReqVoBindUserId;

import javax.servlet.http.HttpServletRequest;


public class CommonReqVoBindUserId extends WithoutBindRequestParams implements ICommonReqVoBindUserId {

    public Long userId;

    public void bindRequestParams(HttpServletRequest request) throws CommonRuntimeException {
        try {
            userId = Long.parseLong(request.getParameter("userId"));
        } catch (NumberFormatException e) {
            throw new CommonRuntimeException("用户ID参数不正确", ExceptionCode.Global.PARAM_USERID_ERROR);
        }
    }

    /**
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
