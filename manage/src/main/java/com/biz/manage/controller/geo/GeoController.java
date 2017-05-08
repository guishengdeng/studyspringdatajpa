package com.biz.manage.controller.geo;

import com.biz.gbck.common.com.GeoStatus;
import com.biz.gbck.model.geo.IArea;
import com.biz.gbck.vo.geo.GeoTreeVo;
import com.biz.gbck.vo.geo.SimpleRegionVo;
import com.biz.service.geo.interfaces.GeoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 商品管理
 * Created by defei on 3/21/16.
 */
@Controller
@RequestMapping("geo") public class GeoController {
    @Autowired
    private GeoService geoService;
    private static final Logger logger = LoggerFactory.getLogger(GeoController.class);

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('OPT_GEO_TREELIST')")
    public ModelAndView forwardToNewPage() {

        ModelAndView mv = new ModelAndView("geo/list");
        logger.debug("Received /geo/list GET request.");
        List<GeoTreeVo> regionVos = geoService.findRegionByRegionLevel(IArea.LEVEL_REGION);
        List<GeoTreeVo> provincVos = geoService.findRegionByRegionLevel(IArea.LEVEL_PROVINCE);
        List<GeoTreeVo> cityVos = geoService.findRegionByRegionLevel(IArea.LEVEL_CITY);
        List<GeoTreeVo> districtVos = geoService.findRegionByRegionLevel(IArea.LEVEL_DISTRICT);
        List<GeoTreeVo> businessVos = geoService.findRegionByRegionLevel(IArea.LEVEL_BUSINESS);
        mv.addObject("geoStatus", GeoStatus.values());
        mv.addObject("regions", regionVos);
        mv.addObject("provinces", provincVos);
        mv.addObject("cities", cityVos);
        mv.addObject("districts", districtVos);
        mv.addObject("businesses", businessVos);
        return mv;
    }

    @RequestMapping(value = "/listChildren", method = RequestMethod.GET)
    public ModelAndView forwardToNewPage(@RequestParam(value = "areaLevel") Integer areaLevel,
                                         @RequestParam(value = "regionId") Integer regionId) {

        logger.debug("Received /geo/listChildren GET request with areaLevel:{}, regionId:{}.",
            areaLevel, regionId);
        return new ModelAndView("geo/selectHtml", "regions",
            geoService.findRegionByParentAreaLevelAndParentId(areaLevel, regionId));
    }

    @RequestMapping(value = "/findChildren", method = RequestMethod.POST)
    public List<SimpleRegionVo> findChildren(@RequestParam(value = "areaLevel") Integer areaLevel,
                                             @RequestParam(value = "id") Integer id) {

        logger.debug("Received /geo/listChildren GET request with areaLevel:{}, regionId:{}.",
            areaLevel, id);
        return geoService.findRegionByParentAreaLevelAndParentId(areaLevel, id);
    }

   /* @RequestMapping(value = "/getChildInfoByIdandType", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('OPT_GEO_DETAIL')")
    @ResponseBody
    public List<GeoInfoVo> getChildInfoByIdandType(@RequestParam(value = "id") Integer id,
        @RequestParam(value = "type") String type) {
        logger.debug("Received /geo/getChileInfoByIdandType POST request with id:{}, type:{}.", id,
            type);
        return geoService.getGeoChileInfoByIdandType(id, type);
    }

    @RequestMapping(value = "/getInfoByIdandType", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('OPT_GEO_DETAIL')")
    @ResponseBody
    public List<GeoInfoVo> getInfoByIdandType(@RequestParam(value = "id") Integer id,
        @RequestParam(value = "type") String type) {
        logger
            .debug("Received /geo/getInfoByIdandType POST request with id:{}, type:{}.", id, type);
        return geoService.getGeoInfoByIdandType(id, type);
    }

    @RequestMapping(value = "deleteGeoInfo", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('OPT_GEO_DELETE')")
    @ResponseBody
    public Boolean deleteGeoInfo(@RequestParam("id") Integer id,
        @RequestParam("type") String type) {
        logger.debug("Received /geo/delete Post request.");
        return geoService.disableGeo(id, type);
    }

    @RequestMapping(value = "addRegion", method = RequestMethod.POST) @ResponseBody
    public Boolean addRegion(GeoInfoVo vo) {
        logger.debug("Received /geo/addRegion Post request.{},,,{}", vo.getId(), vo.getName());
        return geoService.addRegion(vo.getId(), vo.getName());
    }

    @RequestMapping(value = "addChildGeo", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('OPT_GEO_SAVE')")
    @ResponseBody
    public Boolean addChildGeo(GeoInfoVo vo) {
        logger.debug("Received /geo/addChildGeo Post request.{},,,{}", vo.getId(), vo.getName());
        return geoService.addChildGeo(vo);
    }

    @RequestMapping(value = "updateGeo", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('OPT_GEO_SAVE')")
    @ResponseBody
    public ModelAndView updateGeo(GeoInfoVo vo) {
        logger.debug("Received /geo/updateGeo Post request with id={}&type={}", vo.getId(),
            vo.getVoType());
        geoService.updateGeo(vo);
        return new ModelAndView("redirect:/geo.do");
    }
*/
}
