package com.biz.soa.org.controller;

import com.biz.core.ali.oss.util.OssUtil;
import com.biz.gbck.dao.redis.ro.upgrade.UpgradeRo;
import com.biz.gbck.vo.config.AppConfigVo;
import com.biz.service.upgrade.UpgradeService;
import com.biz.soa.org.service.interfaces.AppSoaService;
import com.biz.soa.org.service.upgrade.UpgradeSoaService;
import com.biz.support.web.handler.JSONResult;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("init")
public class GlobalController extends BaseRestController{

    private static final Logger logger = LoggerFactory.getLogger(GlobalController.class);

    @Autowired(required = false)
    private UpgradeSoaService upgradeSoaService;

    @Autowired
    private AppSoaService appSoaService;


    @RequestMapping("/init")
    public JSONResult init(HttpServletRequest request) {
        Map result = new HashMap();
        AppConfigVo config = appSoaService.getAppConfigVo();
        result.putAll(config.getMap());
        result.put("oss", OssUtil.getOssBuckets());
        result.put("categories",appSoaService.getCategories());
        return new JSONResult(result);
    }


    @RequestMapping("upgrade")
    public JSONResult upgrade(
            @RequestParam(value = "ver", required = true, defaultValue = "0") String ver,
            @RequestParam(value = "os", required = true, defaultValue = "") String os,
            @RequestParam(value = "partner", required = true, defaultValue = "") String partner,
            HttpServletRequest request) {
        boolean inhourse = StringUtils.equalsIgnoreCase("inhouse", partner);
        UpgradeRo ro = upgradeSoaService.needUpgrade(os, ver, inhourse);
        if (ro != null) {
            return new JSONResult(ro);
        } else {
            return new JSONResult("need", false);
        }
    }

}
