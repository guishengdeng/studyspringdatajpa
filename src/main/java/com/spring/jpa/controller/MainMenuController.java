package com.spring.jpa.controller;

import com.spring.jpa.model.MainMenu;
import com.spring.jpa.model.MenuItem;
import com.spring.jpa.model.vo.MainMenuVo;
import com.spring.jpa.service.MainMenuService;
import com.spring.jpa.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MainMenuController
 *
 * @author guisheng.deng
 * @date 2017年04月01日
 * @reviewer
 * @description
 * @see
 */
@Controller
@RequestMapping(value = "/mainmenu")
public class MainMenuController {
    @Autowired
    private MainMenuService mainMenuService;
    @Autowired
    private MenuItemService menuItemService;
    @RequestMapping(value = "/mainmenulist")
    public String getMainMenuList(Model model){
        Iterable<MainMenu> mainMenus=mainMenuService.getMainMenuList();
        model.addAttribute("mainmenus",mainMenus);
        return "mainmenu";
    }
    @RequestMapping(value="/update")
    @ResponseBody
    public Map<String,Object> updateMainMenu(Long id){
        Map<String,Object> map=new HashMap<String,Object>();
        MainMenu mainMenu=mainMenuService.getMainMenuById(id);
        Iterable<MenuItem> menuItems=menuItemService.getMenuItems();
        map.put("mainMenu",mainMenu);
        map.put("currentMenuItem",mainMenu.getMenuItems());
        map.put("menuItems",menuItems);
        return map;
    }
    @RequestMapping(value="/add")
    @ResponseBody
    public Map<String,Object> getMenuItems(Model model){
        Iterable<MenuItem> menuItems=menuItemService.getMenuItems();
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("menuItems",menuItems);
        return map;
    }
    @RequestMapping(value="/updateOrAddSubmit")
    public String addOrUpdate(MainMenuVo vo){
        MainMenu mainMenu=new MainMenu();
        mainMenu.setId(vo.getId());
        mainMenu.setName(vo.getName());
        mainMenu.setDescription(vo.getDescription());
        mainMenu.setCode(vo.getCode());
        List<MenuItem> menuItems=new ArrayList<MenuItem>();
        if(vo.getMenuitem_id()!=null){
            for(Long menuitem_id:vo.getMenuitem_id()){
                MenuItem menuItem=menuItemService.getMenuItemById(menuitem_id);
                menuItems.add(menuItem);
            }
        }
        mainMenu.setMenuItems(menuItems);
        mainMenuService.addOrUpdate(mainMenu);
        return "redirect:mainmenulist.action";
    }
    @RequestMapping(value = "/delete")
    public String delete(Long id){
        mainMenuService.deleteById(id);
        return "redirect:mainmenulist.action";
    }
}