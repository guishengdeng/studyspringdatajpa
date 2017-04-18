package com.biz.gbck.vo.product.event;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 分类删除 Vo
 *
 * @author david-liu
 * @date 2016年12月22日
 * @reviewer
 * @see
 */
public class DeleteCategoryEventVo implements Serializable {

    private static final long serialVersionUID = -3198086394728925723L;

    private Long categoryId;

    public DeleteCategoryEventVo(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
