package com.biz.gbck.dao.mysql.po.product.bbc;

import com.alibaba.fastjson.JSON;
import com.biz.gbck.dao.mysql.po.product.*;
import com.biz.gbck.enums.product.SaleStatusEnum;
import com.biz.gbck.enums.product.VendorTypeEnum;
import com.biz.gbck.vo.product.PropertyItemVo;
import com.biz.support.jpa.converter.ListStringConverter;
import com.biz.support.jpa.po.BaseEntity;
import com.google.common.collect.Lists;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 商品 Po
 *
 * @author david-liu
 * @date 2016年12月14日
 * @reviewer
 * @see
 */
@Entity
@Table(name = "pro_product")
public class Product extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -4560453072255817874L;

    /**
     * 商家 ID
     */
    @Column(nullable = true)
    private Long vendorId;

    /**
     * 商品类型(A类/B类)
     */
    @Column
    @Enumerated(EnumType.STRING)
    private VendorTypeEnum productType;

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
    @JoinTable(name = "pro_product_extend_property",
            joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "extend_property_id", referencedColumnName = "id")})
    private List<ExtendProperty> properties;

    /**
     * 商品介绍(富文本形式)
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
     * 商品轮播图
     */
    @Column(columnDefinition = "TEXT")
    @Convert(converter = ListStringConverter.class)
    private List<String> images;

    /**
     * 商品销售标签(商品的一种组合方式)
     */
    @ManyToMany
    @JoinTable(name = "pro_sale_tag_product",
            joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "sale_tag_id", referencedColumnName = "id")})
    private List<SaleTag> saleTags;

    @ManyToMany
    @JoinTable(name = "pro_product_cascade_product",
            joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "cascade_id", referencedColumnName = "id")})
    private List<ProductCascade> productCascades;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "relevance_product_id")
    private RelevanceProduct relevanceProduct;

    /**
     * 商品的伴随展示标签(商品列表项下的标签介绍)
     */
    @OneToMany
    private List<ApartTag> apartTags;

    /**
     * 商品统计数据
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ProductStatistic productStatistic;

    /**
     * 商品重量(单位: 克)
     */
    @Column
    private Integer weight;

    /**
     * 是否在审核(修改审核)
     */
    @Column
    private Boolean inAudit;

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
     * 删除标识
     */
    @Column
    private Boolean deleteFlag = Boolean.FALSE;

    /**
     * 是否锁定商品(如果锁定, 商家不能修改)
     */
    @Column
    private Boolean locked = Boolean.FALSE;

    /**
     * 是否是组合商品
     */
    private Boolean isRapidProduct = Boolean.FALSE;

    /**
     * 组合商品
     */
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private GroupProduct groupProduct;

    /**
     * B类商品上下架状态
     */
    @Column
    @Enumerated(EnumType.STRING)
    private SaleStatusEnum typeBSaleStatus;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public GroupProduct getGroupProduct() {
        return groupProduct;
    }

    public void setGroupProduct(GroupProduct groupProduct) {
        this.groupProduct = groupProduct;
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

    public RelevanceProduct getRelevanceProduct() {
        return relevanceProduct;
    }

    public void setRelevanceProduct(RelevanceProduct relevanceProduct) {
        this.relevanceProduct = relevanceProduct;
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

    public String getSaleTagIds() {
        if (CollectionUtils.isEmpty(this.saleTags)) {
            return null;
        }
        List<Long> saleTagIds = Lists.newArrayList();
        for (SaleTag saleTag : this.saleTags) {
            saleTagIds.add(saleTag.getId());
        }
        return StringUtils.join(saleTagIds, ",");
    }

    public String getApartTagIds() {
        if (CollectionUtils.isEmpty(this.saleTags)) {
            return null;
        }
        List<Long> apartTagIds = Lists.newArrayList();
        for (ApartTag apartTag : this.apartTags) {
            apartTagIds.add(apartTag.getId());
        }
        return StringUtils.join(apartTagIds, ",");
    }

    public String getPropertyJson() {
        List<ExtendProperty> properties = this.getProperties();
        if (CollectionUtils.isEmpty(properties)) {
            return "";
        }

        List<PropertyItemVo> propertyItemVos = Lists.newArrayList();
        for (ExtendProperty property : properties) {
            PropertyItemVo itemVo = new PropertyItemVo();
            ProductExtend extend = property.getProductExtend();
            itemVo.setPropertyId(extend.getId());
            itemVo.setPropertyName(extend.getName());
            itemVo.setPropertyValue(property.getValue());
            itemVo.setPropertyValueId(property.getId());
            propertyItemVos.add(itemVo);
        }
        return JSON.toJSONString(propertyItemVos);

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

    public ProductStatistic getProductStatistic() {
        return productStatistic;
    }

    public void setProductStatistic(ProductStatistic productStatistic) {
        this.productStatistic = productStatistic;
    }

    public List<ApartTag> getApartTags() {
        return apartTags;
    }

    public void setApartTags(List<ApartTag> apartTags) {
        this.apartTags = apartTags;
    }

    public Boolean getInAudit() {
        return inAudit;
    }

    public void setInAudit(Boolean inAudit) {
        this.inAudit = inAudit;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public List<ProductCascade> getProductCascades() {
        return productCascades;
    }

    public void setProductCascades(List<ProductCascade> productCascades) {
        this.productCascades = productCascades;
    }

    public VendorTypeEnum getProductType() {
        return productType;
    }

    public void setProductType(VendorTypeEnum productType) {
        this.productType = productType;
    }

    public Boolean getRapidProduct() {
        return isRapidProduct;
    }

    public void setRapidProduct(Boolean rapidProduct) {
        isRapidProduct = rapidProduct;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public SaleStatusEnum getTypeBSaleStatus() {
        return typeBSaleStatus;
    }

    public void setTypeBSaleStatus(SaleStatusEnum typeBSaleStatus) {
        this.typeBSaleStatus = typeBSaleStatus;
    }

    public String getVendorProductCode() {
        return vendorProductCode;
    }

    public void setVendorProductCode(String vendorProductCode) {
        this.vendorProductCode = vendorProductCode;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public Boolean getIsRapidProduct() {
        return isRapidProduct;
    }

    public void setIsRapidProduct(Boolean isRapidProduct) {
        this.isRapidProduct = isRapidProduct;
    }


}
