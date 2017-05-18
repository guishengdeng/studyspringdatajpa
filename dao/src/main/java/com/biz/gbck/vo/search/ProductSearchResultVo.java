package com.biz.gbck.vo.search;

import com.biz.gbck.vo.product.gbck.response.ProductBrandFilterVO;
import com.biz.gbck.vo.product.gbck.response.ProductFilterVO;
import com.google.common.collect.Lists;
import java.io.Serializable;
import java.util.List;

/**
 * 商品搜索结果 Vo
 *
 * @author david-liu
 * @date 2017年01月09日
 * @reviewer
 * @see
 */
public class ProductSearchResultVo<T> implements Serializable {

    private static final long serialVersionUID = 5739540086289792657L;

    /**
     * 搜索结果总共条数
     */
    private Integer totalCount;

    /**
     * 搜索结果数量
     */
    private Integer count;

    /**
     * 商品
     */
    private List<T> items = Lists.newArrayList();

    private List<ProductFilterVO> filters = Lists.newArrayList();

    private ProductBrandFilterVO brands;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<ProductFilterVO> getFilters() {
        return filters;
    }

    public void setFilters(List<ProductFilterVO> filters) {
        this.filters = filters;
    }

    public ProductBrandFilterVO getBrands() {
        return brands;
    }

    public void setBrands(ProductBrandFilterVO brands) {
        this.brands = brands;
    }
}