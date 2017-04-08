package com.depotnextdoor.manage.filter;

import com.depotnextdoor.manage.servlet.ManageServlet;
import com.depotnextdoor.manage.servlet.MarvelServlet;
import com.depotnextdoor.manage.util.AuthorityUtil;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 管理后台过滤器
 *
 * @author defei
 * @date 2017年04月06日
 * @reviewer
 */
public class ManageFilter extends MarvelFilter<MarvelServlet> {

    @Override
    public ManageServlet buildMarvelServlet() {
        return new ManageServlet(AuthorityUtil.getLoginUser());
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        filterChain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

}
