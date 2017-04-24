package com.biz.gbck.vo.product.backend;

import java.io.Serializable;
import java.util.List;

/**
 * 商家商品详情 Vo(商家后台用)
 *
 * @author david-liu
 * @date 2016年12月25日
 * @reviewer
 * @see
 */
public class VendorProductDetailVo implements Serializable {

    private static final long serialVersionUID = -3662437575317870383L;

    /**
     * 商品 ID
     */
    private String id;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商家 ID
     */
    private String vendorId;

    /**
     * 商家名称
     */
    private String vendorName;

    /**
     * 商品子标题
     */
    private String subTitle;

    /**
     * 商品国际码
     */
    private String i18nCode;

    /**
     * 商品简介
     */
    private String breif;

    /**
     * 商品品牌 ID
     */
    private String brandId;

    /**
     * 商品分类 ID
     */
    private String categoryId;

    /**
     * 商品扩展属性
     */
    private List<ExtendVo> properties;

    /**
     * 商品介绍富文本信息
     */
    private List<String> introImages;

    /**
     * 商品 Logo
     */
    private String logo;

    /**
     * 商品主图(轮播图)
     */
    private List<String> images;

    /**
     * 商品是否在审核当中
     */
    private Boolean inAudit;

    /**
     * SEO 标题
     */
    private String seoTitle;

    /**
     * SEO 关键字信息
     */
    private String seoKeywords;

    /**
     * SEO 描述信息
     */
    private String seoDescription;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getI18nCode() {
        return i18nCode;
    }

    public void setI18nCode(String i18nCode) {
        this.i18nCode = i18nCode;
    }

    public String getBreif() {
        return breif;
    }

    public void setBreif(String breif) {
        this.breif = breif;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public List<ExtendVo> getProperties() {
        return properties;
    }

    public void setProperties(List<ExtendVo> properties) {
        this.properties = properties;
    }

    public List<String> getIntroImages() {
        return introImages;
    }

    public void setIntroImages(List<String> introImages) {
        this.introImages = introImages;
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

    public Boolean getInAudit() {
        return inAudit;
    }

    public void setInAudit(Boolean inAudit) {
        this.inAudit = inAudit;
    }

    public String getSeoTitle() {
        return seoTitle;
    }

    public void setSeoTitle(String seoTitle) {
        this.seoTitle = seoTitle;
    }

    public String getSeoKeywords() {
        return seoKeywords;
    }

    public void setSeoKeywords(String seoKeywords) {
        this.seoKeywords = seoKeywords;
    }

    public String getSeoDescription() {
        return seoDescription;
    }

    public void setSeoDescription(String seoDescription) {
        this.seoDescription = seoDescription;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }
}
