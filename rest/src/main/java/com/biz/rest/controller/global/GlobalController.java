package com.biz.rest.controller.global;

import com.biz.rest.controller.BaseRestController;
import com.biz.soa.feign.client.global.GlobalFeignClient;
import com.biz.support.web.handler.JSONResult;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("init")
public class GlobalController extends BaseRestController {

    private static final Logger logger = LoggerFactory.getLogger(GlobalController.class);

    @Autowired
    private GlobalFeignClient globalFeignClient;



    @RequestMapping("/init")
    public JSONResult init(HttpServletRequest request) {
        logger.debug("Received rest /init/init GET request.");
        return globalFeignClient.getAppConfigMap();
    }


    @RequestMapping("upgrade")
    public JSONResult upgrade(
            @RequestParam(value = "ver", required = true, defaultValue = "0") String ver,
            @RequestParam(value = "os", required = true, defaultValue = "") String os,
            @RequestParam(value = "partner", required = true, defaultValue = "") String partner,
            HttpServletRequest request) {
        logger.debug("Received rest /init/upgrade GET request.");
        return globalFeignClient.needUpgrade(os, ver, partner);
    }

}