package com.biz.gbck.vo.product.gbck.response;

import com.biz.gbck.vo.product.gbck.ProductPropertyVo;
import com.biz.gbck.vo.product.gbck.SalePromotionVO;
import java.io.Serializable;
import java.util.List;

/**
 * App商品详情返回Vo
 *
 * Created by david-liu on 2017/04/28 12:07.
 */
public class ProductAppDetailRespVO implements Serializable {
    private static final long serialVersionUID = 1967168727397108097L;

    /**
     * 商品ID
     */
    private String id;

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
     * 最大购买数量(限购用)
     */
    private Integer maxQuantity;

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

    /**
     * 是否支持简单特价
     */
    private Boolean isSupportSpecialOffer;

    /**
     * 特价
     */
    private Integer specialOfferPrice;

    /**
     * 上下架状态
     */
    private Integer status;

    /**
     * 促销详情
     */
    private List<SalePromotionVO> salePromotionDetail;

    /**
     * 商品规格
     */
    private String standard;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(Integer maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public Boolean getSupportSpecialOffer() {
        return isSupportSpecialOffer;
    }

    public void setSupportSpecialOffer(Boolean supportSpecialOffer) {
        isSupportSpecialOffer = supportSpecialOffer;
    }

    public Integer getSpecialOfferPrice() {
        return specialOfferPrice;
    }

    public void setSpecialOfferPrice(Integer specialOfferPrice) {
        this.specialOfferPrice = specialOfferPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<SalePromotionVO> getSalePromotionDetail() {
        return salePromotionDetail;
    }

    public void setSalePromotionDetail(List<SalePromotionVO> salePromotionDetail) {
        this.salePromotionDetail = salePromotionDetail;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }
}
