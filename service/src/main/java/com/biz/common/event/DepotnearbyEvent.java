package com.biz.common.event;

import org.springframework.context.ApplicationEvent;


/**
 * 轻量级事件
 */
public abstract class DepotnearbyEvent extends ApplicationEvent {

    public DepotnearbyEvent(Object source) {
        super(source);
    }

}
