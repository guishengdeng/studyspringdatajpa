package com.biz.gbck.vo.product.event;


import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 分类创建事件
 *
 * @author david-liu
 * @date 2016年12月22日
 * @reviewer
 * @see
 */
public class CreateCategoryEvent extends ProductEvent {

    private static final long serialVersionUID = 3517807228645793885L;

    private CreateCategoryEventVo vo;

    public CreateCategoryEvent(Object source, CreateCategoryEventVo vo) {
        super(source);
        this.vo = vo;
    }

    public CreateCategoryEventVo getVo() {
        return vo;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
