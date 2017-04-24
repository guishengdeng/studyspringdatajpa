package com.biz.gbck.dao.mysql.po.product.bbc;

import com.biz.gbck.dao.mysql.po.product.meta.Brand;
import com.biz.gbck.dao.mysql.po.product.meta.Category;
import com.biz.gbck.dao.mysql.po.product.meta.ExtendProperty;
import com.biz.gbck.dao.mysql.po.product.meta.SaleTag;
import com.biz.gbck.enums.product.ProductAuditStatusEnum;
import com.biz.gbck.enums.product.VendorTypeEnum;
import com.biz.gbck.vo.product.backend.IProductAuditVo;
import com.biz.support.jpa.converter.ListStringConverter;
import com.biz.support.jpa.po.BaseEntity;
import java.util.List;
import javax.persistence.*;

/**
 * 商品审核 (用于平台端记录商家端提交的商品)
 *
 * @author david-liu
 * @date 2016年12月22日
 * @reviewer
 * @see
 */
//@Entity
//@Table(name = "pro_product_audit")
public class ProductAudit extends BaseEntity {

    private static final long serialVersionUID = 2778482748988267272L;

    /**
     * 商品 ID(创建审核记录之前, 创建好商品 ID)
     */
    private Long productId;

    /**
     * 商家 ID
     */
    @Column(nullable = false)
    private Long vendorId;

    /**
     * 商户名称
     */
    @Column(length = 50)
    private String vendorName;

    /**
     * 商品名称
     */
    @Column(length = 255, nullable = false)
    private String name;

    /**
     * 商品子标题
     */
    @Column(length = 50)
    private String subTitle;

    /**
     * 商品编码
     */
    @Column(length = 50, nullable = false)
    private String productCode;

    /**
     * 商家自定义商品编码
     */
    @Column(length = 50)
    private String vendorProductCode;

    /**
     * 商品国际条码
     */
    @Column(length = 50)
    private String i18nCode;

    /**
     * 商品简介
     */
    @Column
    private String breif;

    /**
     * 商品品牌
     */
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    /**
     * 商品分类
     */
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    /**
     * 商品扩展属性
     */
    @ManyToMany
    @JoinTable(name = "pro_product_audit_extend_property",
            joinColumns = {@JoinColumn(name = "product_audit_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "extend_property_id", referencedColumnName = "id")})
    private List<ExtendProperty> properties;

    /**
     * 商品介绍
     */
    @Column(columnDefinition = "TEXT")
    @Convert(converter = ListStringConverter.class)
    private List<String> introImages;

    /**
     * 商品 Logo
     */
    @Column
    private String logo;

    /**
     * 商品重量(单位: 克)
     */
    @Column
    private Integer weight;

    /**
     * 商品轮播图
     */
    @Column(columnDefinition = "TEXT")
    @Convert(converter = ListStringConverter.class)
    private List<String> images;

    @ManyToMany
    @JoinTable(name = "pro_sale_tag_product_audit",
            joinColumns = {@JoinColumn(name = "product_audit_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "sale_tag_id", referencedColumnName = "id")})
    private List<SaleTag> saleTags;

    /**
     * SEO 标题
     */
    @Column
    private String seoTitle;

    /**
     * SEO 关键字信息
     */
    @Column
    private String seoKeywords;

    /**
     * SEO 描述信息
     */
    @Column
    private String seoDescription;

    /**
     * 商品审核状态
     */
    @Enumerated(value = EnumType.STRING)
    private ProductAuditStatusEnum auditStatus;

    /**
     * 审核备注
     */
    @Column(columnDefinition = "TEXT")
    private String auditMessage;

    /**
     * 商品是否被平台端锁定
     */
    @Column
    private Boolean locked;

    /**
     * 商品平台类型
     */
    @Column
    @Enumerated(EnumType.STRING)
    private VendorTypeEnum productType;

    /**
     * 删除标识
     */
    @Column
    private Boolean deleteFlag = Boolean.FALSE;

    public String getBreif() {
        return breif;
    }

    public void setBreif(String breif) {
        this.breif = breif;
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

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<ExtendProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<ExtendProperty> properties) {
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

    public List<SaleTag> getSaleTags() {
        return saleTags;
    }

    public void setSaleTags(List<SaleTag> saleTags) {
        this.saleTags = saleTags;
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

    public ProductAuditStatusEnum getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(ProductAuditStatusEnum auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getAuditMessage() {
        return auditMessage;
    }

    public void setAuditMessage(String auditMessage) {
        this.auditMessage = auditMessage;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public VendorTypeEnum getProductType() {
        return productType;
    }

    public void setProductType(VendorTypeEnum productType) {
        this.productType = productType;
    }

    public String getVendorProductCode() {
        return vendorProductCode;
    }

    public void setVendorProductCode(String vendorProductCode) {
        this.vendorProductCode = vendorProductCode;
    }

    /**
     * 基于 Vo构建 Po
     *
     * @param vo vo
     */
    public void fromVo(IProductAuditVo vo) {
        this.setName(vo.getName());
        this.setSubTitle(vo.getSubTitle());
        this.setVendorProductCode(vo.getProductCode());
        this.setI18nCode(vo.getI18nCode());
        this.setBreif(vo.getBreif());
        this.setLogo(vo.getLogo());
        this.setImages(vo.getImages());
        this.setIntroImages(vo.getIntroImages());
        this.setSeoTitle(vo.getSeoTitle());
        this.setSeoKeywords(vo.getSeoKeywords());
        this.setSeoDescription(vo.getSeoDescription());
        this.setWeight(vo.getWeight());
        this.setProductType(vo.getType());
    }

    @Override
    public String toString() {
        return "ProductAudit{" +
                "productId=" + productId +
                ", vendorId=" + vendorId +
                ", vendorName='" + vendorName + '\'' +
                ", name='" + name + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", productCode='" + productCode + '\'' +
                ", vendorProductCode='" + vendorProductCode + '\'' +
                ", i18nCode='" + i18nCode + '\'' +
                ", breif='" + breif + '\'' +
                ", brand=" + brand +
                ", category=" + category +
                ", properties=" + properties +
                ", introImages=" + introImages +
                ", logo='" + logo + '\'' +
                ", weight=" + weight +
                ", images=" + images +
                ", saleTags=" + saleTags +
                ", seoTitle='" + seoTitle + '\'' +
                ", seoKeywords='" + seoKeywords + '\'' +
                ", seoDescription='" + seoDescription + '\'' +
                ", auditStatus=" + auditStatus +
                ", auditMessage='" + auditMessage + '\'' +
                ", locked=" + locked +
                ", productType=" + productType +
                ", deleteFlag=" + deleteFlag +
                '}';
    }
}
