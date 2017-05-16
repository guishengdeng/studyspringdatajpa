package com.biz.gbck.vo.product.gbck.response;

import com.biz.gbck.vo.product.backend.ProductFilterItemListVo;
import java.io.Serializable;
import java.util.List;

/**
 * 商品列表返回VO
 *
 * Created by david-liu on 2017/05/10 16:03.
 */
public class ProductAppListRespVO implements Serializable {
    private static final long serialVersionUID = 3509489993173257574L;

    private Integer currentPage;

    private Integer size;

    private List<ProductAppListItemVo> result;

    private List<ProductFilterItemListVo> filters;

    private List<ProductBrandFilterVO> brands;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<ProductAppListItemVo> getResult() {
        return result;
    }

    public void setResult(List<ProductAppListItemVo> result) {
        this.result = result;
    }

    public List<ProductFilterItemListVo> getFilters() {
        return filters;
    }

    public void setFilters(List<ProductFilterItemListVo> filters) {
        this.filters = filters;
    }

    public List<ProductBrandFilterVO> getBrands() {
        return brands;
    }

    public void setBrands(List<ProductBrandFilterVO> brands) {
        this.brands = brands;
    }
}
