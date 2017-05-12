package com.biz.soa.feign.client.org;

import com.biz.gbck.common.exception.CommonException;
import com.biz.gbck.dao.redis.ro.org.UserRo;
import com.biz.gbck.vo.org.ForgotPasswordReqVo;
import com.biz.gbck.vo.org.UserRegisterReqVo;
import com.biz.soa.feign.hystrix.org.UserFeignClientHystrix;
import com.biz.support.web.handler.JSONResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: liubin
 * @date 5/10/17 15:14
 */
@FeignClient(name = "soa-org", fallback = UserFeignClientHystrix.class)
public interface UserFeignClient {

    @RequestMapping(value = "/soa/user/findUser/{userId}")
    UserRo findUser(@PathVariable("userId") Long userId);

    /**
     * 用户注册
     */
    @RequestMapping(value = "/soa/user/register", method = RequestMethod.POST)
    JSONResult register(UserRegisterReqVo userRegisterReqVo) throws CommonException;

    @RequestMapping(value = "forgotPassword", method = RequestMethod.POST)
    JSONResult forgotPassword(ForgotPasswordReqVo forgotPasswordReqVo) throws CommonException;


}
