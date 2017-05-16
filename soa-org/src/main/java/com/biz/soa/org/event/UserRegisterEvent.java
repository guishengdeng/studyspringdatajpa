package com.biz.soa.org.event;


import com.biz.gbck.dao.redis.ro.org.UserRo;
import com.biz.gbck.vo.org.UserRegisterReqVo;

/**
 * 注册 事件
 */
public class UserRegisterEvent extends UserEvent {

  private static final long serialVersionUID = 1494715564596693444L;

  private UserRo userRo;

  private UserRegisterReqVo userRegisterReqVo;

  public UserRegisterEvent(Object source, UserRo ro, UserRegisterReqVo reqVo) {
    super(source);
    this.userRo = ro;
    this.userRegisterReqVo = reqVo;
  }

  public UserRo getUserRo() {
    return userRo;
  }

  public UserRegisterReqVo getUserRegisterReqVo() {
    return userRegisterReqVo;
  }


}
