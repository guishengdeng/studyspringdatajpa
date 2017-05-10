package com.biz.manage.controller.org;

import com.biz.gbck.dao.mysql.po.org.PartnerPo;
import com.biz.gbck.dao.mysql.po.org.PlatformPo;
import com.biz.gbck.vo.platform.PartnerSearchVo;
import com.biz.gbck.vo.platform.PlatformSearchVo;
import com.biz.service.org.interfaces.PlatformService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 平台公司
 * Created by dylan on 17-5-8.
 */
@Controller
@RequestMapping("/platform")
@Secured("ROLE_PLATFORM")
public class PlatformController {
    private static final Logger logger = LoggerFactory.getLogger(PlatformController.class);
    @Autowired
    private PlatformService platformService;

    /**
     * 进入平台公司列表
     */
    @GetMapping("platformList")
    @PreAuthorize("hasAuthority('OPT_PLATFORM_LIST')")
    public ModelAndView platformList(PlatformSearchVo vo) {
        logger.debug("Received /platform/platformList GET request.");
        ModelAndView mav = new ModelAndView("org/platform/platformList");
        Page<PlatformPo> platformSearchResVoPage = platformService.findPlatformList(vo);
        mav.addObject("platformSearchResVoPage", platformSearchResVoPage);
        mav.addObject("vo", vo);
        return mav;
    }

    /**
     * 查询合伙人
     */
    @GetMapping("partnerList")
    @PreAuthorize("hasAuthority('OPT_PLATFORM_LIST')")
    public ModelAndView findPartnerList(PartnerSearchVo vo) {
        logger.debug("Received /platform/partnerList GET request.");
        ModelAndView mav = new ModelAndView("org/platform/partnerList");
        Page<PartnerPo> partnerSearchResVoPage = platformService.findPartnerList(vo);
        mav.addObject("partnerSearchResVoPage", partnerSearchResVoPage);
        mav.addObject("vo", vo);
        return mav;
    }

    @GetMapping("partnerEdit")
    @PreAuthorize("hasAuthority('OPT_PLATFORM_LIST')")
    public ModelAndView partnerEdit( Long id) {
        logger.debug("Received /platform/partnerEdit GET request.");
        ModelAndView mav = new ModelAndView("org/platform/partnerEdit");
        PartnerPo partnerPo=platformService.findPartnerById(id);
        mav.addObject("partner",partnerPo);
        return mav;
    }


    @GetMapping(value = "/add")
    @PreAuthorize("hasAuthority('OPT_PLATFORM_EDIT')")
    public ModelAndView add() {
        return new ModelAndView("");
    }


    @GetMapping(value = "/edit")
    @PreAuthorize("hasAuthority('OPT_PLATFORM_LIST')")
    public ModelAndView edit(@PathVariable Long id) {
        return new ModelAndView("", "companyGroup", platformService.findOne(id));
    }

//
//    @PostMapping(value = "/save")
//    @PreAuthorize("hasAuthority('OPT_COMPANY_GROUP_EDIT')")
//    public ModelAndView save(@Valid CompanyGroupReqVo companyGroupReqVo, BindingResult bindingResult) {
//        error(bindingResult);
//        companyGroupService.saveOrUpdate(companyGroupReqVo);
//
//        return new ModelAndView("redirect:/companyGroup/list.do");
//    }



}
