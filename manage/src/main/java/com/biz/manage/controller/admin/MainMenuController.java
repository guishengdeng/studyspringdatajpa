package com.biz.manage.controller.admin;

import com.biz.gbck.dao.mysql.po.security.Admin;
import com.biz.gbck.dao.mysql.po.security.MainMenu;
import com.biz.gbck.dao.mysql.po.security.MenuItem;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.service.IdService;
import com.biz.service.security.MainMenuServiceImpl;
import com.biz.service.security.interfaces.MainMenuService;
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
    @Autowired
    private IdService idService;
    @GetMapping
    @PreAuthorize("hasAuthority('OPT_MAINMENU_LIST')")
    public ModelAndView list(@RequestParam(value = "status" ,required = false,defaultValue = "ENABLE") CommonStatusEnum status){
        List<MainMenu> mainMenus = mainMenuService.listByStatus(status);
        return new ModelAndView("manage/menu/menulist", "mainMenus", mainMenus);
    }
    @RequestMapping("/detail")
    @PreAuthorize("hasAuthority('OPT_MAINMENU_LIST')")//权限暂不清楚，暂时定为这个权限
    public String getMenuItems(@RequestParam("id") Long id, Model model, HttpSession session){
        MainMenu mainMenu=mainMenuService.getMainMenu(id);
        List<MenuItem> menuItems=mainMenu.getMenuItems();
        model.addAttribute("mainMenu",mainMenu);
        model.addAttribute("menuItems",menuItems);
        session.setAttribute("mainmenu_id",id);
        return "manage/menu/menuItem";
    }
    //模拟CatController类里的 edit方法，用的是RESTful风格
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
    @RequestMapping("/addOrUpdate")
    @PreAuthorize("hasAuthority('OPT_MAINMENU_ADD')")
    public String addOrUpdate(MainMenu mainMenu){
        if(mainMenu.getId()==null){
            mainMenu.setId(idService.nextId());
        }
        mainMenuService.addOrUpdate(mainMenu);
        return "redirect:/manage/mainMenus";
    }
    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('OPT_MAINMENU_DELETE')")
    @ResponseBody
    public Boolean delete(@RequestParam("id")Long id){
        //严格来讲,这里的删除应该是逻辑删除,而不是物理删除
            //mainMenuService.delete(id);
            //return "redirect:/manage/mainMenus";
         if(id!=null){
             mainMenuService.delete(id);
             return true;
         }
          return false;
    }
    @RequestMapping("/isExist")
    @PreAuthorize("hasAuthority('OPT_MAINMENU_ADD')")
    @ResponseBody
    public Boolean isExist(MainMenu mainMenu){
        return mainMenuService.isExistMainMenu(mainMenu);
    }
}