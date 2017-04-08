package com.depotnextdoor.support.web.assist;

/**
 * 实现该接口的requestVo将在BaseController处理完数据后调用postInitializing()方法
 * @author yanweijin
 * @since 2016年8月16日
 * @usage 
 * @reviewer
 */
public interface PostInitialization {

	void postInitializing();
}
