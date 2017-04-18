package com.biz.gbck.vo.oms.event;

/**
 * 与中台对接的商品相关事件都要继承该类
 * @author zhangcheng
 * @date 2017/1/10
 * @reviewer
 * @see
 */
public abstract class OmsProductEvent extends OmsEvent{

    private static final long serialVersionUID = 2651931655631495186L;

    public OmsProductEvent(Object source) {
        super(source);
    }
}
