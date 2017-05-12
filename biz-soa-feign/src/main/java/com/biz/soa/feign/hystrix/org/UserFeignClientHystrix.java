package com.biz.soa.feign.hystrix.org;

import com.biz.gbck.common.exception.CommonException;
import com.biz.gbck.dao.redis.ro.org.UserRo;
import com.biz.gbck.vo.org.ForgotPasswordReqVo;
import com.biz.gbck.vo.org.UserRegisterReqVo;
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
}
