package com.biz.manage.controller.admin;

import com.biz.gbck.dao.mysql.po.security.Resource;
import com.biz.service.IdService;
import com.biz.service.security.interfaces.MenuItemService;
import com.biz.service.security.interfaces.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * ResourceController
 *
 * @author guisheng.deng
 * @date 2017年04月20日
 * @reviewer
 * @description
 * @see
 */
@Controller
@Secured("ROLE_RESOURCE")
@RequestMapping("manage/resources")
public class ResourceController {
     @Autowired
     private ResourceService resourceService;
     @Autowired
     private IdService idService;
     @Autowired
     private MenuItemService menuItemService;
     @RequestMapping("/add")
     @PreAuthorize("hasAuthority('OPT_RESOURCE_ADD')")
     public String add(Model model,@RequestParam("menuItem_id") Long id ){
         model.addAttribute("cmd","add");
         return "manage/resource/addOrUpdateResource";
     }
    @RequestMapping("/edit")
    @PreAuthorize("hasAuthority('OPT_RESOURCE_EDIT')")
    public String edit(@RequestParam("id")Long id, Model model){
        if(id!=null){
            Resource resource=resourceService.getResource(id);
            model.addAttribute("resource",resource);

        }
         model.addAttribute("cmd","edit");
        return "manage/resource/addOrUpdateResource";
    }
    @RequestMapping("/list")
    @PreAuthorize("hasAuthority('OPT_RESOURCE_LIST')")
    public String list(Model model){
        model.addAttribute("resources",resourceService.listResources());
        return "manage/resource/resourceList";
    }
    @RequestMapping("/addOrUpdate")
    @PreAuthorize("hasAuthority('OPT_RESOURCE_EDIT')")
    public String addOrUpdate(Resource resource, Model model, HttpSession session){
        if(resource.getId()==null)
            resource.setId(idService.nextId());
        Long menuItem_id=(Long)session.getAttribute("menuitem_id");
        resourceService.addOrUpdate(resource);
        return "redirect:../menuItems/detail.do?id="+menuItem_id;
    }
    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('OPT_RESOURCE_DELETE')")
    @ResponseBody
    public Boolean delete(Long id,HttpSession session){
        /*if(id!=null){
            resourceService.delete(id);
        }
        Long menuItem_id=(Long)session.getAttribute("menuitem_id");
        return "redirect:../menuItems/detail.do?id="+menuItem_id;*/
        if(id!=null){
            return true;
        }
        return false;
    }
}