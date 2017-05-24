package com.biz.soa.org.controller;

import com.biz.gbck.vo.geo.SimpleRegionVo;
import com.biz.soa.org.service.geo.interfaces.GeoSoaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("soa/geo")
public class GeoSoaController extends BaseRestController{

    private static final Logger logger = LoggerFactory.getLogger(GeoSoaController.class);

    @Autowired
    private GeoSoaService geoSoaService;

    @RequestMapping("/findRegionByParentAreaLevelAndParentId")
    public List<SimpleRegionVo> findRegionByParentAreaLevelAndParentId(@RequestParam("areaLevel") Integer areaLevel,
                                                                       @RequestParam("regionId") Integer regionId) {
        return geoSoaService.findRegionByParentAreaLevelAndParentId(areaLevel,regionId);
    }

    @RequestMapping("/findRegionByLevel")
    public List<SimpleRegionVo> findRegionByLevel(@RequestParam("levelProvince") int levelProvince) {
        return geoSoaService.findRegionByLevel(levelProvince);
    }

}
