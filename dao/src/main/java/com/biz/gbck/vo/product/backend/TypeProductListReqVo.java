package com.biz.gbck.vo.product.backend;

import java.io.Serializable;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2017/3/15
 */
public class TypeProductListReqVo implements Serializable {

    private static final long serialVersionUID = -340110415615701389L;

    private Long categoryId;

    private SearchPageVo searchPageVo;

    public TypeProductListReqVo() {
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public SearchPageVo getSearchPageVo() {
        return searchPageVo;
    }

    public void setSearchPageVo(SearchPageVo searchPageVo) {
        this.searchPageVo = searchPageVo;
    }
}
