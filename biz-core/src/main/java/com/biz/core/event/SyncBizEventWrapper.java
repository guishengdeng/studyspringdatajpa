package com.biz.core.event;

import org.springframework.context.ApplicationEvent;

final class SyncBizEventWrapper extends ApplicationEvent implements BizEventWrapper {
    private static final long serialVersionUID = -5170108322289431707L;

    private final BizEvent event;

    /**
     * @author yanweijin
     * @date 2016年7月24日
     */
    public SyncBizEventWrapper(BizEvent event) {
        super(event.getSource());
        this.event = event;
    }

    public BizEvent getEvent() {
        return event;
    }
}
