package com.biz.gbck.vo.product.backend;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2017/2/13
 */
public class GeoProductReqVo implements Serializable {

    private static final long serialVersionUID = 554507157994262576L;
    private SearchPageVo searchPageVo;

    /**
     * 分类ID
     */
    private Long categoryId;

    public GeoProductReqVo(SearchPageVo searchPageVo, Long categoryId) {
        this.searchPageVo = searchPageVo;
        this.categoryId = categoryId;
    }

    public GeoProductReqVo() {
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
