package com.biz.gbck.vo.oms.event;

/**
 * 与中台对接的会员相关事件都要继承该类
 * @author zhangcheng
 * @date 2017/1/10
 * @reviewer
 * @see
 */
public abstract class OmsMemberEvent extends OmsEvent{

    private static final long serialVersionUID = -3821019765959426349L;

    public OmsMemberEvent(Object source) {
        super(source);
    }
}
