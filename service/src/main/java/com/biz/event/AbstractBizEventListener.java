package com.biz.event;

import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;

/**
 * 预先抽象的事件监听器基类
 * 
 * @author yanweijin
 * @date 2016年7月24日
 * @reviewer
 * @param <E> 监听的事件类型
 */
public abstract class AbstractBizEventListener<E extends BizEvent> implements ApplicationListener<E> {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Override
//	@Transactional
	public void onApplicationEvent(E event) {
		handleEvent(event);
	}

	@PostConstruct
	void init() {
		logger.debug("初始化事件监听器:{}", this.getClass().getName());
	}

	protected abstract void handleEvent(E event);

}
