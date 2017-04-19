package com.biz.gbck.vo.product.backend;

import java.io.Serializable;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2016/12/27
 */
public class SaletagSortList implements Serializable {

    private static final long serialVersionUID = -5615288775995497932L;

    /**
     * 销售标签ID
     */
    private Long id;

    /**
     * 销售标签的排序
     */
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

