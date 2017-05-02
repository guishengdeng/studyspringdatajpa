package com.biz.manage.controller.org;

import com.biz.gbck.dao.mysql.po.org.CompanyGroupPo;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.vo.org.CompanyGroupReqVo;
import com.biz.manage.controller.BaseController;
import com.biz.service.org.interfaces.CompanyGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * @author: liubin
 * @date 4/27/17 10:33
 */
@Controller
@RequestMapping(value = "/companyGroup")
@Secured(value = "ROLE_COMPANY_GROUP")
public class CompanyGroupController extends BaseController {

    @Autowired
    private CompanyGroupService companyGroupService;

    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('OPT_COMPANY_GROUP_LIST')")
    public ModelAndView list() {
        List<CompanyGroupPo> companyGroups = companyGroupService.findByStatus(CommonStatusEnum.ENABLE);
        return new ModelAndView("", "companyGroups", companyGroups);
    }

    @GetMapping(value = "/add")
    @PreAuthorize("hasAuthority('OPT_COMPANY_GROUP_EDIT')")
    public ModelAndView add() {
        return new ModelAndView("");
    }


    @GetMapping(value = "/edit")
    @PreAuthorize("hasAuthority('OPT_COMPANY_GROUP_LIST')")
    public ModelAndView edit(@PathVariable Long id) {
        return new ModelAndView("", "companyGroup", companyGroupService.findOne(id));
    }

    @PostMapping(value = "/save")
    @PreAuthorize("hasAuthority('OPT_COMPANY_GROUP_EDIT')")
    public ModelAndView save(@Valid CompanyGroupReqVo companyGroupReqVo, BindingResult bindingResult) {
        error(bindingResult);
        companyGroupService.saveOrUpdate(companyGroupReqVo);

        return new ModelAndView("redirect:/companyGroup/list.do");
    }

    @PostMapping(value = "/remove")
    @PreAuthorize("hasAuthority('OPT_COMPANY_GROUP_EDIT')")
    @ResponseBody
    public Boolean remove(@PathVariable Long id) {

        try {
            companyGroupService.remove(id);
            return true;
        } catch (Exception e) {
            logger.info("Remove companyGroup [{}] failed.", id, e);
            return false;
        }
    }






}
