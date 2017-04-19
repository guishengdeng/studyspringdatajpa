package com.biz.manage.filter;

import com.biz.gbck.dao.mysql.po.security.Admin;
import com.biz.manage.servlet.ManageServlet;
import com.biz.manage.servlet.MarvelServlet;
import com.biz.manage.util.AuthorityUtil;
import com.biz.support.web.util.HttpServletHelper;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * 管理后台过滤器
 *
 * @author defei
 * @date 2017年04月06日
 * @reviewer
 */
public class ManageFilter extends MarvelFilter<MarvelServlet> {

    private static final String ACTIVE_MENU_COOKIE_KEY = "activeMenu";

    private static final String ACTIVE_MAIN_MENU_ = "_activeMainMenu_";

    private static final String ACTIVE_SUB_MENU_ = "_activeSubMenu_";

    @Override
    public ManageServlet buildMarvelServlet() {
        return new ManageServlet((Admin) AuthorityUtil.getLoginUser());
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        String activeMenu = HttpServletHelper.getCookieValue(request, ACTIVE_MENU_COOKIE_KEY);
        if (isNotBlank(activeMenu)) {
            String[] activeMenuInfo = activeMenu.split("\\D");
            if (activeMenuInfo.length == 2) {
                request.setAttribute(ACTIVE_MAIN_MENU_, activeMenuInfo[0]);
                request.setAttribute(ACTIVE_SUB_MENU_, activeMenuInfo[1]);
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    public static void cleanActiveMenu() {
        HttpServletHelper.setCookie(getMarvelServlet().getResponse(), ACTIVE_MENU_COOKIE_KEY, null, 0, "/");
        getMarvelServlet().getRequest().setAttribute(ACTIVE_MAIN_MENU_, null);
        getMarvelServlet().getRequest().setAttribute(ACTIVE_SUB_MENU_, null);
    }

}
