package com.biz.gbck.vo.search;

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
     * 商品 ID
     */
    private List<T> items = Lists.newArrayList();

    /**
     * 商品筛选字段
     */
    private List<ProductFieldVo> filters = Lists.newArrayList();

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<ProductFieldVo> getFilters() {
        return filters;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public void setFilters(List<ProductFieldVo> filters) {
        this.filters = filters;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "ProductSearchResultVo{" +
                "totalCount=" + totalCount +
                ", count=" + count +
                ", filters=" + filters +
                '}';
    }
}