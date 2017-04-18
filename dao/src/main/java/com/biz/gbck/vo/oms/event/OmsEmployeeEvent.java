package com.biz.gbck.vo.oms.event;

/**
 * 与中台对接的员工相关事件都要继承该类
 * @author zhangcheng
 * @date 2017/1/10
 * @reviewer
 * @see
 */
public abstract class OmsEmployeeEvent extends OmsEvent{

    private static final long serialVersionUID = 6751879922404247679L;

    public OmsEmployeeEvent(Object source) {
        super(source);
    }
}
