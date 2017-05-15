package com.biz.rest.controller;

import com.biz.core.ali.oss.util.OssUtil;
import com.biz.gbck.dao.redis.ro.upgrade.UpgradeRo;
import com.biz.gbck.vo.config.AppConfigVo;
import com.biz.service.app.AppService;
import com.biz.service.upgrade.UpgradeService;
import com.biz.support.web.handler.JSONResult;
import javax.servlet.http.HttpServletRequest;

import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("init")
public class GlobalController { //extends BaseController

    private static final Logger logger = LoggerFactory.getLogger(GlobalController.class);

    @Autowired(required = false)
    private UpgradeService upgradeService;

    @Autowired
    private AppService appService;


    @RequestMapping("/init")
    public JSONResult init(HttpServletRequest request) {
        Map result = new HashMap();
        AppConfigVo config = appService.getAppConfigVo();
        result.putAll(config.getMap());
        result.put("oss", OssUtil.getOssBuckets());
        result.put("categories",appService.getCategories());
        return new JSONResult(result);
    }


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
