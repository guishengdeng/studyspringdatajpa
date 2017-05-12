package com.biz.gbck.vo.product.backend;

import java.io.Serializable;

/**
 * 商品过滤条件ReqVO
 *
 * Created by david-liu on 2017/05/07 23:51.
 */
public class ProductFilterListReqVO implements Serializable {
    private static final long serialVersionUID = -705992692657041404L;

    private SearchPageVo searchPageVo;

    private Long categoryId;

    public ProductFilterListReqVO(SearchPageVo searchPageVo, Long categoryId) {
        this.searchPageVo = searchPageVo;
        this.categoryId = categoryId;
    }

    public SearchPageVo getSearchPageVo() {
        return searchPageVo;
    }

    public void setSearchPageVo(SearchPageVo searchPageVo) {
        this.searchPageVo = searchPageVo;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
