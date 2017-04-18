package com.biz.gbck.vo.oms.event;

/**
 * 与中台对接的省仓相关事件都要继承该类
 * @author zhangcheng
 * @date 2017/1/10
 * @reviewer
 * @see
 */
public abstract class OmsWarehouseEvent extends OmsEvent{

    private static final long serialVersionUID = -358432233565311355L;

    public OmsWarehouseEvent(Object source) {
        super(source);
    }
}
