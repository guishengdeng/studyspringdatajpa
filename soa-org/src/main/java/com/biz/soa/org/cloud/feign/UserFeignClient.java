package com.biz.soa.org.cloud.feign;

import com.biz.gbck.common.exception.CommonException;
import com.biz.gbck.dao.redis.ro.org.UserRo;
import com.biz.service.org.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: liubin
 * @date 5/10/17 15:14
 */
@FeignClient(name = "soa-user")
public interface UserFeignClient {

    @RequestMapping(value = "/soa/user/findUser/{userId}")
    public UserRo findUser(@PathVariable("userId") Long userId);





}
