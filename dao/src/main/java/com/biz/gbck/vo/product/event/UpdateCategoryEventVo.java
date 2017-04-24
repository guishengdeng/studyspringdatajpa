package com.biz.gbck.vo.product.event;

import com.biz.gbck.vo.product.backend.UpdateCategoryVo;
import java.io.Serializable;

/**
 * 分类编辑事件 Vo
 *
 * @author david-liu
 * @date 2016年12月22日
 * @reviewer
 * @see
 */
public class UpdateCategoryEventVo implements Serializable {

    private static final long serialVersionUID = 5966037040038930314L;

    private UpdateCategoryVo vo;

    public UpdateCategoryEventVo(UpdateCategoryVo vo) {
        this.vo = vo;
    }

    public UpdateCategoryVo getVo() {
        return vo;
    }

    public void setVo(UpdateCategoryVo vo) {
        this.vo = vo;
    }

    @Override
    public String toString() {
        return "UpdateCategoryEventVo{" +
                "vo=" + vo +
                '}';
    }
}
