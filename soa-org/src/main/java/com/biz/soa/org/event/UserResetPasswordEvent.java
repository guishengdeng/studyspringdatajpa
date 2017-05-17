package com.biz.soa.org.event;


import com.biz.gbck.dao.redis.ro.org.UserRo;

/**
 * Reset password event
 * Created by zhangcheng on 2016/7/28.
 */
public class UserResetPasswordEvent extends UserEvent{

    private static final long serialVersionUID = 1494715564286693444L;

    private UserRo userRo;

    public UserResetPasswordEvent(Object source , UserRo ro) {
        super(source);
        this.userRo = ro;
    }

    public UserRo getUserRo() {
        return userRo;
    }
}
