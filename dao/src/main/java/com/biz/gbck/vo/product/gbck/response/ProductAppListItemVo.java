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
     * 商品ID
     */
    private String id;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 商品Logo图片
     */
    private String logo;

    /**
     * 商品标签
     */
    private List<String> tags;

    /**
     * 商品角标图片
     */
    private String apartTagImage;

    /**
     * 是否支持特价
     */
    private Boolean isSupportSpecialOffer;

    /**
     * 是否支持优惠券
     */
    private Boolean isSupportVoucher;

    /**
     * 商品支持的促销
     */
    private List<String> supportPromotions;

    /**
     * 简单特价
     */
    private Integer specialOfferPrice;


    /**
     * 销售价
     */
    private Integer salePrice;

    /**
     * 建议零售价
     */
    private Integer suggestSalePrice;

    /**
     * 库存
     */
    private Integer stock;

    public ProductAppListItemVo() {
    }

    public ProductAppListItemVo(ProductAppListItemVo itemVo) {
        this.setId(itemVo.getId());
        this.setProductName(itemVo.getProductName());
        this.setProductCode(itemVo.getProductCode());
        this.setLogo(itemVo.getLogo());
        this.setTags(itemVo.getTags());
        this.setApartTagImage(itemVo.getApartTagImage());
        this.setSupportSpecialOffer(itemVo.getSupportSpecialOffer());
        this.setSupportVoucher(itemVo.getSupportVoucher());
        this.setSupportPromotions(itemVo.getSupportPromotions());
        this.setSpecialOfferPrice(itemVo.getSpecialOfferPrice());
        this.setSalePrice(itemVo.getSalePrice());
        this.setSuggestSalePrice(itemVo.getSuggestSalePrice());
        this.setStock(itemVo.getStock());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getApartTagImage() {
        return apartTagImage;
    }

    public void setApartTagImage(String apartTagImage) {
        this.apartTagImage = apartTagImage;
    }

    public Boolean getSupportSpecialOffer() {
        return isSupportSpecialOffer;
    }

    public void setSupportSpecialOffer(Boolean supportSpecialOffer) {
        isSupportSpecialOffer = supportSpecialOffer;
    }

    public Boolean getSupportVoucher() {
        return isSupportVoucher;
    }

    public void setSupportVoucher(Boolean supportVoucher) {
        isSupportVoucher = supportVoucher;
    }

    public List<String> getSupportPromotions() {
        return supportPromotions;
    }

    public void setSupportPromotions(List<String> supportPromotions) {
        this.supportPromotions = supportPromotions;
    }

    public Integer getSpecialOfferPrice() {
        return specialOfferPrice;
    }

    public void setSpecialOfferPrice(Integer specialOfferPrice) {
        this.specialOfferPrice = specialOfferPrice;
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
