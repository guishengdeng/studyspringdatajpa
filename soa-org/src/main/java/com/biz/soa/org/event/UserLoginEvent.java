package com.biz.soa.org.event;


import com.biz.gbck.dao.redis.ro.org.UserRo;
import com.biz.gbck.vo.org.UserLoginReqVo;

/**
 * 登录事件
 */
public class UserLoginEvent extends UserEvent {

    private static final long serialVersionUID = 1494715564286693444L;

    private UserRo userRo;

    private UserLoginReqVo userLoginReqVo;

    public UserLoginEvent(Object source, UserRo ro, UserLoginReqVo userLoginReqVo) {
        super(source);
        this.userRo = ro;
        this.userLoginReqVo = userLoginReqVo;
    }

    public UserRo getUserRo() {
        return userRo;
    }

    public UserLoginReqVo getUserLoginReqVo() {
        return userLoginReqVo;
    }
}
