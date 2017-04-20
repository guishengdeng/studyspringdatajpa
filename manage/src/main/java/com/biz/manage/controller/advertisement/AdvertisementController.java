package com.biz.manage.controller.advertisement;

import com.biz.gbck.activity.frontend.ActivityVo;
import com.biz.gbck.advertisement.frontend.AdvertisementVo;
import com.biz.gbck.advertisement.frontend.request.AdvertisementRequestVo;
import com.biz.service.activity.interfaces.ActivityService;
import com.biz.service.advertisement.interfaces.AdvertisementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by xys on 2017/4/18.
 */
@Controller
@RequestMapping("manage/advertisement")
public class AdvertisementController {

    private static final Logger logger = LoggerFactory.getLogger(AdvertisementController.class);

    @Autowired
    private AdvertisementService advertisementService;

    @RequestMapping("/list")
    public ModelAndView toList() {
        List<AdvertisementVo> advertisements = advertisementService.findAllAdvertisements();
        ModelAndView view = new ModelAndView("manage/advertisement/list");

        return view.addObject("advertisements",advertisements);
    }

    /**
     * 跳转到新建广告页面
     */
    @RequestMapping("/add")
    public ModelAndView toAdd() {
        ModelAndView view = new ModelAndView("manage/advertisement/add");
        return view;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(@RequestParam("id") String id) {
        ModelAndView view = new ModelAndView("manage/advertisement/add", "advertisement", advertisementService.findById(id));
        view.addObject("cmd", "edit");
        return view;
    }

    @RequestMapping("/delete")
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
