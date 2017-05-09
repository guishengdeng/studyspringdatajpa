package com.biz.gbck.vo.product.promotion.soa.singleProduct.request;

import java.io.Serializable;

/**
 * 获取某商品的单品促销请求Vo
 *
 * Created by david-liu on 2017/04/27 11:21.
 */
public class ProductSingleProductPromotionReqVo implements Serializable {
    private static final long serialVersionUID = 3002577760049032169L;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 分类Id
     */
    private Long categoryId;

    /**
     * 价格组ID
     */
    private Long priceGroupId;

    /**
     * 品牌ID
     */
    private Long brandId;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getPriceGroupId() {
        return priceGroupId;
    }

    public void setPriceGroupId(Long priceGroupId) {
        this.priceGroupId = priceGroupId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }
}
