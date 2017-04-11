package com.biz.event;

import org.springframework.context.ApplicationEvent;

public class BizEvent extends ApplicationEvent {

    private static final long serialVersionUID = 8809937598352484211L;

    public BizEvent(Object source) {
        super(source);
    }

}
