package com.biz.gbck.vo.product.event;

/**
 * 分类编辑事件
 *
 * @author david-liu
 * @date 2016年12月22日
 * @reviewer
 * @see
 */
public class UpdateCategoryEvent extends ProductEvent {

    private static final long serialVersionUID = 8814857085740456482L;

    private UpdateCategoryEventVo vo;

    public UpdateCategoryEvent(Object source, UpdateCategoryEventVo vo) {
        super(source);
        this.vo = vo;
    }

    public UpdateCategoryEventVo getVo() {
        return vo;
    }

    public void setVo(UpdateCategoryEventVo vo) {
        this.vo = vo;
    }

    @Override
    public String toString() {
        return "UpdateCategoryEvent{" +
                "vo=" + vo +
                '}';
    }
}
