package com.biz.gbck.vo.product.event;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 分类删除事件
 *
 * @author david-liu
 * @date 2016年12月22日
 * @reviewer
 * @see
 */
public class DeleteCategoryEvent extends ProductEvent {

    private static final long serialVersionUID = -8957967747931943634L;

    private DeleteCategoryEventVo vo;

    public DeleteCategoryEvent(Object source, DeleteCategoryEventVo vo) {
        super(source);
        this.vo = vo;
    }

    public DeleteCategoryEventVo getVo() {
        return vo;
    }

    public void setVo(DeleteCategoryEventVo vo) {
        this.vo = vo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
