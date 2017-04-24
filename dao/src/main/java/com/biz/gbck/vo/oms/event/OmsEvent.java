package com.biz.gbck.vo.oms.event;


import com.biz.core.event.BizEvent;

/**
 * 与中台对接相关的事件的上层基类
 *
 * @author zhangcheng
 * @date 2017/1/9
 * @reviewer
 * @see
 */
public abstract class OmsEvent extends BizEvent {

    private static final long serialVersionUID = -3592791163846343663L;

    public OmsEvent(Object source) {
        super(source);
    }
}
