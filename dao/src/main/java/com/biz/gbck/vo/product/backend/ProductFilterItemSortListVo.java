package com.biz.gbck.vo.product.backend;

import java.io.Serializable;

public class ProductFilterItemSortListVo implements Serializable {

    private static final long serialVersionUID = 3463693583231330597L;

    private Long id;

    private Integer idx;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

}
