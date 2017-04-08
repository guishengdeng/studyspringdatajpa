package com.depotnextdoor.support.web.handler;

import com.biz.core.exception.SystemException;
import com.biz.core.util.HttpServletHelper;
import com.biz.support.web.BuildRequestHandler;
import com.biz.support.web.assist.IPAddressAware;
import com.biz.support.web.assist.IRequestVo;
import com.depotnextdoor.support.web.BuildRequestHandler;
import javax.servlet.http.HttpServletRequest;

/**
 * ip地址处理器,如果请求对象实现了IIPVo接口,将请求者ip写入该对象
 * @author yanweijin
 * @since 2016年8月16日
 * @usage 
 * @reviewer
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
