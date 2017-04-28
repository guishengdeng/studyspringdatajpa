package com.biz.gbck.vo.product.gbck.response;

import com.biz.gbck.vo.product.gbck.ProductPropertyVo;
import java.io.Serializable;
import java.util.List;

/**
 * App商品详情返回Vo
 *
 * Created by david-liu on 2017/04/28 12:07.
 */
public class ProductAppDetailRespVo implements Serializable {
    private static final long serialVersionUID = 1967168727397108097L;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 简介
     */
    private String brief;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 售价
     */
    private Integer salePrice;

    /**
     * 建议零售价
     */
    private Integer suggestPrice;

    /**
     * 市场采购价
     */
    private Integer marketPrice;

    /**
     * 最小起售数量
     */
    private Integer minQuantity;

    /**
     * 属性列表
     */
    private List<ProductPropertyVo> properties;

    /**
     * 商品logo
     */
    private String logo;

    /**
     * 商品轮播图
     */
    private List<String> images;

    /**
     * 商品介绍图片
     */
    private List<String> introImages;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Integer salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getSuggestPrice() {
        return suggestPrice;
    }

    public void setSuggestPrice(Integer suggestPrice) {
        this.suggestPrice = suggestPrice;
    }

    public Integer getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Integer marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Integer getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(Integer minQuantity) {
        this.minQuantity = minQuantity;
    }

    public List<ProductPropertyVo> getProperties() {
        return properties;
    }

    public void setProperties(List<ProductPropertyVo> properties) {
        this.properties = properties;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getIntroImages() {
        return introImages;
    }

    public void setIntroImages(List<String> introImages) {
        this.introImages = introImages;
    }
}
