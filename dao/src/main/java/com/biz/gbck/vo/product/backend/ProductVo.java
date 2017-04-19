package com.biz.gbck.vo.product.backend;

import java.io.Serializable;
import java.util.List;

/**
 * 后台商品 Vo
 *
 * @author david-liu
 * @date 2016年12月19日
 * @reviewer
 * @see
 */
public class ProductVo implements Serializable {

    private static final long serialVersionUID = -4644597604845186321L;

    private String id;

    /**
     * 商家 ID
     */
    private Long vendorId;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品副标题
     */
    private String subTitle;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 商品国际条码
     */
    private String i18nCode;

    /**
     * 商品简介
     */
    private String breif;

    /**
     * 商品品牌 ID
     */
    private Long brandId;

    /**
     * 分类 ID
     */
    private Long categoryId;

    /**
     * 商品介绍(富文本形式)
     */
    private String rawHtml;

    /**
     * 商品扩展属性(产地, 香型, 净含量等)
     */
    private List<ExtendVo> extendVos;

    /**
     * 商品主图
     */
    private String logo;

    /**
     * 商品展示图
     */
    private List<String> images;

    /**
     * 商品销售标签集合
     */
    private List<Long> saleTagIds;

    /**
     * SEO 标题
     */
    private String seoTitle;

    /**
     * SEO 关键字
     */
    private String seoKeywords;

    /**
     * SEO 描述
     */
    private String seoDescription;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
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

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getRawHtml() {
        return rawHtml;
    }

    public void setRawHtml(String rawHtml) {
        this.rawHtml = rawHtml;
    }

    public List<ExtendVo> getExtendVos() {
        return extendVos;
    }

    public void setExtendVos(List<ExtendVo> extendVos) {
        this.extendVos = extendVos;
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

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public List<Long> getSaleTagIds() {
        return saleTagIds;
    }

    public void setSaleTagIds(List<Long> saleTagIds) {
        this.saleTagIds = saleTagIds;
    }
}
