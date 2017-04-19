package com.biz.gbck.vo.product.backend;

import com.biz.gbck.enums.product.ProductAuditStatusEnum;
import java.io.Serializable;
import java.util.List;

/**
 * 商家商品审核记录详情 Vo
 *
 * @author david-liu
 * @date 2016年12月26日
 * @reviewer
 * @see
 */
public class VendorProductAuditDetailVo implements Serializable {

    private static final long serialVersionUID = -2545409754794230402L;


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
     * 商品审核状态
     */
    private ProductAuditStatusEnum auditStatus;

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

    /**
     * 审核信息
     */
    private String auditMessage;

    /**
     * 重量（克）
     */
    private Integer weight;


    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public ProductAuditStatusEnum getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(ProductAuditStatusEnum auditStatus) {
        this.auditStatus = auditStatus;
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

    public String getAuditMessage() {
        return auditMessage;
    }

    public void setAuditMessage(String auditMessage) {
        this.auditMessage = auditMessage;
    }
}
