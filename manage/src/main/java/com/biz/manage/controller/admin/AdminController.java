package com.biz.manage.controller.admin;

import com.biz.gbck.dao.mysql.po.security.Admin;
import com.biz.gbck.exceptions.product.AdminNotFoundException;
import com.biz.gbck.vo.admin.AdminReqVo;
import com.biz.manage.util.AuthorityUtil;
import com.biz.service.security.interfaces.AdminService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    /**
     * List<Admin> admins = status.isEnable() ? adminService.listEnableAdmins() : adminService.listDisableAdmins();
     @RequestParam(value = "enabled", required = false, defaultValue = "ENABLE") CommonStatusEnum status
     当用户访问该路径时enable参数是没有值的,所有给它一个默认值
     CommonStatusEnum status,
     @ModelAttribute("adminVo")
     @RequestParam(value = "enabled", required = false, defaultValue = "ENABLE") CommonStatusEnum status,
     */
    @GetMapping
    @PreAuthorize("hasAuthority('OPT_USER_LIST')")
    public ModelAndView list( @ModelAttribute("adminVo") AdminReqVo vo) {
        //这是对查询条件的用户进行分页
        Page<Admin> adminPage = adminService.queryAdminsByCondition(vo);
        Boolean flag=true;
        if(vo.getStatus()!=null){
            flag=vo.getStatus().isEnable();
        }
        return new ModelAndView("manage/admin/list", "adminPage", adminPage).addObject("enabled", flag);
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
    @RequestMapping("/save_edit")
    @PreAuthorize("hasAuthority('OPT_USER_EDIT')")
    public String save_edit(Admin admin){
        String creator=AuthorityUtil.getLoginUsername();
        adminService.createAdmin(admin,creator);
        return "redirect:/manage/users";
    }
    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('OPT_USER_DELETE')")
    @ResponseBody
    public Boolean delete(@RequestParam("username") String username){
       if(StringUtils.isNotBlank(username)){
           adminService.deleteAdmin(username);
           return true;
       }
       return false;
    }
    @RequestMapping("/isExist")
    @PreAuthorize("hasAuthority('OPT_USER_ADD')")
    @ResponseBody
    public Boolean isExist(@RequestParam("username") String username,String cmd){
        try {

            return adminService.isExistAdmin(username,cmd);
        } catch (AdminNotFoundException e) {
            e.printStackTrace();
            return false;
        }

    }
}
