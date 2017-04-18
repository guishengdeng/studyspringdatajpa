package com.biz.gbck.vo.oms.event;

/**
 * 与中台门店对接的相关事件必须继承该基类
 * @author zhangcheng
 * @date 2017/1/10
 * @reviewer
 * @see
 */
public abstract class OmsDepotEvent extends OmsEvent{

    private static final long serialVersionUID = 3942269839812635649L;

    public OmsDepotEvent(Object source) {
        super(source);
    }
}
