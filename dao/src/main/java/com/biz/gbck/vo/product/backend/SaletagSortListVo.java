package com.biz.gbck.vo.product.backend;

import com.google.common.collect.Lists;
import java.io.Serializable;
import java.util.List;

/**
 * @author 江南
 * @usage 销售标签排序Vo
 * @reviewer
 * @since 2016/12/27
 */
public class SaletagSortListVo implements Serializable {

    private static final long serialVersionUID = 5534697066535839051L;

    /**
     * 分类ID
     */
    private Long categoryId;

    /**
     * 当前分类下销售标签的排序
     */
    private List<SaletagSortList> saletagSortLists = Lists.newArrayList();

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<SaletagSortList> getSaletagSortLists() {
        return saletagSortLists;
    }

    public void setSaletagSortLists(List<SaletagSortList> saletagSortLists) {
        this.saletagSortLists = saletagSortLists;
    }
}
