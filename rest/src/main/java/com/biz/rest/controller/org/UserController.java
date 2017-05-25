package com.biz.rest.controller.org;

import com.biz.gbck.common.vo.CommonReqVoBindUserId;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.vo.org.*;
import com.biz.rest.controller.BaseRestController;
import com.biz.rest.util.RestUtil;
import com.biz.service.org.interfaces.UserService;
import com.biz.soa.feign.client.org.UserFeignClient;
import com.biz.support.web.handler.JSONResult;
import com.biz.support.web.util.HttpServletHelper;
import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * 用户模块 注册,登陆,修改密码,获取用户信息等
 *
 * @author defei
 */

@RestController
@RequestMapping("users")
public class UserController extends BaseRestController {

    @Autowired(required = false)
    private UserService userService;

    @Autowired
    private UserFeignClient userFeignClient;


    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public JSONResult test() {
        return new JSONResult(userFeignClient.test());
    }

    /**
     * 用户注册
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public JSONResult register(HttpServletRequest request) throws DepotNextDoorException {

        UserRegisterReqVo userRegisterReqVo =
            RestUtil.parseBizData(request, UserRegisterReqVo.class);
        String clientIP = HttpServletHelper.getClientIP(request);
        logger.debug("Received /users/register POST request with mobile:{} from IP:{}",
            userRegisterReqVo.getMobile(), clientIP);
        userRegisterReqVo.setIp(clientIP);
        JSONResult result = userFeignClient.register(userRegisterReqVo);
//        UserLoginResVo userLoginResVo = user.createUserAndShop(userRegisterReqVo);
        return result;
    }

    /**
     * 忘记密码
     */
    @RequestMapping(value = "forgotPassword", method = RequestMethod.POST)
    public JSONResult forgotPassword(HttpServletRequest request) throws DepotNextDoorException {

        ForgotPasswordReqVo forgotPasswordReqVo =
            RestUtil.parseBizData(request, ForgotPasswordReqVo.class);
        userFeignClient.forgotPassword(forgotPasswordReqVo);
        //userService.forgotPassword(forgotPasswordReqVo);
        return new JSONResult();
    }

    /**
     * 登录
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public JSONResult login(HttpServletRequest request) throws  DepotNextDoorException {
        UserLoginReqVo userLoginReqVo = RestUtil.parseBizData(request, UserLoginReqVo.class);
        String clientIP = HttpServletHelper.getClientIP(request);
        logger.debug("Received /users/login POST request with account:{} from ip:{}",
            userLoginReqVo.getAccount(), clientIP);
        userLoginReqVo.setIp(clientIP);
        Stopwatch stopwatch = Stopwatch.createStarted();
        JSONResult result = userFeignClient.login(userLoginReqVo);
        //UserLoginResVo userLoginResVo = userService.login(userLoginReqVo);
        logger.error("用户[{}]登录总耗时 {} ms", userLoginReqVo.getAccount(),
            stopwatch.elapsed(TimeUnit.MILLISECONDS));
        return result;
    }

    /**
     * 退出登录
     */
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public JSONResult logout(HttpServletRequest request) {
        CommonReqVoBindUserId reqVoBindUserId =
            RestUtil.parseBizData(request, CommonReqVoBindUserId.class);
        //userService.logout(reqVoBindUserId);
        userFeignClient.logout(reqVoBindUserId);
        return new JSONResult();
    }

    /**
     * 自动登陆详情(此接口会绑定token)
     */
    @RequestMapping(value = "autoLogin", method = RequestMethod.POST)
    public JSONResult autoLogin(HttpServletRequest request) throws DepotNextDoorException {

        AutoLoginReqVo reqVo = RestUtil.parseBizData(request, AutoLoginReqVo.class);
        logger.debug("Received /users tokenChange request with account:{} from ip:{}",
            reqVo.getUserId());
//        UserLoginResVo userLoginResVo = userService.autoLogin(reqVo);
        JSONResult result = userFeignClient.autoLogin(reqVo);
        return result;
    }

    /**
     * 变更用户绑定手机号码
     */
    @RequestMapping(value = "changeMobile", method = RequestMethod.POST)
    public JSONResult changeMobile(HttpServletRequest request) throws DepotNextDoorException {

        UserChangeMobileReqVo userChangeMobileReqVo =
            RestUtil.parseBizData(request, UserChangeMobileReqVo.class);
        //userService.changeMobile(userChangeMobileReqVo);
        userFeignClient.changeMobile(userChangeMobileReqVo);
        return new JSONResult();
    }

    /**
     * 修改用户头像
     */
    @RequestMapping(value = "changeAvatar", method = RequestMethod.POST)
    public JSONResult updateAvatar(HttpServletRequest request) throws DepotNextDoorException {

        UserChangeAvatarReqVo userChangeAvatarReqVo =
            RestUtil.parseBizData(request, UserChangeAvatarReqVo.class);
        //userService.changeAvatar(userChangeAvatarReqVo);
        userFeignClient.updateAvatar(userChangeAvatarReqVo);
        return new JSONResult();
    }


    /**
     * 修改用户密码
     */
    @RequestMapping(value = "changePwd", method = RequestMethod.POST)
    public JSONResult changePwd(HttpServletRequest request) throws DepotNextDoorException {

        ChangePwdVo changePwdVo = RestUtil.parseBizData(request, ChangePwdVo.class);
        //userService.changePwd(changePwdVo);
        userFeignClient.changePwd(changePwdVo);
        return new JSONResult();
    }


    /**
     *验证登录密码
     */
    @RequestMapping(value = "validateLoginPassword", method = RequestMethod.POST)
    public JSONResult validateLoginPwd(HttpServletRequest request) throws DepotNextDoorException {

        ValidateUserLoginPwdReqVo validateUserLoginPwdReqVo =
            RestUtil.parseBizData(request, ValidateUserLoginPwdReqVo.class);

        JSONResult result = userFeignClient.validateLoginPwd(validateUserLoginPwdReqVo);

//        boolean validateResult =  userService
//            .validateUserLoginPwd(validateUserLoginPwdReqVo.getUserId(),
//                validateUserLoginPwdReqVo.getPassword());
//        if (!validateResult) {
//            throw new CommonException("验证用户登录密码失败", ExceptionCode.User.PWD_NOT_MATCH);
//        }

        return result;
    }

}
