package com.biz.soa.feign.client.org;

import com.biz.gbck.common.exception.CommonException;
import com.biz.gbck.common.vo.CommonReqVoBindUserId;
import com.biz.gbck.dao.redis.ro.org.UserRo;
import com.biz.gbck.vo.org.AutoLoginReqVo;
import com.biz.gbck.vo.org.ChangePwdVo;
import com.biz.gbck.vo.org.ForgotPasswordReqVo;
import com.biz.gbck.vo.org.UserChangeAvatarReqVo;
import com.biz.gbck.vo.org.UserChangeMobileReqVo;
import com.biz.gbck.vo.org.UserLoginReqVo;
import com.biz.gbck.vo.org.UserRegisterReqVo;
import com.biz.gbck.vo.org.ValidateUserLoginPwdReqVo;
import com.biz.soa.feign.hystrix.org.UserFeignClientHystrix;
import com.biz.support.web.handler.JSONResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: liubin
 * @date 5/10/17 15:14
 */
@FeignClient(name = "soa-org", fallback = UserFeignClientHystrix.class)
public interface UserFeignClient {

    @RequestMapping(value = "/soa/user/test", method = RequestMethod.GET)
    String test();

    @RequestMapping(value = "/soa/user/findUser/{userId}")
    UserRo findUser(@PathVariable("userId") Long userId);

    /**
     * 用户注册
     */
    @RequestMapping(value = "/soa/user/register", method = RequestMethod.POST, consumes = "application/json")
    JSONResult register(UserRegisterReqVo userRegisterReqVo) throws CommonException;

    @RequestMapping(value = "/soa/user/forgotPassword", method = RequestMethod.POST)
    JSONResult forgotPassword(ForgotPasswordReqVo forgotPasswordReqVo) throws CommonException;

    /**
     * 登录
     */
    @RequestMapping(value = "/soa/user/login", method = RequestMethod.POST)
    JSONResult login(UserLoginReqVo userLoginReqVo) throws CommonException;


    /**
     * 退出登录
     */
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    JSONResult logout(CommonReqVoBindUserId reqVoBindUserId);

    /**
     * 自动登陆详情(此接口会绑定token)
     */
    @RequestMapping(value = "autoLogin", method = RequestMethod.POST)
    JSONResult autoLogin(AutoLoginReqVo reqVo) throws CommonException;


    /**
     * 变更用户绑定手机号码
     */
    @RequestMapping(value = "changeMobile", method = RequestMethod.POST)
    JSONResult changeMobile(UserChangeMobileReqVo userChangeMobileReqVo) throws CommonException;


    /**
     * 修改用户头像
     */
    @RequestMapping(value = "changeAvatar", method = RequestMethod.POST)
    public JSONResult updateAvatar(UserChangeAvatarReqVo userChangeAvatarReqVo) throws CommonException;


    /**
     * 修改用户密码
     */
    @RequestMapping(value = "changePwd", method = RequestMethod.POST)
    public JSONResult changePwd(ChangePwdVo changePwdVo) throws CommonException;


    /**
     * 验证登录密码
     */
    @RequestMapping(value = "validateLoginPassword", method = RequestMethod.POST)
    public JSONResult validateLoginPwd(ValidateUserLoginPwdReqVo validateUserLoginPwdReqVo) throws CommonException;


}





