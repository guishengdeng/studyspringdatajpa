package com.spring.jpa.controller;

import com.spring.jpa.model.User;
import com.spring.jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        List<User> users=userService.showUserList();
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
    public String submitUpate(User user){
        //思路：做修改操作就是：先查询出来是否有该对象，有该对象后，再做修改，然后保存（插入）
        userService.updateOrAddSubmit(user);
        return "redirect:userlist.action";//修改完成后，直接让其转发到查询页面
    }
    @RequestMapping(value = "/delete")
    public String submitUpate(String id){
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
}