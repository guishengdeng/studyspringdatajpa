package com.spring.jpa.interceptor;

import com.spring.jpa.model.User;
import com.spring.jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * LoginInterceptor
 *
 * @author guisheng.deng
 * @date 2017年03月16日
 * @reviewer
 * @description
 * @see
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String uri=request.getRequestURI();
        if(uri.indexOf("/user/loginSubmit.action")>=0){
            String username=request.getParameter("username");
            String password=request.getParameter("password");
            User user=null;
            //严格来说，数据库里的用户表的用户名是不能重复的，也就是要求用户名要有唯一性。
            if(!userService.userList(username).isEmpty()){
                 user=userService.userList(username).get(0);
            }
            if(user!=null&&user.getPassword().equals(password)){
                return true;
            }
                request.setAttribute("result","用户名与密码不匹配或者是否注册？");

        }
        HttpSession session=request.getSession();
        String username=(String) session.getAttribute("username");
        if(username!=null){
            return true;
        }
        //执行到这里表示用户信息需要验证，需要跳转到登陆页面
        request.getRequestDispatcher("/WEB-INF/page/login.jsp").forward(request, response);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}