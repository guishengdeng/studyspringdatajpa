package com.biz.soa.feign.client.org;

import com.biz.gbck.common.vo.CommonReqVoBindUserId;
import com.biz.gbck.dao.mysql.po.org.UserPo;
import com.biz.gbck.dao.redis.ro.org.UserRo;
import com.biz.gbck.enums.user.AuditStatus;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.vo.org.*;
import com.biz.soa.feign.hystrix.org.UserFeignClientHystrix;
import com.biz.support.web.handler.JSONResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author: liubin
 * @date 5/10/17 15:14
 */
@FeignClient(name = "soa-org", fallback = UserFeignClientHystrix.class)
public interface UserFeignClient {

    @RequestMapping(value = "/soa/user/test", method = RequestMethod.GET)
    String test();

    @RequestMapping(value = "/soa/user/findUser", method = RequestMethod.POST)
    UserRo findUser(@RequestParam("userId") Long userId) throws DepotNextDoorException;

    /**
     * 用户注册
     */
    @RequestMapping(value = "/soa/user/register", method = RequestMethod.POST, consumes = "application/json")
    JSONResult register(@RequestBody UserRegisterReqVo userRegisterReqVo) throws DepotNextDoorException;

    @RequestMapping(value = "/soa/user/forgotPassword", method = RequestMethod.POST)
    JSONResult forgotPassword(@RequestBody ForgotPasswordReqVo forgotPasswordReqVo) throws DepotNextDoorException;

    /**
     * 登录
     */
    @RequestMapping(value = "/soa/user/login", method = RequestMethod.POST)
    JSONResult login(@RequestBody UserLoginReqVo userLoginReqVo) throws DepotNextDoorException;


    /**
     * 退出登录
     */
    @RequestMapping(value = "/soa/user/logout", method = RequestMethod.POST)
    JSONResult logout(@RequestBody CommonReqVoBindUserId reqVoBindUserId);

    /**
     * 自动登陆详情(此接口会绑定token)
     */
    @RequestMapping(value = "/soa/user/autoLogin", method = RequestMethod.POST)
    JSONResult autoLogin(@RequestBody AutoLoginReqVo reqVo) throws DepotNextDoorException;


    /**
     * 变更用户绑定手机号码
     */
    @RequestMapping(value = "/soa/user/changeMobile", method = RequestMethod.POST)
    JSONResult changeMobile(@RequestBody UserChangeMobileReqVo userChangeMobileReqVo) throws DepotNextDoorException;


    /**
     * 修改用户头像
     */
    @RequestMapping(value = "/soa/user/changeAvatar", method = RequestMethod.POST)
    JSONResult updateAvatar(@RequestBody UserChangeAvatarReqVo userChangeAvatarReqVo) throws DepotNextDoorException;


    /**
     * 修改用户密码
     */
    @RequestMapping(value = "/soa/user/changePwd", method = RequestMethod.POST)
    JSONResult changePwd(@RequestBody ChangePwdVo changePwdVo) throws DepotNextDoorException;


    /**
     * 验证登录密码
     */
    @RequestMapping(value = "/soa/user/validateLoginPassword", method = RequestMethod.POST)
    JSONResult validateLoginPwd(@RequestBody ValidateUserLoginPwdReqVo validateUserLoginPwdReqVo) throws DepotNextDoorException;


    /**
     * 通过电话号码查用户
     */
    @RequestMapping(value = "findUserPoByMobile", method = RequestMethod.POST)
    UserPo findUserPoByMobile(@RequestParam("mobile") String mobile);

    /**
     * 通过电话号码查用户
     */
    @RequestMapping(value = "findUserPoByAccount", method = RequestMethod.POST)
    UserPo findUserPoByAccount(@RequestParam("account") String account);

    @RequestMapping(value = "/soa/user/findUserInfo", method =RequestMethod.POST)
    UserInfoVo findUserInfo(@RequestParam("userId") Long userId) throws DepotNextDoorException;

    /**
     * 根据商户审核状态查找用户
     * @param auditStatus
     * @return
     * @throws DepotNextDoorException
     */
    @RequestMapping(value = "findAllUserByAuditStatus", method = RequestMethod.POST)
    List<UserPo> findAllUserByAuditStatus(@RequestParam("auditStatus") AuditStatus auditStatus) throws DepotNextDoorException;

    /**
     * 根据商户类型查找用户Ids
     * @param shopTypeId
     * @return
     */
    @RequestMapping(value = "findUserIdByShopType", method = RequestMethod.POST)
    List<Long> findUserIdByShopType(@RequestParam("shopTypeId") Long shopTypeId);

    /**
     * 根据商户Id和是否是店长查找用户Id
     * @param shopId
     * @param isAdmin
     * @return
     */
    @RequestMapping(value = "findAdminUserIdsByShopId", method = RequestMethod.POST)
    List<Long> findAdminUserIdsByShopId(@RequestParam("shopId") Long shopId, @RequestParam("isAdmin") Boolean isAdmin);
}





