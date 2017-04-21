package com.biz.gbck.dao.mysql.po.product;

import com.alibaba.fastjson.JSON;
import com.biz.gbck.dao.mysql.po.product.bbc.ProductCascade;
import com.biz.gbck.dao.mysql.po.product.bbc.RelevantProduct;
import com.biz.gbck.vo.product.PropertyItemVo;
import com.biz.support.jpa.converter.ListStringConverter;
import com.biz.support.jpa.po.BaseEntity;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 商品JPA Entity
 *
 * Created by david-liu on 2017/04/20 10:59.
 */
@Entity
@Table(name = "pro_product")
public class Product extends BaseEntity {

    private static final long serialVersionUID = 1794204729130850758L;

    /**
     * 商品名称
     */
    @Column(length = 50, nullable = false)
    private String name;

    /**
     * 商品副标题
     */
    @Column(length = 50)
    private String subTitle;

    /**
     * 商品编码
     */
    @Column(length = 50, nullable = false)
    private String productCode;

    /**
     * 商品国际通行码
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
     * 商品 Logo 图片
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
     * 商品介绍图片
     */
    @Column(columnDefinition = "TEXT")
    @Convert(converter = ListStringConverter.class)
    private List<String> introImages;

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
    @JoinColumn(name = "relevant_product_id")
    private RelevantProduct relevantProduct;

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
     * 是否锁定商品(如果锁定, 不能修改)
     */
    @Column
    private Boolean locked = Boolean.FALSE;

    /**
     * 是否二维码管控(默认二维码管控)
     */
    @Column
    private Boolean isControlByQRCode = Boolean.TRUE;

    /**
     * 是否是流通商品(默认非流通商品)
     */
    @Column
    private Boolean isCircularFlow = Boolean.FALSE;

    /**
     * 是否是组合商品(默认是非组合商品)
     */
    @Column
    private Boolean isRapidProduct = Boolean.FALSE;

    /**
     * 组合商品
     */
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private GroupProduct groupProduct;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DepotProduct> depotProducts;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PriceGroup> priceGroups;

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

    public RelevantProduct getRelevantProduct() {
        return relevantProduct;
    }

    public void setRelevantProduct(RelevantProduct relevantProduct) {
        this.relevantProduct = relevantProduct;
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
        List<Long> apartTagIds = this.apartTags.stream().map(ApartTag::getId).collect(Collectors.toList());
        return StringUtils.join(apartTagIds, ",");
    }

    public String getPropertyJson() {
        List<ExtendProperty> properties = this.getProperties();
        if (CollectionUtils.isEmpty(properties)) {
            return "";
        }

        List<PropertyItemVo> propertyItemVos = properties.stream().map(property -> {
            PropertyItemVo itemVo = new PropertyItemVo();
            ProductExtend extend = property.getProductExtend();
            itemVo.setPropertyId(extend.getId());
            itemVo.setPropertyName(extend.getName());
            itemVo.setPropertyValue(property.getValue());
            itemVo.setPropertyValueId(property.getId());
            return itemVo;
        }).collect(Collectors.toList());

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

    public Boolean getControlByQRCode() {
        return isControlByQRCode;
    }

    public void setControlByQRCode(Boolean controlByQRCode) {
        isControlByQRCode = controlByQRCode;
    }

    public Boolean getCircularFlow() {
        return isCircularFlow;
    }

    public void setCircularFlow(Boolean circularFlow) {
        isCircularFlow = circularFlow;
    }

    public List<DepotProduct> getDepotProducts() {
        return depotProducts;
    }

    public void setDepotProducts(List<DepotProduct> depotProducts) {
        this.depotProducts = depotProducts;
    }

    public List<PriceGroup> getPriceGroups() {
        return priceGroups;
    }

    public void setPriceGroups(List<PriceGroup> priceGroups) {
        this.priceGroups = priceGroups;
    }
}
