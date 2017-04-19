package com.biz.gbck.vo.product.frontend;

import com.biz.gbck.vo.product.backend.ProductSalesVo;
import com.google.common.collect.Lists;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2017/2/28
 */
public class CategoryProductSalesTopVo implements Serializable {

    private static final long serialVersionUID = -7139214744513360946L;

    private Long categoryId;

    private List<ProductSalesVo> productSalesVos;

    public CategoryProductSalesTopVo(Long categoryId, List<ProductSalesVo> productSalesVos) {
        this.categoryId = categoryId;
        this.productSalesVos = productSalesVos;
    }

    public CategoryProductSalesTopVo() {
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<ProductSalesVo> getProductSalesVos() {
        return productSalesVos;
    }

    public void setProductSalesVos(List<ProductSalesVo> productSalesVos) {
        this.productSalesVos = productSalesVos;
    }

    public List<ProductSalesVo> getTopN(int n) {
        Collections.sort(this.getProductSalesVos());
        if (this.getProductSalesVos() == null) {
            return Lists.newArrayList();
        }
        if (this.getProductSalesVos().size() <= n) {
            return this.getProductSalesVos();
        } else if (this.getProductSalesVos().size() > n) {
            return this.getProductSalesVos().subList(0, n - 1);
        }
        return Lists.newArrayList();
    }

}
