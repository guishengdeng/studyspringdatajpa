package com.depotnextdoor.manage.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * 登出处理器
 *
 * @author defei
 * @date 2017年04月06日
 * @reviewer
 */
public class ManageLogoutSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler
        implements LogoutSuccessHandler {

    public void onLogoutSuccess(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse, Authentication authentication)
            throws IOException, ServletException {
        super.handle(httpServletRequest, httpServletResponse, authentication);
    }
}
