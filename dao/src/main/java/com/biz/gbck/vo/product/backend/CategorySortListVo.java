package com.biz.gbck.vo.product.backend;

import java.io.Serializable;

/**
 * @author wangyumin
 * @date 2017年1月4日
 */
public class CategorySortListVo implements Serializable {

    private static final long serialVersionUID = 5797690799456176570L;

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
