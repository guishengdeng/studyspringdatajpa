package com.biz.soa.org.event;


import com.biz.core.event.BizEvent;
import org.springframework.context.ApplicationEvent;

/**
 * 商户 事件
 */

public class ShopEvent extends BizEvent {

    protected ShopEvent(Object source) {
        super(source);
    }

}
