package com.biz.gbck.vo.oms.event;

/**
 * 与中台库存对接的相关事件都要继承该基类
 *
 * @author zhangcheng
 * @date 2017/1/10
 * @reviewer
 * @see
 */
public abstract class OmsStockEvent extends OmsEvent {

    private static final long serialVersionUID = 6794932654644113369L;

    public OmsStockEvent(Object source) {
        super(source);
    }
}
