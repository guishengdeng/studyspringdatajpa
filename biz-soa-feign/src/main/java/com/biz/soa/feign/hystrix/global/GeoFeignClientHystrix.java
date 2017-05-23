package com.biz.soa.feign.hystrix.global;



import com.biz.gbck.vo.geo.SimpleRegionVo;
import com.biz.soa.feign.client.global.GeoFeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by  dylan
 */
@Component
public class GeoFeignClientHystrix implements GeoFeignClient {


    @Override
    public List<SimpleRegionVo> findRegionByParentAreaLevelAndParentId(@RequestParam("areaLevel") Integer areaLevel, @RequestParam("regionId") Integer regionId) {
        return null;
    }

    @Override
    public List<SimpleRegionVo> findRegionByLevel(@RequestParam("levelProvince") int levelProvince) {
        return null;
    }
}
