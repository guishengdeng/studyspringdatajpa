package com.biz.gbck.vo.product.gbck.request;

import com.biz.gbck.vo.product.gbck.response.ProductFieldVo;
import java.io.Serializable;
import java.util.List;

/**
 * App商品列表页请求Vo
 *
 * Created by david-liu on 2017/04/28 09:39.
 */
public class ProductAppListReqVo implements Serializable {
    private static final long serialVersionUID = -8016336127108731901L;

    /**
     * 价格组ID
     */
    private Long priceGroupId;

    /**
     * 分类ID
     */
    private Long categoryId;

    /**
     * 搜索过滤字段
     */
    private List<ProductFieldVo<String>> productSearchFields;

    public Long getPriceGroupId() {
        return priceGroupId;
    }

    public void setPriceGroupId(Long priceGroupId) {
        this.priceGroupId = priceGroupId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<ProductFieldVo<String>> getProductSearchFields() {
        return productSearchFields;
    }

    public void setProductSearchFields(List<ProductFieldVo<String>> productSearchFields) {
        this.productSearchFields = productSearchFields;
    }
}
