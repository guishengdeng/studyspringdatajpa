package com.biz.gbck.vo.product.backend;

import java.io.Serializable;
import java.util.List;

public class PropertySortVo implements Serializable {

    private Long categoryId;

    private List<PropertySortListVo> propertySortListVos;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<PropertySortListVo> getPropertySortListVos() {
        return propertySortListVos;
    }

    public void setPropertySortListVos(List<PropertySortListVo> propertySortListVos) {
        this.propertySortListVos = propertySortListVos;
    }

}
