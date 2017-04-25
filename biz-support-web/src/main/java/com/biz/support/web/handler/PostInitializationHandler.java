package com.biz.support.web.handler;

import com.biz.core.exceptions.SystemException;
import com.biz.support.web.BuildRequestHandler;
import com.biz.support.web.assist.PostInitialization;

import javax.servlet.http.HttpServletRequest;

/**
 * 后置初始化处理器,如果请求对象实现了PostInitialization接口,调用该对象的postInitializing()方法
 *
 * @author yanweijin
 * @usage
 * @reviewer
 * @since 2016年8月16日
 */
public class PostInitializationHandler implements BuildRequestHandler {

    @Override
    public void handle(HttpServletRequest request, Object requestVo) throws SystemException {
        if (requestVo instanceof PostInitialization) {
            ((PostInitialization) requestVo).postInitializing();
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }

}
