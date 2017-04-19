package com.biz.gbck.vo.product.backend;

import java.io.Serializable;
import java.util.List;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2017/3/21
 */
public class UpdateTypeBProductVo implements Serializable {

    private Long productId;

    private String productCode;

    private Long brandId;

    private String breif;

    private Long categoryId;

    private String i18nCode;

    private String image;

    private List<String> productImages;

    private List<String> introImages;

    private String productName;

    private String seoDescription;

    private String seoKeywords;

    private String seoTitle;

    private String subTitle;

    private List<Long> extendids;

    public UpdateTypeBProductVo() {
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBreif() {
        return breif;
    }

    public void setBreif(String breif) {
        this.breif = breif;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getI18nCode() {
        return i18nCode;
    }

    public void setI18nCode(String i18nCode) {
        this.i18nCode = i18nCode;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<String> productImages) {
        this.productImages = productImages;
    }

    public List<String> getIntroImages() {
        return introImages;
    }

    public void setIntroImages(List<String> introImages) {
        this.introImages = introImages;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSeoDescription() {
        return seoDescription;
    }

    public void setSeoDescription(String seoDescription) {
        this.seoDescription = seoDescription;
    }

    public String getSeoKeywords() {
        return seoKeywords;
    }

    public void setSeoKeywords(String seoKeywords) {
        this.seoKeywords = seoKeywords;
    }

    public String getSeoTitle() {
        return seoTitle;
    }

    public void setSeoTitle(String seoTitle) {
        this.seoTitle = seoTitle;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public List<Long> getExtendids() {
        return extendids;
    }

    public void setExtendids(List<Long> extendids) {
        this.extendids = extendids;
    }

    @Override
    public String toString() {
        return "UpdateTypeBProductVo{" +
                "productId=" + productId +
                ", productCode='" + productCode + '\'' +
                ", brandId=" + brandId +
                ", breif='" + breif + '\'' +
                ", categoryId=" + categoryId +
                ", i18nCode='" + i18nCode + '\'' +
                ", image='" + image + '\'' +
                ", productImages=" + productImages +
                ", introImages=" + introImages +
                ", productName='" + productName + '\'' +
                ", seoDescription='" + seoDescription + '\'' +
                ", seoKeywords='" + seoKeywords + '\'' +
                ", seoTitle='" + seoTitle + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", extendids=" + extendids +
                '}';
    }
}
