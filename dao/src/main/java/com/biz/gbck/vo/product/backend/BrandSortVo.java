package com.biz.gbck.vo.product.backend;

import java.io.Serializable;
import java.util.List;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2016/12/27
 */
public class BrandSortVo implements Serializable {

    private Long categoryId;

    private List<BrandSortListVo> brandSortListVos;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<BrandSortListVo> getBrandSortListVos() {
        return brandSortListVos;
    }

    public void setBrandSortListVos(List<BrandSortListVo> brandSortListVos) {
        this.brandSortListVos = brandSortListVos;
    }
}
