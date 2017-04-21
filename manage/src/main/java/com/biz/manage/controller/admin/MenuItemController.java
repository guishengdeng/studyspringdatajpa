package com.biz.manage.controller.admin;

import com.biz.gbck.dao.mysql.po.security.MainMenu;
import com.biz.gbck.dao.mysql.po.security.MenuItem;
import com.biz.gbck.dao.mysql.po.security.Resource;
import com.biz.gbck.vo.menu.MenuItemVo;
import com.biz.service.IdService;
import com.biz.service.security.interfaces.MainMenuService;
import com.biz.service.security.interfaces.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Random;

/**
 * MenuItemController
 *
 * @author guisheng.deng
 * @date 2017年04月19日
 * @reviewer
 * @description
 * @see
 */
@Controller
@RequestMapping("manage/menuItems")
@Secured("ROLE_MENUITEM")
public class MenuItemController {
    @Autowired
    private MenuItemService menuItemService;
    @Autowired
    private MainMenuService mainMenuService;
    @Autowired
    private IdService idService;
    @RequestMapping("/list")
    @PreAuthorize("hasAuthority('OPT_MENUITEM_LIST')")
    public String list(Model model){
        List<MenuItem> menuItems=menuItemService.listMenuItems();
        model.addAttribute("menuItems",menuItems);
        return "manage/menu/menuItem";
    }
    @RequestMapping("/edit")
    @PreAuthorize("hasAuthority('OPT_MENUITEM_EDIT')")
    public ModelAndView edit(@RequestParam("id") Long id){
        ModelAndView view=null;
        if(id!=null){
            MenuItem menuItem=menuItemService.getMenuItem(id);
            view=new ModelAndView("manage/menu/addOrUpdateMenuItem","menuItem",menuItem)
                    .addObject("cmd","edit")
                    .addObject("mainMenus",mainMenuService.listMainMenus());
        }
        return view;
    }
    @RequestMapping("/add")
    @PreAuthorize("hasAuthority('OPT_MENUITEM_ADD')")
    public String add(Model model){
         model.addAttribute("cmd","add");
         model.addAttribute("mainMenus",mainMenuService.listMainMenus());
         return "manage/menu/addOrUpdateMenuItem";
    }
    @RequestMapping("/addOrUpdate")
    @PreAuthorize("hasAuthority('OPT_MENUITEM_ADD')")
    public String addOrUpdate(MenuItem menuItem){
        if(menuItem.getId()==null){
            menuItem.setId(idService.nextId());
        }
        menuItemService.addOrUpdate(menuItem);
        return "redirect:list.do";
    }
    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('OPT_MENUITEM_DELETE')")
    public String delete(@RequestParam("id") Long id){
        if(id!=null){
            menuItemService.delete(id);
        }
        return "redirect:manage/menuItems";
    }
    @RequestMapping("/detail")
    @PreAuthorize("hasAuthority('OPT_MENUITEM_LIST')")
    public String  detail(@RequestParam("id") Long id, Model model,HttpSession session){
        if(id!=null){
            MenuItem menuItem = menuItemService.getMenuItem(id);
            List<Resource> resources=menuItem.getResources();
            model.addAttribute("resources",resources);
            session.setAttribute("menuitem_id",id);
        }
        return "manage/resource/resourceList";
    }
}