package com.biz.gbck.vo.product.backend;

import java.io.Serializable;
import java.util.List;

public class ExtendPropertySortVo implements Serializable {

    private static final long serialVersionUID = 951039110825937317L;

    private Long productExtendId;

    private List<ExtendPropertySortListVo> extendPropertySortListVos;

    public Long getProductExtendId() {
        return productExtendId;
    }

    public void setProductExtendId(Long productExtendId) {
        this.productExtendId = productExtendId;
    }

    public List<ExtendPropertySortListVo> getExtendPropertySortListVos() {
        return extendPropertySortListVos;
    }

    public void setExtendPropertySortListVos(List<ExtendPropertySortListVo> extendPropertySortListVos) {
        this.extendPropertySortListVos = extendPropertySortListVos;
    }

}
