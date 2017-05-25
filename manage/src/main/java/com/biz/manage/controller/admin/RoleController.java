package com.biz.manage.controller.admin;

import com.biz.gbck.dao.mysql.po.security.Role;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.manage.controller.BaseController;
import com.biz.service.IdService;
import com.biz.service.security.interfaces.MainMenuService;
import com.biz.service.security.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * RoleController
 *
 * @author guisheng.deng
 * @date 2017年04月20日
 * @reviewer
 * @description
 * @see
 */
@RequestMapping("manage/roles")
@Secured("ROLE_ROLE")
@Controller
public class RoleController extends BaseController{
    @Autowired
    private RoleService roleService;
    @Autowired
    private IdService idService;
    @Autowired
    private MainMenuService mainMenuService;
    @GetMapping
    @PreAuthorize("hasAuthority('OPT_ROLE_LIST')")
    public String listRoles(Model model, @RequestParam(value = "status",required = false,defaultValue = "ENABLE")CommonStatusEnum status){
       model.addAttribute("roles",roleService.findByStatus(status));
       return "manage/role/roleList";
    }
    @RequestMapping("/addOrUpdate")
    @PreAuthorize("hasAuthority('OPT_ROLE_EDIT')")
    public String addOrUpdate(@Valid Role role, BindingResult result){
        if(role.getId()==null)
            role.setId(idService.nextId());
        error(result);
        roleService.addOrUpdate(role);
        return "redirect:/manage/roles";
    }
    @RequestMapping("/add")
    @PreAuthorize("hasAuthority('OPT_ROLE_ADD')")
    public String add(Model model){
        model.addAttribute("cmd","add");
        model.addAttribute("mainmenus",mainMenuService.listMainMenus());
        return "manage/role/addOrUpdateRole";
    }
    @RequestMapping("/edit")
    @PreAuthorize("hasAuthority('OPT_ROLE_EDIT')")
    public String edit(Model model, @RequestParam("id") Long id){
        Role role = id != null ? roleService.getRole(id) : null;
        model.addAttribute("role",role);
        model.addAttribute("cmd","edit");
        model.addAttribute("mainmenus",mainMenuService.listMainMenus());
        return "manage/role/addOrUpdateRole";
    }
    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('OPT_ROLE_DELETE')")
    @ResponseBody
    public Boolean delete(@RequestParam("id") Long id){
      //逻辑删除,不是物理删除。用ajax请求
       if(id!=null){
           roleService.delete(id);
           return true;
       }
       return false;
    }
    @RequestMapping("/isExist")
    @PreAuthorize("hasAuthority('OPT_ROLE_ADD')")
    @ResponseBody
    public Boolean isExist(Role role){
        return roleService.isExist(role);
    }
}