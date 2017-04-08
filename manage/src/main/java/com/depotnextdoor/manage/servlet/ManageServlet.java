package com.depotnextdoor.manage.servlet;

/**
 * 自定义Servlet
 *
 * @author defei
 * @date 2017年04月06日
 * @reviewer
 */
public class ManageServlet extends MarvelServlet {

    private static ThreadLocal<Object> threadLocalAdmin = new ThreadLocal<>();

    public static Object getAdmin() {
        return threadLocalAdmin.get();
    }

    public ManageServlet(Object admin) {
        threadLocalAdmin.set(admin);
    }
}
