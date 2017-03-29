package com.spring.jpa.controller;

import com.spring.jpa.model.Role;
import com.spring.jpa.model.User;
import com.spring.jpa.model.vo.UserRoleVo;
import com.spring.jpa.security.accessdenied.LoginUserValidate;
import com.spring.jpa.service.RoleService;
import com.spring.jpa.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


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
    @Autowired
    private RoleService roleService;
    @RequestMapping(value = "/userlist")
    public String getUserList(Model model,HttpSession session){
         Iterable<User> users=userService.showUserList();
         //model.addAttribute("username",getPrincipal());
         model.addAttribute("users",users);
         session.setAttribute("roleSet",getCurrentUserRole());
         session.setAttribute("username",getPrincipal());
         return "userlist";
    }

    @RequestMapping(value = "{path}")
    public String forwardToPage(@PathVariable String path,@RequestParam(value="action",required = false)String bindAction){
        return path;
    }


    @RequestMapping(value = "/update")
    @ResponseBody
    public  String updateUser(String id){

        JSONObject json = new JSONObject();
        long user_id=Long.parseLong(id);
        User user=userService.getUserById(user_id);
        Iterable<Role> roles=roleService.getRoleList();
        JsonConfig jsonc = new JsonConfig();
        jsonc.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        json.put("user", JSONObject.fromObject(user, jsonc));
        json.put("roles", JSONArray.fromObject(roles, jsonc));
        try{
            return URLEncoder.encode(json.toString(), "UTF-8");
        }catch(Exception e){
            return null;
        }
    }
    @RequestMapping(value = "/updateOrAddSubmit")
    public String updateOrAddSubmit(UserRoleVo vo){
        //思路：做修改操作就是：先查询出来是否有该对象，有该对象后，再做修改，然后保存（插入）
        User user=new User();
        user.setId(vo.getId());
        user.setEmail(vo.getEmail());
        user.setSex(vo.getSex());
        user.setUsername(vo.getUsername());
        user.setAge(vo.getAge());
        user.setPassword(vo.getPassword());
        Set<Role> roles=new HashSet<>();
        if(vo.getRole_id()!=null){
            for(String role_id:vo.getRole_id()){
                Long roleId=Long.parseLong(role_id);
                Role role=roleService.getRoleById(roleId);
                roles.add(role);
            }
        }
         user.setRoles(roles);
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
    @ResponseBody
    public String addJsp(){
        JSONObject json = new JSONObject();
        Iterable<Role> roles=roleService.getList();
        JsonConfig jsonc = new JsonConfig();
        jsonc.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        json.put("roles", JSONArray.fromObject(roles, jsonc));

        try{
            return URLEncoder.encode(json.toString(), "UTF-8");
        }catch(Exception e){
            return null;
        }
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
     * 严格来讲：这个方法应该定义在Service层
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
    private Set<Role> getCurrentUserRole(){
            //相当于是获得实现UserDetails接口的实现类
            Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Set<Role> roleSet=null;
            if(principal instanceof UserDetails)
                roleSet = ((LoginUserValidate) principal).getRoleDirectory();
            else
                roleSet=new HashSet<Role>();

        return roleSet;
    }
}