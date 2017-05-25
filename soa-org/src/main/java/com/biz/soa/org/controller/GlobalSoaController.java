package com.biz.soa.org.controller;

import com.biz.core.ali.oss.BucketResVo;
import com.biz.core.ali.oss.config.OssConfig;
import com.biz.gbck.dao.redis.ro.upgrade.UpgradeRo;
import com.biz.gbck.vo.config.AppConfigVo;
import com.biz.gbck.vo.upgrade.AddUpgradeVo;
import com.biz.soa.org.service.app.interfaces.AppSoaService;
import com.biz.soa.org.service.upgrade.interfaces.UpgradeSoaService;
import com.biz.support.web.handler.JSONResult;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;

@RestController
@RequestMapping("soa/init")
public class GlobalSoaController extends BaseRestController{

    private static final Logger logger = LoggerFactory.getLogger(GlobalSoaController.class);

    @Autowired(required = false)
    private UpgradeSoaService upgradeSoaService;

    @Autowired
    private AppSoaService appSoaService;


    /**
     * 启动上报信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/init", method = RequestMethod.POST)
    public JSONResult init(HttpServletRequest request) {
        logger.debug("Received soa-org /init/init GET request.");
        Map result = new HashMap();
        AppConfigVo config = appSoaService.getAppConfigVo();
        result.putAll(config.getMap());
        result.put("oss", getOssBuckets());
        result.put("categories",appSoaService.getCategories());
        return new JSONResult(result);
    }

    /**
     * 查询是否有可升级版本
     * @param ver
     * @param os
     * @param partner
     * @param request
     * @return
     */
    @RequestMapping(value = "upgrade", method = RequestMethod.POST)
    public JSONResult upgrade(
            @RequestParam(value = "ver", required = true, defaultValue = "0") String ver,
            @RequestParam(value = "os", required = true, defaultValue = "") String os,
            @RequestParam(value = "partner", required = true, defaultValue = "") String partner,
            HttpServletRequest request) {
        logger.debug("Received soa-org  /init/upgrade GET request.");
        boolean inhourse = StringUtils.equalsIgnoreCase("inhouse", partner);
        UpgradeRo ro = upgradeSoaService.needUpgrade(os, ver, inhourse);
        if (ro != null) {
            return new JSONResult(ro);
        } else {
            return new JSONResult("need", false);
        }
    }

    /**
     * 根据型号查询升级配置集合
     * @param os
     * @return
     */
    @RequestMapping(value = "findUpgradeByOs", method = RequestMethod.POST)
    public List<UpgradeRo> findUpgradeByOs(@RequestParam("os") String os) {
        return upgradeSoaService.findAll(os);
    }

    /**
     * 根据id删除对应升级配置
     * @param id
     */
    @RequestMapping(value = "deleteUpgradeById", method = RequestMethod.POST)
    public void deleteUpgradeById(@RequestParam("id") String id) {
        upgradeSoaService.delete(id);
    }

    /**
     * 添加升级配置
     * @param upgrade
     */
    @RequestMapping(value = "saveUpgrade", method = RequestMethod.POST)
    public void saveUpgrade(@RequestBody AddUpgradeVo upgrade) {
        upgradeSoaService.save(upgrade);
    }

    /**
     * 判断给的升级版本号码是否存在
     * @param version
     * @param os
     * @return
     */
    @RequestMapping(value = "verifyVersion", method = RequestMethod.POST)
    public boolean verifyVersion(@RequestParam("version") String version, @RequestParam("os") String os) {
        return upgradeSoaService.verifyVersion(version,os);
    }


    /**
     *获取隔壁仓库Oss上传bucket集合
     */
    public List<BucketResVo> getOssBuckets() {
        List<BucketResVo> bucketResVos=newArrayList();
        bucketResVos.add(new BucketResVo("product",config.getProductBucketName()));
        bucketResVos.add(new BucketResVo("audit",config.getAuditBucketName()));
        return bucketResVos;
    }

    @Autowired
    private OssConfig config;

}
