package com.biz.rest.controller.org;

/*import com.depotnearby.common.exception.ExceptionCode;
import com.depotnearby.exception.CommonException;
import com.depotnearby.rest.bean.Constant;
import com.depotnearby.rest.util.RestUtil;
import com.depotnearby.vo.CommonReqVoBindUserId;
import com.depotnearby.vo.user.*; */
import com.biz.gbck.common.exception.CommonException;
import com.biz.rest.controller.BaseRestController;
import com.biz.rest.util.RestUtil;
import com.biz.service.org.interfaces.UserService;
import com.biz.support.web.handler.JSONResult;
import com.biz.support.web.util.HttpServletHelper;
import com.biz.vo.org.UserLoginReqVo;
import com.biz.vo.org.UserLoginResVo;
import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * 用户模块 注册,登陆,修改密码,获取用户信息等
 *
 * @author defei
 */

@RestController
@RequestMapping("users") public class UserController extends BaseRestController {

    @Autowired
    private UserService userService;

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 用户注册
     */
   /* @RequestMapping("register") public JSONResult register(HttpServletRequest request)
        throws CommonException {

        UserRegisterReqVo userRegisterReqVo =
            RestUtil.parseBizData(request, UserRegisterReqVo.class);
        String clientIP = HttpServletHelper.getClientIP(request);
        logger.debug("Received /users/register POST request with mobile:{} from IP:{}",
            userRegisterReqVo.getMobile(), clientIP);
        userRegisterReqVo.setIp(clientIP);
        UserLoginResVo userLoginResVo = userService.createUserAndShop(userRegisterReqVo);
        return new JSONResult(userLoginResVo);
    }*/

    /**
     * 忘记密码
     */
  /*  @RequestMapping("forgotPassword") public JSONResult forgotPassword(HttpServletRequest request)
        throws CommonException {

        ForgotPasswordReqVo forgotPasswordReqVo =
            RestUtil.parseBizData(request, ForgotPasswordReqVo.class);
        userService.forgotPassword(forgotPasswordReqVo);
        return new JSONResult();
    }*/

    /**
     * 登录
     */
    @RequestMapping("login") public JSONResult login(HttpServletRequest request)
        throws CommonException {
        UserLoginReqVo userLoginReqVo = RestUtil.parseBizData(request, UserLoginReqVo.class);
        String clientIP = HttpServletHelper.getClientIP(request);
        logger.debug("Received /users/login POST request with account:{} from ip:{}",
            userLoginReqVo.getAccount(), clientIP);
        userLoginReqVo.setIp(clientIP);
        Stopwatch stopwatch = Stopwatch.createStarted();
        UserLoginResVo userLoginResVo = userService.login(userLoginReqVo);
        logger.error("用户[{}]登录总耗时 {} ms", userLoginReqVo.getAccount(),
            stopwatch.elapsed(TimeUnit.MILLISECONDS));
        return new JSONResult(userLoginResVo);
    }

    /**
     * 退出登录
     */
  /*  @RequestMapping("logout") public JSONResult logout(HttpServletRequest request) {
        CommonReqVoBindUserId reqVoBindUserId =
            RestUtil.parseBizData(request, CommonReqVoBindUserId.class);
        userService.logout(reqVoBindUserId);
        return new JSONResult();
    }*/

    /**
     * 自动登陆详情(此接口会绑定token)
     */
   /* @RequestMapping(value = "autoLogin") public JSONResult autoLogin(HttpServletRequest request)
        throws CommonException {
        AutoLoginReqVo reqVo = RestUtil.parseBizData(request, AutoLoginReqVo.class);
        logger.debug("Received /users tokenChange request with account:{} from ip:{}",
            reqVo.getUserId());
        UserLoginResVo userLoginResVo = userService.autoLogin(reqVo);
        return new JSONResult(userLoginResVo);
    }*/

    /**
     * 变更用户绑定手机号码
     */
   /* @RequestMapping("changeMobile") public JSONResult changeMobile(HttpServletRequest request)
        throws CommonException {

        UserChangeMobileReqVo userChangeMobileReqVo =
            RestUtil.parseBizData(request, UserChangeMobileReqVo.class);
        userService.changeMobile(userChangeMobileReqVo);
        return new JSONResult();
    }*/

    /**
     * 修改用户头像
     */
   /* @RequestMapping("changeAvatar") public JSONResult updateAvatar(HttpServletRequest request)
        throws CommonException {

        UserChangeAvatarReqVo userChangeAvatarReqVo =
            RestUtil.parseBizData(request, UserChangeAvatarReqVo.class);
        userService.changeAvatar(userChangeAvatarReqVo);
        return new JSONResult();
    }*/


    /**
     * 修改用户密码
     */
   /* @RequestMapping("changePwd") public JSONResult changePwd(HttpServletRequest request)
        throws CommonException {
        ChangePwdVo changePwdVo = RestUtil.parseBizData(request, ChangePwdVo.class);
        userService.changePwd(changePwdVo);
        return new JSONResult();
    }*/


  /*  @RequestMapping("validateLoginPassword")
    public JSONResult validateLoginPwd(HttpServletRequest request) throws CommonException {
        ValidateUserLoginPwdReqVo validateUserLoginPwdReqVo =
            RestUtil.parseBizData(request, ValidateUserLoginPwdReqVo.class);
        boolean validateResult = userService
            .validateUserLoginPwd(validateUserLoginPwdReqVo.getUserId(),
                validateUserLoginPwdReqVo.getPassword());
        if (!validateResult) {
            throw new CommonException("验证用户登录密码失败", ExceptionCode.User.PWD_NOT_MATCH);
        }

        return new JSONResult(Constant.SUCCESS_CODE, "验证用户登录密码成功");
    }*/

}
