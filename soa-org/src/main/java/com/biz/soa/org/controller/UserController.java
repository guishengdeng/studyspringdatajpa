package com.biz.soa.org.controller;

import com.biz.gbck.common.exception.CommonException;
import com.biz.gbck.dao.redis.ro.org.UserRo;
import com.biz.gbck.vo.soa.MicroServiceResult;
import com.biz.service.org.interfaces.UserService;
import com.biz.soa.base.SoaBaseController;
import com.biz.soa.org.cloud.feign.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: liubin
 * @date 5/9/17 09:37
 */
@RequestMapping(value = "/soa/user")
public class UserController extends SoaBaseController {

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/findUser/{userId}")
    public MicroServiceResult<UserRo> findUser(@PathVariable("userId") Long userId) throws CommonException {
        return render200(userService.findUser(userId));
    }



}
