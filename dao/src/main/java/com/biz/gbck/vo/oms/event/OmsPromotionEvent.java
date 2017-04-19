package com.biz.gbck.vo.oms.event;

/**
 * 与中台对接促销相关的事件都要继承该类
 *
 * @author zhangcheng
 * @date 2017/1/10
 * @reviewer
 * @see
 */
public abstract class OmsPromotionEvent extends OmsEvent {

    private static final long serialVersionUID = -7075603297268280954L;

    public OmsPromotionEvent(Object source) {
        super(source);
    }
}
