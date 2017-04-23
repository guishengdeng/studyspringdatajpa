package com.biz.manage.controller.admin;

import com.biz.gbck.dao.mysql.po.security.Admin;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.manage.util.AuthorityUtil;
import com.biz.service.security.interfaces.AdminService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author david-liu
 * @date 2017年04月12日
 * @reviewer
 */
@Controller
@RequestMapping("manage/users")
@Secured("ROLE_USER")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private Md5PasswordEncoder md5PasswordEncoder;

    @GetMapping
    @PreAuthorize("hasAuthority('OPT_USER_LIST')")
    public ModelAndView list(@RequestParam(value = "enabled", required = false, defaultValue = "ENABLE") CommonStatusEnum status) {
        List<Admin> admins = status.isEnable() ? adminService.listEnableAdmins() : adminService.listDisableAdmins();
        return new ModelAndView("manage/admin/list", "admins", admins).addObject("enabled", status.isEnable());
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('OPT_USER_ADD')")
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("manage/admin/add");
        view.addObject("cmd", "add");
        view.addObject("roles", adminService.listAllRoles());
        return view;
    }

    @RequestMapping("/edit")
    @PreAuthorize("hasAuthority('OPT_USER_EDIT')")
    public ModelAndView edit(@RequestParam("username") String username) {
        ModelAndView view = new ModelAndView("manage/admin/add", "admin", adminService.getAdmin(username));
        view.addObject("cmd", "edit");
        view.addObject("roles", adminService.listAllRoles());
        return view;
    }

    @RequestMapping("/detail")
    @PreAuthorize("hasAuthority('OPT_USER_DETAIL')")
    public ModelAndView detail(@RequestParam("username") String username) {
        return new ModelAndView("manage/admin/detail", "admin", adminService.getAdmin(username));
    }

    @RequestMapping("/save_add")
    @PreAuthorize("hasAuthority('OPT_USER_ADD')")
    public ModelAndView save_add(Admin admin, @RequestParam("password") String pwd) {
        admin.setPassword(md5PasswordEncoder.encodePassword(pwd, admin.getUsername()));
        String creator = AuthorityUtil.getLoginUsername();
        adminService.createAdmin(admin, creator);
        return new ModelAndView("redirect:/manage/users");
    }
    //add by denggguisheng------start
    @RequestMapping("/save_edit")
    @PreAuthorize("hasAuthority('OPT_USER_EDIT')")
    public String save_edit(Admin admin){
        //获得当前登陆用户名
        String creator=AuthorityUtil.getLoginUsername();
        //这里的添加和修改其实调用的都是一个方法
        adminService.createAdmin(admin,creator);
        return "redirect:/manage/users";
    }
    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('OPT_USER_DELETE')")
    @ResponseBody
    public Boolean delete(@RequestParam("username") String username){
       /* adminService.deleteAdmin(username);
        return "redirect:/manage/users";*/
       if(username!=null){
           return true;
       }
       return false;
    }
}
