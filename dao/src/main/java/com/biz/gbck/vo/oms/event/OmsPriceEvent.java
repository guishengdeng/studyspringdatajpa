package com.biz.gbck.vo.oms.event;

/**
 * 与中台对接价格相关的时间都要继承该类
 *
 * @author zhangcheng
 * @date 2017/1/10
 * @reviewer
 * @see
 */
public abstract class OmsPriceEvent extends OmsEvent {

    private static final long serialVersionUID = -3186581244671891700L;

    public OmsPriceEvent(Object source) {
        super(source);
    }
}
