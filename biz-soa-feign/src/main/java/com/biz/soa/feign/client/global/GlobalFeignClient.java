package com.biz.soa.feign.client.global;

import com.biz.gbck.dao.redis.ro.upgrade.UpgradeRo;
import com.biz.gbck.vo.upgrade.AddUpgradeVo;
import com.biz.soa.feign.hystrix.global.GlobalFeignClientHystrix;
import com.biz.support.web.handler.JSONResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * @author: dylan 启动上报
 * @date 5/10/17 15:00
 */
@FeignClient(name = "soa-org", fallback = GlobalFeignClientHystrix.class)
public interface GlobalFeignClient {
    /**
     * 获取启动上报返回参数
     */
    @RequestMapping(value = "soa/init/init")
    JSONResult getAppConfigMap();

    /**
     * 获取升级配置
     * @param os 型号 ios android
     * @param ver 现在的版本号
     * @param inhourse 是否强制升级
     * @return
     */
    @RequestMapping(value = "soa/init/upgrade")
    JSONResult needUpgrade(@RequestParam("os")String os, @RequestParam("ver")String ver,
                           @RequestParam("inhourse")String inhourse);

    /**
     * 根据型号查询升级配置集合
     * @param os
     * @return
     */
    @RequestMapping(value = "soa/init/findUpgradeByOs")
    List<UpgradeRo> findUpgradeByOs(@RequestParam("os") String os);

    /**
     * 根据id删除对应升级配置
     * @param id
     */
    @RequestMapping(value = "soa/init/deleteUpgradeById")
    void deleteUpgradeById(@RequestParam("id") String id);

    /**
     * 添加升级配置
     * @param upgrade
     */
    @RequestMapping(value = "soa/init/saveUpgrade")
    void saveUpgrade(@RequestBody AddUpgradeVo upgrade);

    /**
     * 判断给的升级版本号码是否存在
     * @param version
     * @param os
     * @return
     */
    @RequestMapping(value = "soa/init/verifyVersion")
    boolean verifyVersion(@RequestParam("version") String version, @RequestParam("os") String os);
}
