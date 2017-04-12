package com.biz.manage.servlet;

import com.biz.gbck.dao.mysql.po.security.Admin;

/**
 * 自定义Servlet
 *
 * @author defei
 * @date 2017年04月06日
 * @reviewer
 */
public class ManageServlet extends MarvelServlet {

    private static ThreadLocal<Admin> threadLocalAdmin = new ThreadLocal<>();

    public static Admin getAdmin() {
        return threadLocalAdmin.get();
    }

    public ManageServlet(Admin admin) {
        threadLocalAdmin.set(admin);
    }
}
