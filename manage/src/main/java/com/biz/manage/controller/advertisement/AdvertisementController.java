package com.biz.manage.controller.advertisement;

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
@RequestMapping("manage/advertisement")
@Secured("ROLE_ADVERTISEMENT")
public class AdvertisementController {

    private static final Logger logger = LoggerFactory.getLogger(AdvertisementController.class);

    @Autowired
    private AdvertisementService advertisementService;

    @GetMapping("list")
    @PreAuthorize("hasAuthority('OPT_ADVERTISEMENTS_LIST')")
    public ModelAndView toList() {
        List<AdvertisementVo> advertisements = advertisementService.findAllAdvertisements();
        ModelAndView view = new ModelAndView("manage/advertisement/list");

        return view.addObject("advertisements",advertisements);
    }

    /**
     * 跳转到新建广告页面
     */
    @GetMapping("add")
    @PreAuthorize("hasAuthority('OPT_ADVERTISEMENTS_CREATE')")
    public ModelAndView toAdd() {
        ModelAndView view = new ModelAndView("manage/advertisement/add");
        return view;
    }

    @RequestMapping("/edit")
    @PreAuthorize("hasAuthority('OPT_ADVERTISEMENTS_UPDATE')")
    public ModelAndView edit(@RequestParam("id") String id) {
        ModelAndView view = new ModelAndView("manage/advertisement/add", "advertisement", advertisementService.findById(id));
        return view;
    }

    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('OPT_ADVERTISEMENTS_DELETE')")
    public ModelAndView delete(@RequestParam("id") String id) {
        advertisementService.delete(id);
        return new ModelAndView("redirect:/manage/advertisement/list.do");
    }

    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView saveOrUpdate(AdvertisementRequestVo req) {
        advertisementService.saveOrUpdateAdvertisement(req);
        return new ModelAndView("redirect:/manage/advertisement/list.do");
    }
}
