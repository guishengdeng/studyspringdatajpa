package com.biz.manage.controller.geo;

import com.biz.gbck.vo.geo.SimpleRegionVo;
import com.biz.soa.feign.client.global.GeoFeignClient;
import com.biz.support.web.handler.JSONResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 商品管理
 * Created by defei on 3/21/16.
 */
@Controller
@RequestMapping("geo") public class GeoController {
    private static final Logger logger = LoggerFactory.getLogger(GeoController.class);

    @Autowired
    private GeoFeignClient geoFeignClient;




    /**
     * 获取1,2,3（省市县）级菜单对应集合
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/listChildren", method = RequestMethod.GET)
    public JSONResult forwardToNewPage(@RequestParam(value = "areaLevel") Integer areaLevel,
                                         @RequestParam(value = "regionId") Integer regionId) {
        List<SimpleRegionVo> simples= geoFeignClient.findRegionByParentAreaLevelAndParentId(areaLevel, regionId);
        return new JSONResult(simples);
    }


}
