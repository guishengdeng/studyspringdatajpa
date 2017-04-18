package com.biz.gbck.vo.product;

import com.biz.core.page.PageResult;
import com.biz.gbck.vo.product.frontend.ProductListItemVo;
import com.biz.gbck.vo.search.ProductFieldItemVo;
import com.biz.gbck.vo.search.ProductFieldVo;
import com.google.common.collect.Lists;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 商品分页结果
 *
 * @author david-liu
 * @date 2017年02月17日
 * @reviewer
 */
public class ProductPageResult extends PageResult<ProductListItemVo> {
    private static final long serialVersionUID = -8926110635780584032L;

    private List<ProductFieldVo> searchFields = Lists.newArrayList();

    public List<ProductFieldVo> getSearchFields() {
        //        去掉field中的 /
        if (CollectionUtils.isNotEmpty(searchFields)) {
            for (ProductFieldVo index : searchFields) {
                if (CollectionUtils.isNotEmpty(index.getItems())) {
                    for (ProductFieldItemVo productFieldItemVo : index.getItems()) {
                        if (productFieldItemVo.getValue().contains("/")) {
                            productFieldItemVo.setValue(productFieldItemVo.getValue().split("/")[1]);
                        }
                    }
                }
            }
        }
        return searchFields;
    }

    public void setSearchFields(List<ProductFieldVo> searchFields) {
        this.searchFields = searchFields;
    }

    public ProductPageResult(List<ProductFieldVo> searchFields) {
        this.searchFields = searchFields;
    }

    public ProductPageResult(int currentPage, int size, int total, List<ProductListItemVo> result, List<ProductFieldVo> searchFields) {
        super(currentPage, size, total, result);
        this.searchFields = searchFields;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
