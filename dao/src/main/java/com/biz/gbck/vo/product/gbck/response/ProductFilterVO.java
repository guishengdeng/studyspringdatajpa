package com.biz.gbck.vo.product.gbck.response;

import java.io.Serializable;
import java.util.List;

/**
 * 商品过滤条件VO
 *
 * Created by david-liu on 2017/05/10 16:07.
 */
public class ProductFilterVO implements Serializable {
    private static final long serialVersionUID = -3294429441473791453L;

    private String id;

    private String label;

    private Boolean hasMore;

    private Boolean usePrefix;

    private List<ProductFilterItemVO> filterItems;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }

    public Boolean getUsePrefix() {
        return usePrefix;
    }

    public void setUsePrefix(Boolean usePrefix) {
        this.usePrefix = usePrefix;
    }

    public List<ProductFilterItemVO> getFilterItems() {
        return filterItems;
    }

    public void setFilterItems(List<ProductFilterItemVO> filterItems) {
        this.filterItems = filterItems;
    }
}
