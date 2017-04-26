package com.biz.support.web.handler;


import com.biz.core.exceptions.SystemException;
import com.biz.support.web.BuildRequestHandler;
import com.biz.support.web.assist.IPAddressAware;
import com.biz.support.web.util.HttpServletHelper;

import javax.servlet.http.HttpServletRequest;

/**
 * ip地址处理器,如果请求对象实现了IIPVo接口,将请求者ip写入该对象
 *
 * @author yanweijin
 * @usage
 * @reviewer
 * @since 2016年8月16日
 */
public class IpAddressHandler implements BuildRequestHandler {

    @Override
    public void handle(HttpServletRequest request, Object requestVo) throws SystemException {
        if (requestVo instanceof IPAddressAware) {
            IPAddressAware ipvo = (IPAddressAware) requestVo;
            ipvo.setIp(HttpServletHelper.getClientIP(request));
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }

}
