package com.biz.manage.controller.org;

import com.biz.gbck.dao.mysql.po.org.PlatformPo;
import com.biz.manage.controller.BaseController;
import com.biz.service.org.interfaces.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * @author: liubin
 * @date 5/2/17 10:27
 */
@Controller
@RequestMapping(value = "/platform")
@Secured(value = "ROLE_PLATFORM")
public class PlatformController extends BaseController {

    @Autowired
    private PlatformService platformService;

    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('OPT_PLATFORM_LIST')")
    public ModelAndView list() {
        List<PlatformPo> platformPos = platformService.findAll();
        return new ModelAndView("", "platformPos", platformPos);
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
