package com.biz.core.event;

import org.springframework.context.ApplicationEvent;

/**
 * 异步事件包装,配合{@link BizEventMulticaster}使用
 *
 * @author yanweijin
 * @reviewer
 * @since 2016年7月24日
 */
final class AsyncBizEventWrapper extends ApplicationEvent implements BizEventWrapper {

    private static final long serialVersionUID = -7071108383289433708L;

    private final BizEvent event;

    /**
     * @author yanweijin
     * @date 2016年7月24日
     */
    public AsyncBizEventWrapper(BizEvent event) {
        super(event.getSource());
        this.event = event;
    }

    public BizEvent getEvent() {
        return event;
    }
}
