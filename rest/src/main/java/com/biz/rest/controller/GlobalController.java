package com.biz.rest.controller;

import com.biz.gbck.dao.redis.ro.upgrade.UpgradeRo;
import com.biz.service.upgrade.UpgradeService;
import com.biz.support.web.handler.JSONResult;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("init")
public class GlobalController { //extends BaseController

    private static final Logger logger = LoggerFactory.getLogger(GlobalController.class);

    @Autowired(required = false)
    private UpgradeService upgradeService;

    @RequestMapping("upgrade")
    public JSONResult upgrade(
            @RequestParam(value = "ver", required = true, defaultValue = "0") String ver,
            @RequestParam(value = "os", required = true, defaultValue = "") String os,
            @RequestParam(value = "partner", required = true, defaultValue = "") String partner,
            HttpServletRequest request) {
        boolean inhourse = StringUtils.equalsIgnoreCase("inhouse", partner);
        UpgradeRo ro = upgradeService.needUpgrade(os, ver, inhourse);
        if (ro != null) {
            return new JSONResult(ro);
        } else {
            return new JSONResult("need", false);
        }
    }

}
