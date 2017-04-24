package com.biz.gbck.vo.product.backend;

import com.google.common.base.Joiner;
import java.io.Serializable;
import java.util.List;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2017/3/16
 */
public class PlatformTypeBProductDetailVo implements Serializable {


    private static final long serialVersionUID = -679870661908018013L;

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
    private String productName;

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
    private List<ExtendVo> extendVos;

    /**
     * 商品介绍信息
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

    public PlatformTypeBProductDetailVo() {
    }

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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
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

    public List<ExtendVo> getExtendVos() {
        return extendVos;
    }

    public void setExtendVos(List<ExtendVo> extendVos) {
        this.extendVos = extendVos;
    }

    public String getIntroImages() {
        if (this.introImages != null) {
            return Joiner.on(",").join(this.introImages);
        }
        return null;
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

    public String getImages() {
        if (this.images != null) {
            return Joiner.on(",").join(this.images);
        }
        return null;
    }

    public void setImages(List<String> images) {
        this.images = images;
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
}
