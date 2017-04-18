package com.biz.gbck.vo.oms.event;

/**
 * 与中台对接Geo相关的时间都要继承该类
 * @author zhangcheng
 * @date 2017/1/10
 * @reviewer
 * @see
 */
public abstract class OmsGeoEvent extends OmsEvent{

    private static final long serialVersionUID = 4484956292323712450L;

    public OmsGeoEvent(Object source) {
        super(source);
    }
}
