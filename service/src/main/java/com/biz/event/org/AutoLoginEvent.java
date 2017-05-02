package com.biz.event.org;


import com.biz.gbck.dao.redis.ro.org.UserRo;
import com.biz.vo.org.AutoLoginReqVo;

/**
 * 登录事件
 */
public class AutoLoginEvent extends UserEvent {

    private static final long serialVersionUID = 1494715564286693444L;

    private UserRo userRo;

    private AutoLoginReqVo autoLoginReqVo;

    public AutoLoginEvent(Object source, UserRo ro, AutoLoginReqVo autoLoginReqVo) {
        super(source);
        this.userRo = ro;
        this.autoLoginReqVo = autoLoginReqVo;
    }

    public UserRo getUserRo() {
        return userRo;
    }

	public AutoLoginReqVo getAutoLoginReqVo() {
		return autoLoginReqVo;
	}

}
