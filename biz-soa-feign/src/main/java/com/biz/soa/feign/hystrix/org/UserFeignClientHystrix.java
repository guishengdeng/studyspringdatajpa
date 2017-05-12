package com.biz.soa.feign.hystrix.org;

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
import com.biz.soa.feign.client.org.UserFeignClient;
import com.biz.support.web.handler.JSONResult;
import org.springframework.stereotype.Component;

/**
 * Created by david-liu on 2017/05/12 12:19.
 */
@Component
public class UserFeignClientHystrix implements UserFeignClient {
    @Override
    public UserRo findUser(Long userId) {
        return null;
    }

    @Override
    public JSONResult register(UserRegisterReqVo userRegisterReqVo) throws CommonException {
        return null;
    }

    @Override
    public JSONResult forgotPassword(ForgotPasswordReqVo forgotPasswordReqVo) throws CommonException {
        return null;
    }

    @Override
    public String test() {
        return null;
    }

    @Override
    public JSONResult login(UserLoginReqVo userLoginReqVo) throws CommonException {
        return null;
    }

    @Override
    public JSONResult logout(CommonReqVoBindUserId reqVoBindUserId) {
        return null;
    }

    @Override
    public JSONResult autoLogin(AutoLoginReqVo reqVo) throws CommonException {
        return null;
    }

    @Override
    public JSONResult changeMobile(UserChangeMobileReqVo userChangeMobileReqVo) throws CommonException {
        return null;
    }

    @Override
    public JSONResult updateAvatar(UserChangeAvatarReqVo userChangeAvatarReqVo) throws CommonException {
        return null;
    }

    @Override
    public JSONResult changePwd(ChangePwdVo changePwdVo) throws CommonException {
        return null;
    }

    @Override
    public JSONResult validateLoginPwd(ValidateUserLoginPwdReqVo validateUserLoginPwdReqVo) throws CommonException {
        return null;
    }
}
