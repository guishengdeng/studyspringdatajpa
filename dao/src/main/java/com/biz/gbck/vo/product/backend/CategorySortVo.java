package com.biz.gbck.vo.product.backend;

import java.io.Serializable;
import java.util.List;

public class CategorySortVo implements Serializable {

    private static final long serialVersionUID = -2657856116554368228L;

    private Long categoryId;

    private List<CategorySortListVo> categorySortListVos;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<CategorySortListVo> getCategorySortListVos() {
        return categorySortListVos;
    }

    public void setCategorySortListVos(List<CategorySortListVo> categorySortListVos) {
        this.categorySortListVos = categorySortListVos;
    }


}
