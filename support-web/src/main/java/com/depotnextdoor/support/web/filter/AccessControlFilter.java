package com.depotnextdoor.support.web.filter;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yanweijin
 * @date 2017/2/8
 */
public class AccessControlFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) response;
        String allowDomain = "*";
        resp.setHeader("Access-Control-Allow-Origin", allowDomain);
        chain.doFilter(request, resp);
    }

    @Override
    public void destroy() {

    }
}
