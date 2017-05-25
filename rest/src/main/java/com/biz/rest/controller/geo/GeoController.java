package com.biz.rest.controller.geo;

import com.biz.gbck.common.model.geo.IArea;
import com.biz.gbck.vo.geo.GeoReqVo;
import com.biz.gbck.vo.geo.SimpleRegionVo;
import com.biz.rest.util.RestUtil;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 省市区
 * Created by dylan on 3/21/16.
 */
@Controller
@RequestMapping("geo") public class GeoController {
    private static final Logger logger = LoggerFactory.getLogger(GeoController.class);

    @Autowired
    private GeoFeignClient geoFeignClient;


    /**
     * 获取所有省
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findProvinces")
    public JSONResult findProvinces(HttpServletRequest httpRequest, HttpServletResponse response) {
        List<SimpleRegionVo> simples= geoFeignClient.findRegionByLevel(IArea.LEVEL_PROVINCE);
        return new JSONResult(simples);
    }


    /**
     * 根据省查id询市
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findCityByProvinceId")
    public JSONResult findCityByProvinceId(HttpServletRequest httpRequest, HttpServletResponse response) {
        GeoReqVo geoReqVo=  RestUtil.parseBizData(httpRequest, GeoReqVo.class);
        List<SimpleRegionVo> simples= geoFeignClient.findRegionByParentAreaLevelAndParentId(IArea.LEVEL_PROVINCE, geoReqVo.getId());
        return new JSONResult(simples);
    }

    /**
     * 根据市查id询县区
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findDistrictByCityId")
    public JSONResult findDistrictByCityId(HttpServletRequest httpRequest, HttpServletResponse response) {
        GeoReqVo geoReqVo=  RestUtil.parseBizData(httpRequest, GeoReqVo.class);
        List<SimpleRegionVo> simples= geoFeignClient.findRegionByParentAreaLevelAndParentId(IArea.LEVEL_CITY, geoReqVo.getId());
        return new JSONResult(simples);
    }


}
