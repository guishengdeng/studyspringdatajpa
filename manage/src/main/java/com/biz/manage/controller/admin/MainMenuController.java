package com.biz.manage.controller.admin;

import com.biz.gbck.dao.mysql.po.security.Admin;
import com.biz.gbck.dao.mysql.po.security.MainMenu;
import com.biz.gbck.dao.mysql.po.security.MenuItem;
import com.biz.service.security.interfaces.MainMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * MainMenuController
 *
 * @author guisheng.deng
 * @date 2017年04月18日
 * @reviewer
 * @description
 * @see
 */
@RequestMapping("manage/mainMenus")
@Controller
@Secured("ROLE_MAINMENU")
public class MainMenuController {
    @Autowired
    private MainMenuService mainMenuService;

    @GetMapping
    @PreAuthorize("hasAuthority('OPT_MAINMENU_LIST')")
    public ModelAndView list(){
        List<MainMenu> mainMenus = mainMenuService.listMainMenus();
        return new ModelAndView("manage/menu/menulist", "mainMenus", mainMenus);
    }
    @RequestMapping("/detail")
    @PreAuthorize("hasAuthority('OPT_MAINMENU_LIST')")//权限暂不清楚，暂时定为这个权限
    public String getMenuItems(@RequestParam("id") Long id,Model model){
        MainMenu mainMenu=mainMenuService.getMainMenu(id);
        List<MenuItem> menuItems=mainMenu.getMenuItems();
        model.addAttribute("menuItems",menuItems);
        return "manage/menu/menuitem";
    }
    @RequestMapping("/edit")
    @PreAuthorize("hasAuthority('OPT_MAINMENU_EDIT')")
    public String edit(@RequestParam("id")Long id,Model model){
        MainMenu mainMenu=mainMenuService.getMainMenu(id);
        model.addAttribute("mainMenu",mainMenu);
        model.addAttribute("cmd","edit");
        return "manage/menu/addOrUpdate";
    }
    @RequestMapping("/add")
    @PreAuthorize("hasAuthority('OPT_MAINMENU_ADD')")
    public String add(Model model){
        model.addAttribute("cmd","add");
        return "manage/menu/addOrUpdate";
    }
}