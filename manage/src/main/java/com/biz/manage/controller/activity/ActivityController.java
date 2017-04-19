package com.biz.manage.controller.activity;

import com.biz.gbck.advertisement.frontend.AdvertisementVo;
import com.biz.service.advertisement.interfaces.AdvertisementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by xys on 2017/4/18.
 */
@Controller
@RequestMapping("/manage/advertisement")
public class ActivityController {

    private static final Logger logger = LoggerFactory.getLogger(ActivityController.class);

    @Autowired
    private AdvertisementService advService;

    @RequestMapping("/list")
    public ModelAndView toList() {
        ModelAndView view = new ModelAndView("advertisement/list");
        return view;
    }

    /**
     * 跳转到新建广告页面
     */
    @RequestMapping("/add")
    public ModelAndView toAdd() {
        ModelAndView view = new ModelAndView("advertisement/add");
        return view;
    }

    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView saveOrUpdate(AdvertisementVo req) {
        advService.saveOrUpdateAdvertisement(req);
        return new ModelAndView("redirect:/manage/advertisement/list");
    }
}
