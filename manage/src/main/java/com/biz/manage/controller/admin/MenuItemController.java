package com.biz.manage.controller.admin;

import com.biz.gbck.dao.mysql.po.security.MainMenu;
import com.biz.gbck.dao.mysql.po.security.MenuItem;
import com.biz.gbck.dao.mysql.po.security.Resource;
import com.biz.gbck.vo.menu.MenuItemVo;
import com.biz.service.IdService;
import com.biz.service.security.interfaces.MainMenuService;
import com.biz.service.security.interfaces.MenuItemService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public String addOrUpdate(MenuItem menuItem,Model model,HttpSession session){
        if(menuItem.getId()==null){
            menuItem.setId(idService.nextId());
        }
        //要获得主菜单的id   从MainMenuController里set
        Long mainMenu_id=(Long)session.getAttribute("mainmenu_id");
        MainMenu mainMenu=mainMenuService.getMainMenu(mainMenu_id);
        model.addAttribute("mainMenu",mainMenu);
        menuItemService.addOrUpdate(menuItem);
        return "redirect:../mainMenus/detail.do?id="+mainMenu_id;
    }
    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('OPT_MENUITEM_DELETE')")
    @ResponseBody
    public Boolean delete(@RequestParam("id") Long id){
       /* if(id!=null){
            menuItemService.delete(id);
        }
        Long mainMenu_id=(Long)session.getAttribute("mainmenu_id");
        model.addAttribute("mainMenu",mainMenuService.getMainMenu(mainMenu_id));
        return "redirect:../mainMenus/detail.do?id="+mainMenu_id;*/
        if(id!=null){
            //说明用户要执行删除操作,此时应该执行调用删除方法,而删除应该是逻辑删除而不是物理删除
            return true;
        }
        return false;

    }
    @RequestMapping("/detail")
    @PreAuthorize("hasAuthority('OPT_MENUITEM_LIST')")
    public String  detail(@RequestParam("id") Long id, Model model,HttpSession session){
        if(id!=null){
            MenuItem menuItem = menuItemService.getMenuItem(id);
            List<Resource> resources=menuItem.getResources();
            model.addAttribute("resources",resources);
            model.addAttribute("menuItem",menuItem);
        }
        session.setAttribute("menuitem_id",id);
        return "manage/resource/resourceList";
    }
}