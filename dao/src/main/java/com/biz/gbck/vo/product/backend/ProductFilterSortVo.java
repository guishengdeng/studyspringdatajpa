package com.biz.gbck.vo.product.backend;

import java.io.Serializable;
import java.util.List;

/**
 * 分类条件排序VO
 *
 * @author wangyumin
 * @date 2017年1月7日
 */
public class ProductFilterSortVo implements Serializable {

    private static final long serialVersionUID = 1317574521375025818L;

    private Long categoryId;

    private List<ProductFilterSortListVo> productFilterSortListVos;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<ProductFilterSortListVo> getProductFilterSortListVos() {
        return productFilterSortListVos;
    }

    public void setProductFilterSortListVos(List<ProductFilterSortListVo> productFilterSortListVos) {
        this.productFilterSortListVos = productFilterSortListVos;
    }

}
