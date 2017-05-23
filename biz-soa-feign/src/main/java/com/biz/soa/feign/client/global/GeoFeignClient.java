package com.biz.soa.feign.client.global;


import com.biz.gbck.vo.geo.SimpleRegionVo;
import com.biz.soa.feign.hystrix.global.GeoFeignClientHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * @author: dylan 省市县
 * @date
 */
@FeignClient(name = "soa-org", fallback = GeoFeignClientHystrix.class)
public interface GeoFeignClient {

    /**
     *根据 123(省市县)级别和对应id查询对应集合
     * @return
     */
    @RequestMapping(value = "soa/geo/findRegionByParentAreaLevelAndParentId", method = RequestMethod.POST)
    List<SimpleRegionVo> findRegionByParentAreaLevelAndParentId(@RequestParam("areaLevel")Integer areaLevel,
                                                                @RequestParam("regionId")Integer regionId);

    /**
     * 根据 1(省)级别，查询省集合
     */
    @RequestMapping(value = "soa/geo/findRegionByLevel", method = RequestMethod.POST)
    List<SimpleRegionVo> findRegionByLevel(@RequestParam("levelProvince")int levelProvince);
}
