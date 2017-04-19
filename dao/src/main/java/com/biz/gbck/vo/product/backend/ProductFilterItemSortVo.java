package com.biz.gbck.vo.product.backend;

import java.io.Serializable;
import java.util.List;

public class ProductFilterItemSortVo implements Serializable {

    private static final long serialVersionUID = 888359596429675394L;

    private Long productFilterId;

    private List<ProductFilterItemSortListVo> productFilterItemSortListVos;

    public Long getProductFilterId() {
        return productFilterId;
    }

    public void setProductFilterId(Long productFilterId) {
        this.productFilterId = productFilterId;
    }

    public List<ProductFilterItemSortListVo> getProductFilterItemSortListVos() {
        return productFilterItemSortListVos;
    }

    public void setProductFilterItemSortListVos(List<ProductFilterItemSortListVo> productFilterItemSortListVos) {
        this.productFilterItemSortListVos = productFilterItemSortListVos;
    }

}
