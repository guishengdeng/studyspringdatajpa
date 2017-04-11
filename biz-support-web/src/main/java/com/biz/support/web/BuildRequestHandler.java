package com.biz.support.web;

import com.biz.core.exceptions.SystemException;
import com.biz.support.web.assist.IRequestVo;
import javax.servlet.http.HttpServletRequest;
import org.springframework.core.Ordered;

/**
 * 构建请求处理链
 * 所有实现必须被mvc容器管理
 * 返回较小的order值的实现排在处理链更前方
 * @author yanweijin
 * @since 2016年8月17日
 * @usage 
 * @reviewer
 */
public interface BuildRequestHandler extends Ordered {

	void handle(HttpServletRequest request, IRequestVo requestVo) throws SystemException;
	
}
