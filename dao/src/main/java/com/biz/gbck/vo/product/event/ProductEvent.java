package com.biz.gbck.vo.product.event;


import com.biz.core.event.BizEvent;

/**
 * 商品事件基类
 *
 * @author david-liu
 * @date 2016年12月16日
 * @reviewer
 * @see
 */
public abstract class ProductEvent extends BizEvent {

    private static final long serialVersionUID = -8172429489107074175L;

    public ProductEvent(Object source) {
        super(source);
    }
}
