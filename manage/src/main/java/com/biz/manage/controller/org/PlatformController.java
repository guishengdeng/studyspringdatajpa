package com.biz.manage.controller.org;

import com.biz.gbck.enums.org.CompanyLevel;
import com.biz.gbck.vo.platform.*;
import com.biz.gbck.vo.spring.PageVO;
import com.biz.service.org.interfaces.PlatformService;
import com.biz.soa.feign.client.org.PlatformFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
    private PlatformFeignClient platformFeignClient;

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
        PageVO<PlatformResSearchVo> platformSearchResVoPage = platformFeignClient.findPlatformList(vo);
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
        PageVO<PartnerSearchResVo> partnerSearchResVoPage = platformFeignClient.findPartnerList(vo);
        mav.addObject("partnerSearchResVoPage", partnerSearchResVoPage);
        mav.addObject("vo", vo);
        return mav;
    }

    @GetMapping("partnerEdit")
    @PreAuthorize("hasAuthority('OPT_PLATFORM_LIST')")
    public ModelAndView partnerEdit( Long id) {
        logger.debug("Received /platform/partnerEdit GET request.");
        ModelAndView mav = new ModelAndView("org/platform/partnerEdit");
        PartnerSearchResVo partnerSearchResVo=platformFeignClient.findPartnerById(id);
        mav.addObject("partner",partnerSearchResVo);
        return mav;
    }

/*

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
*/

//
//    @PostMapping(value = "/save")
//    @PreAuthorize("hasAuthority('OPT_COMPANY_GROUP_EDIT')")
//    public ModelAndView save(@Valid CompanyGroupReqVo companyGroupReqVo, BindingResult bindingResult) {
//        error(bindingResult);
//        companyGroupService.saveOrUpdate(companyGroupReqVo);
//
//        return new ModelAndView("redirect:/companyGroup/list.do");
//    }

      @PostMapping("/findByCompanyLevel")
      @PreAuthorize("hasAuthority('OPT_PLATFORM_LIST')")
      @ResponseBody
      public List<PlatFormRespVo> findByCompanyLevel(CompanyLevel companyLevel){

          return platformService.getRespVoByCompanyLevel(companyLevel);
      }


}
