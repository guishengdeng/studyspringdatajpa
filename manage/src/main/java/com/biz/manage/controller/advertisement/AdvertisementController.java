package com.biz.manage.controller.advertisement;

import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.vo.advertisement.frontend.AdvertisementVo;
import com.biz.gbck.vo.advertisement.frontend.request.AdvertisementRequestVo;
import com.biz.service.advertisement.interfaces.AdvertisementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by xys on 2017/4/18.
 */
@Controller
@RequestMapping("advertisement")
@Secured("ROLE_ADVERTISEMENT")
public class AdvertisementController {

    private static final Logger logger = LoggerFactory.getLogger(AdvertisementController.class);

    @Autowired
    private AdvertisementService advertisementService;

    @GetMapping("list")
    @PreAuthorize("hasAuthority('OPT_ADVERTISEMENTS_LIST')")
    public ModelAndView toList() {
        List<AdvertisementVo> advertisements = advertisementService.findAdvertisementByStatus(CommonStatusEnum.ENABLE.getValue());
        return new ModelAndView("manage/advertisement/list").addObject("advertisements",advertisements);
    }

    /**
     * 跳转到新建广告页面
     */
    @GetMapping("add")
    @PreAuthorize("hasAuthority('OPT_ADVERTISEMENTS_CREATE')")
    public ModelAndView toAdd() {
        return new ModelAndView("manage/advertisement/add");
    }

    @RequestMapping("/edit")
    @PreAuthorize("hasAuthority('OPT_ADVERTISEMENTS_UPDATE')")
    public ModelAndView edit(@RequestParam("id") String id) {
        return new ModelAndView("manage/advertisement/add", "advertisement", advertisementService.findById(id));
    }

    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('OPT_ADVERTISEMENTS_DELETE')")
    public ModelAndView delete(@RequestParam("id") String id) {
        advertisementService.delete(id);
        return new ModelAndView("redirect:/advertisement/list.do");
    }

    @RequestMapping("/disable")
    @PreAuthorize("hasAuthority('OPT_ADVERTISEMENTS_DELETE')")
    public ModelAndView disable(@RequestParam("id") String id) {
        advertisementService.disable(id);
        return new ModelAndView("redirect:/advertisement/list.do");
    }

    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView saveOrUpdate(AdvertisementRequestVo req) {
        advertisementService.saveOrUpdateAdvertisement(req);
        return new ModelAndView("redirect:/advertisement/list.do");
    }
}
