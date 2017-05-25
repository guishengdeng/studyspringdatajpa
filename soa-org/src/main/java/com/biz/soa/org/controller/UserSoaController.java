package com.biz.soa.org.controller;

import com.biz.gbck.common.exception.CommonException;
import com.biz.gbck.common.exception.ExceptionCode;
import com.biz.gbck.common.vo.CommonReqVoBindUserId;
import com.biz.gbck.dao.mysql.po.org.UserPo;
import com.biz.gbck.dao.redis.ro.org.UserRo;
import com.biz.gbck.enums.user.AuditStatus;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.vo.org.*;
import com.biz.soa.org.service.interfaces.UserSoaService;
import com.biz.soa.org.util.Constant;
import com.biz.support.web.handler.JSONResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户模块 注册,登陆,修改密码,获取用户信息等
 *
 * @author defei
 */

@RestController
@RequestMapping("soa/user")
public class UserSoaController extends BaseRestController {

    @Autowired
    private UserSoaService userSoaService;

    private static Logger logger = LoggerFactory.getLogger(UserSoaController.class);


    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String test() {
        return "test";
    }

    /**
     * 用户注册
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public JSONResult register(@RequestBody UserRegisterReqVo userRegisterReqVo)
        throws CommonException {

        UserLoginResVo userLoginResVo = userSoaService.createUserAndShop(userRegisterReqVo);
        return new JSONResult(userLoginResVo);
    }

    /**
     * 忘记密码
     */
    @RequestMapping(value = "forgotPassword", method = RequestMethod.POST)
    public JSONResult forgotPassword(@RequestBody ForgotPasswordReqVo forgotPasswordReqVo)
        throws CommonException {

//        ForgotPasswordReqVo forgotPasswordReqVo =
//            RestUtil.parseBizData(request, ForgotPasswordReqVo.class);
        userSoaService.forgotPassword(forgotPasswordReqVo);
        return new JSONResult();
    }

    /**
     * 登录
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public JSONResult login(@RequestBody UserLoginReqVo userLoginReqVo) throws CommonException {
//        UserLoginReqVo userLoginReqVo = RestUtil.parseBizData(request, UserLoginReqVo.class);
//        String clientIP = HttpServletHelper.getClientIP(request);
        logger.info("Received /users/login POST request with account:{} from ip:{}",
            userLoginReqVo.getAccount(), userLoginReqVo.getIp());
//        userLoginReqVo.setIp(clientIP);
//        Stopwatch stopwatch = Stopwatch.createStarted();
        UserLoginResVo userLoginResVo = userSoaService.login(userLoginReqVo);
//        logger.error("用户[{}]登录总耗时 {} ms", userLoginReqVo.getAccount(),
//            stopwatch.elapsed(TimeUnit.MILLISECONDS));
        return new JSONResult(userLoginResVo);
    }

    /**
     * 退出登录
     */
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public JSONResult logout(@RequestBody CommonReqVoBindUserId reqVoBindUserId) {
//        CommonReqVoBindUserId reqVoBindUserId =
//            RestUtil.parseBizData(request, CommonReqVoBindUserId.class);
        userSoaService.logout(reqVoBindUserId);
        return new JSONResult();
    }

    /**
     * 自动登陆详情(此接口会绑定token)
     */
    @RequestMapping(value = "autoLogin", method = RequestMethod.POST)
    public JSONResult autoLogin(@RequestBody AutoLoginReqVo reqVo) throws CommonException {

//        AutoLoginReqVo reqVo = RestUtil.parseBizData(request, AutoLoginReqVo.class);
        logger.debug("Received /users tokenChange request with account:{} from ip:{}",
            reqVo.getUserId());

        UserLoginResVo userLoginResVo = userSoaService.autoLogin(reqVo);
        return new JSONResult(userLoginResVo);
    }

    /**
     * 变更用户绑定手机号码
     */
    @RequestMapping(value = "changeMobile", method = RequestMethod.POST)
    public JSONResult changeMobile(@RequestBody UserChangeMobileReqVo userChangeMobileReqVo) throws CommonException {

//        UserChangeMobileReqVo userChangeMobileReqVo =
//            RestUtil.parseBizData(request, UserChangeMobileReqVo.class);
        userSoaService.changeMobile(userChangeMobileReqVo);
        return new JSONResult();
    }

    /**
     * 修改用户头像
     */
    @RequestMapping(value = "changeAvatar", method = RequestMethod.POST)
    public JSONResult updateAvatar(@RequestBody UserChangeAvatarReqVo userChangeAvatarReqVo) throws CommonException {

//        UserChangeAvatarReqVo userChangeAvatarReqVo =
//            RestUtil.parseBizData(request, UserChangeAvatarReqVo.class);
        userSoaService.changeAvatar(userChangeAvatarReqVo);
        return new JSONResult();
    }


    /**
     * 修改用户密码
     */
    @RequestMapping(value = "changePwd", method = RequestMethod.POST)
    public JSONResult changePwd(@RequestBody ChangePwdVo changePwdVo) throws CommonException {

//        ChangePwdVo changePwdVo = RestUtil.parseBizData(request, ChangePwdVo.class);
        userSoaService.changePwd(changePwdVo);
        return new JSONResult();
    }


    /**
     *验证登录密码
     */
    @RequestMapping(value = "validateLoginPassword", method = RequestMethod.POST)
    public JSONResult validateLoginPwd(@RequestBody ValidateUserLoginPwdReqVo validateUserLoginPwdReqVo) throws CommonException {
//        ValidateUserLoginPwdReqVo validateUserLoginPwdReqVo =
//            RestUtil.parseBizData(request, ValidateUserLoginPwdReqVo.class);
        boolean validateResult = userSoaService
            .validateUserLoginPwd(Long.valueOf(validateUserLoginPwdReqVo.getUserId()),
                validateUserLoginPwdReqVo.getPassword());
        if (!validateResult) {
            throw new CommonException("验证用户登录密码失败", ExceptionCode.User.PWD_NOT_MATCH);
        }

        return new JSONResult(Constant.SUCCESS_CODE, "验证用户登录密码成功");
    }

    /**
     * 通过电话号码查用户
     */
    @RequestMapping(value = "findUserPoByMobile", method = RequestMethod.POST)
    public UserPo findUserPoByMobile(@RequestParam("mobile") String mobile) {
        return userSoaService.findUserPoByMobile(mobile);
    }

    /**
     * 通过电话号码查用户
     */
    @RequestMapping(value = "findUserPoByAccount", method = RequestMethod.POST)
    public UserPo findUserPoByAccount(@RequestParam("account") String account) {
        return userSoaService.findUserPoByAccount(account);
    }


    @RequestMapping(value = "findUser", method = RequestMethod.POST)
    public UserRo findUser(@RequestParam("userId") Long userId) throws CommonException {
        return userSoaService.findUser(userId);
    }

    @PostMapping(value = "findUserInfo")
    public UserInfoVo finUserInfo(@RequestParam("userId") Long userId) throws CommonException {
        if (logger.isDebugEnabled()) {
            logger.debug("Received findUserInfo request. userId: {}", userId);
        }
        return userSoaService.findUserInfo(userId);
    }

    /**
     * 根据店铺审核状态查询所有userPo
     */
    @RequestMapping(value = "findAllUserByAuditStatus", method = RequestMethod.POST)
    public List<UserPo> findAllUserByAuditStatus(@RequestParam("auditStatus") AuditStatus auditStatus) throws DepotNextDoorException {
        return userSoaService.findAllUserByAuditStatus(auditStatus);
    }

    @RequestMapping(value = "findUserIdByShopType", method = RequestMethod.POST)
    public List<Long> findUserIdByShopType(@RequestParam("shopTypeId") Long shopTypeId) {
        return userSoaService.findUserIdByShopType(shopTypeId);
    }

    @RequestMapping(value = "findAdminUserIdsByShopId", method = RequestMethod.POST)
    public List<Long> findAdminUserIdsByShopId(@RequestParam("shopId") Long shopId, @RequestParam("isAdmin") Boolean isAdmin) {
        return userSoaService.findAdminUserIdsByShopId(shopId, isAdmin);
    }

    @RequestMapping(value = "findUserIdByCompanyGroupId", method = RequestMethod.POST)
    List<Long> findUserIdByCompanyGroupId(@RequestParam("companyGroupId") Long companyGroupId) {
        return userSoaService.findUserIdByCompanyGroupId(companyGroupId);
    }
}
