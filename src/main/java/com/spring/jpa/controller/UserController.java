package com.spring.jpa.controller;

import com.spring.jpa.model.User;
import com.spring.jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


/**
 * UserController
 *
 * @author guisheng.deng
 * @date 2017年03月09日
 * @reviewer
 * @see
 */
@Controller
@RequestMapping(value="/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/userlist")
    public String getUserList(Model model){
         Iterable<User> users=userService.showUserList();
         model.addAttribute("username",getPrincipal());
         model.addAttribute("users",users);
         return "userlist";
    }
    @RequestMapping(value = "/update")

    public String updateUser(@RequestParam(value = "id") String id,Model model){

        long user_id=Long.parseLong(id);
        User user=userService.getUserById(user_id);
        model.addAttribute("user",user);
        return "updatelist";
    }
    @RequestMapping(value = "/updatesubmit")

    public String submitUpdate(User user, HttpServletRequest request){
        //思路：做修改操作就是：先查询出来是否有该对象，有该对象后，再做修改，然后保存（插入）
        /*JSONObject json = JSONObject.fromObject(request.getParameter("jsondata"));
        System.out.println(json);*/

        userService.updateOrAddSubmit(user);
        return "redirect:userlist.action";
    }
    @RequestMapping(value = "/delete")
    public String submitDelete(String id){
        Long user_id=Long.parseLong(id);
        userService.deleteUser(user_id);
        return "redirect:userlist.action";
    }
    @RequestMapping(value="/add")
    public String addJsp(){
        return "adduser";
    }
    @RequestMapping(value="/adduser")
    public String addUser(User user){
        //user对象是来自前段adduser.jsp页面传递过来的参数
        userService.updateOrAddSubmit(user);
        return "redirect:userlist.action";
    }
    @RequestMapping(value="/login")
    public String userLogin(HttpServletRequest request,HttpSession session,String username){

        session.setAttribute("username",username);
        return "login";
    }
    @RequestMapping(value="/loginSubmit")
    public String  loginSubmit(HttpSession session, User user,HttpServletRequest request){
        //user对象，是从前段login.jsp页面传递过来的参数 因为user对象里有username和password属性，且与input标签的name属性值一致
            session.setAttribute("username",user.getUsername());
            return "forward:userlist.action";
        }
    @RequestMapping(value = "/loginout")
    public String loginOut(HttpSession session, HttpServletResponse response){
          session.invalidate();//销毁session对象。
          return "redirect:login.action";
    }
    @RequestMapping(value = "/access_denied")
    public String accessDenied(Model model){
        model.addAttribute("user",getPrincipal());
        return "access_denied";
    }

    /**
     * @description:用于用户登录时，在登陆成功后
     * 跳转到目标页面，显示登陆用户名
     * @return String
     */
    private String getPrincipal(){
        String username="";
        Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails)
             username=((UserDetails) principal).getUsername();
        else
             username=principal.toString();

        return username;
    }
}