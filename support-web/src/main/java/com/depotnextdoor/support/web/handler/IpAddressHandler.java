package com.depotnextdoor.support.web.handler;


import com.depotnextdoor.core.exceptions.SystemException;
import com.depotnextdoor.manage.util.HttpServletHelper;
import com.depotnextdoor.support.web.BuildRequestHandler;
import com.depotnextdoor.support.web.assist.IPAddressAware;
import com.depotnextdoor.support.web.assist.IRequestVo;
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
    public void handle(HttpServletRequest request, IRequestVo requestVo) throws SystemException {
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
