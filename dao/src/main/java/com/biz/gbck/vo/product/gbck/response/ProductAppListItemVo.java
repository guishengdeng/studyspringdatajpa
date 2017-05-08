package com.biz.gbck.vo.product.gbck.response;

import java.io.Serializable;
import java.util.List;

/**
 * 商品列表项Vo
 *
 * Created by david-liu on 2017/04/28 09:20.
 */
public class ProductAppListItemVo implements Serializable {
    private static final long serialVersionUID = 6806976491980622046L;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 分类
     */
    private ProductFieldVo<Long> category;

    /**
     * 品牌
     */
    private ProductFieldVo<Long> brand;

    /**
     * 商品促销信息
     */
    private List<ProductFieldVo<Long>> promotions;

    /**
     * 商品Logo图片
     */
    private String logo;

    /**
     * 销售价
     */
    private Integer salePrice;

    /**
     * 建议零售价
     */
    private Integer suggestSalePrice;

    /**
     * 商品标签
     */
    private List<String> tags;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public ProductFieldVo<Long> getCategory() {
        return category;
    }

    public void setCategory(ProductFieldVo<Long> category) {
        this.category = category;
    }

    public ProductFieldVo<Long> getBrand() {
        return brand;
    }

    public void setBrand(ProductFieldVo<Long> brand) {
        this.brand = brand;
    }

    public List<ProductFieldVo<Long>> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<ProductFieldVo<Long>> promotions) {
        this.promotions = promotions;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Integer salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getSuggestSalePrice() {
        return suggestSalePrice;
    }

    public void setSuggestSalePrice(Integer suggestSalePrice) {
        this.suggestSalePrice = suggestSalePrice;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
