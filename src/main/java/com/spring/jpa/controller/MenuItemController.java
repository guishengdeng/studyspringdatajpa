package com.spring.jpa.controller;

import com.spring.jpa.model.MainMenu;
import com.spring.jpa.model.MenuItem;
import com.spring.jpa.model.Role;
import com.spring.jpa.model.vo.MenuItemVo;
import com.spring.jpa.service.MainMenuService;
import com.spring.jpa.service.MenuItemService;
import com.spring.jpa.service.RoleService;
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
 * MenuItemController
 *
 * @author guisheng.deng
 * @date 2017年04月01日
 * @reviewer
 * @description
 * @see
 */
@Controller
@RequestMapping(value="/menuitem")
public class MenuItemController {
    @Autowired
    private MenuItemService menuItemService;
    @Autowired
    private MainMenuService mainMenuService;
    @Autowired
    private RoleService roleService;
    @RequestMapping(value="/menuitemlist")
    public String getMenuItems(Model model){
        Iterable<MenuItem> menuItems=menuItemService.getMenuItems();
        model.addAttribute("menuItems",menuItems);
        return "menuitem";
    }
    @RequestMapping(value="/add")
    @ResponseBody
    public Map<String,Object> addData(){
        Map<String,Object> map=new HashMap<String,Object>();
        Iterable<MainMenu> mainMenus=mainMenuService.getMainMenuList();
        Iterable<Role> roles=roleService.getRoleList();
        map.put("mainMenus",mainMenus);
        map.put("roles",roles);
        return map;
    }
    @RequestMapping(value="/updateOrAddSubmit")
    public String addOrUpdateSubmit(MenuItemVo vo){
        MenuItem menuItem=new MenuItem();
        menuItem.setMenuitem_id(vo.getMenuitem_id());
        menuItem.setDescription(vo.getDescription());
        menuItem.setSymbol(vo.getSymbol());
        menuItem.setLink(vo.getLink());
        menuItem.setName(vo.getName());
        List<Role> roles=new ArrayList<Role>();
        if(vo.getId()!=null){
                MainMenu mainMenu=mainMenuService.getMainMenuById(vo.getId());
                menuItem.setMainMenu(mainMenu);
        }
        if (vo.getRole_id()!=null){
            for(Long role_id:vo.getRole_id()){
                Role role=roleService.getRoleById(role_id);
                roles.add(role);
            }
        }
        menuItem.setRoles(roles);
        menuItemService.addOrUpdate(menuItem);
        return "redirect:menuitemlist.action";
    }
    @RequestMapping(value="/update")
    @ResponseBody
    public Map<String,Object> update(Long menuitem_id){
        Map<String,Object> map=new HashMap<String,Object>();
        MenuItem menuItem=menuItemService.getMenuItemById(menuitem_id);
        MainMenu currentMainMenu=menuItem.getMainMenu();
        Iterable<MainMenu> mainMenus=mainMenuService.getMainMenuList();
        List<Role> currentRoles=menuItem.getRoles();
        Iterable<Role> roles=roleService.getRoleList();
        map.put("menuItem",menuItem);
        map.put("currentMainMenu",currentMainMenu);
        map.put("mainMenus",mainMenus);
        map.put("currentRoles",currentRoles);
        map.put("roles",roles);
        return map;
    }
    @RequestMapping(value="/delete")
    public String delete(Long menuitem_id){
         menuItemService.delete(menuitem_id);
         return "redirect:menuitemlist.action";
    }
}