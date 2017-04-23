package com.biz.manage.controller.admin;

import com.biz.gbck.dao.mysql.po.security.Role;
import com.biz.service.IdService;
import com.biz.service.security.interfaces.MenuItemService;
import com.biz.service.security.interfaces.ResourceService;
import com.biz.service.security.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
public class RoleController {
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private MenuItemService menuItemService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private IdService idService;
    @GetMapping
    @PreAuthorize("hasAuthority('OPT_ROLE_LIST')")
    public String listRoles(Model model){
       model.addAttribute("roles",roleService.listRoles());
       return "manage/role/roleList";
    }
    @RequestMapping("/addOrUpdate")
    @PreAuthorize("hasAuthority('OPT_ROLE_EDIT')")
    public String addOrUpdate(Role role){
        if(role.getId()==null)
            role.setId(idService.nextId());
        roleService.addOrUpdate(role);
        return "redirect:/manage/roles";
    }
    @RequestMapping("/add")
    @PreAuthorize("hasAuthority('OPT_ROLE_ADD')")
    public String add(Model model){

        model.addAttribute("cmd","add");
        model.addAttribute("menuItems",menuItemService.listMenuItems());
        model.addAttribute("resources",resourceService.listResources());
        return "manage/role/addOrUpdateRole";
    }
    @RequestMapping("/edit")
    @PreAuthorize("hasAuthority('OPT_ROLE_EDIT')")
    public String edit(Model model, @RequestParam("id") Long id){
        Role role = id!=null?roleService.getRole(id):null;
        model.addAttribute("role",role);
        model.addAttribute("cmd","edit");
        model.addAttribute("menuItems",menuItemService.listMenuItems());
        model.addAttribute("resources",resourceService.listResources());
        return "manage/role/addOrUpdateRole";
    }
    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('OPT_ROLE_DELETE')")
    @ResponseBody
    public Boolean delete(@RequestParam("id") Long id){
       /* roleService.delete(id);
        return "redirect:/manage/roles";*/
       if(id!=null){
           return true;
       }
       return false;
    }
}