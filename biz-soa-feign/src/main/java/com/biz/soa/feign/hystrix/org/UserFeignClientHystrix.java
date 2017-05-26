package com.biz.soa.feign.hystrix.org;

import com.biz.gbck.common.vo.CommonReqVoBindUserId;
import com.biz.gbck.dao.mysql.po.org.UserPo;
import com.biz.gbck.dao.redis.ro.org.UserRo;
import com.biz.gbck.enums.user.AuditStatus;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.vo.org.*;
import com.biz.soa.feign.client.org.UserFeignClient;
import com.biz.support.web.handler.JSONResult;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by david-liu on 2017/05/12 12:19.
 */
@Component
public class UserFeignClientHystrix implements UserFeignClient {
    @Override
    public String test() {
        return null;
    }

    @Override
    public UserRo findUser(@PathVariable("userId") Long userId) {
        return null;
    }

    @Override
    public JSONResult register(@RequestBody UserRegisterReqVo userRegisterReqVo) throws DepotNextDoorException {
        return null;
    }

    @Override
    public JSONResult forgotPassword(@RequestBody ForgotPasswordReqVo forgotPasswordReqVo) throws DepotNextDoorException {
        return null;
    }

    @Override
    public JSONResult login(@RequestBody UserLoginReqVo userLoginReqVo) throws DepotNextDoorException {
        return null;
    }

    @Override
    public JSONResult logout(@RequestBody CommonReqVoBindUserId reqVoBindUserId) {
        return null;
    }

    @Override
    public JSONResult autoLogin(@RequestBody AutoLoginReqVo reqVo) throws DepotNextDoorException {
        return null;
    }

    @Override
    public JSONResult changeMobile(@RequestBody UserChangeMobileReqVo userChangeMobileReqVo) throws DepotNextDoorException {
        return null;
    }

    @Override
    public JSONResult updateAvatar(@RequestBody UserChangeAvatarReqVo userChangeAvatarReqVo) throws DepotNextDoorException {
        return null;
    }

    @Override
    public JSONResult changePwd(@RequestBody ChangePwdVo changePwdVo) throws DepotNextDoorException {
        return null;
    }

    @Override
    public JSONResult validateLoginPwd(@RequestBody ValidateUserLoginPwdReqVo validateUserLoginPwdReqVo) throws DepotNextDoorException {
        return null;
    }

    @Override
    public UserRo findUserRoByMobile(String mobile) {
        return null;
    }

    @Override
    public UserInfoVo findUserInfo(Long userId) throws DepotNextDoorException {
        return null;
    }

    @Override
    public List<UserPo> findAllUserByAuditStatus(@RequestParam("auditStatus") AuditStatus auditStatus) throws DepotNextDoorException {
        return null;
    }

    @Override
    public List<Long> findUserIdByShopType(@RequestParam("shopTypeId") Long shopTypeId) {
        return null;
    }

    @Override
    public List<Long> findAdminUserIdsByShopId(@RequestParam("shopId") Long shopId, @RequestParam("isAdmin") Boolean isAdmin) {
        return null;
    }

    @Override
    public List<Long> findUserIdByCompanyGroupId(@RequestParam("companyGroupId") Long companyGroupId) {
        return null;
    }
}
