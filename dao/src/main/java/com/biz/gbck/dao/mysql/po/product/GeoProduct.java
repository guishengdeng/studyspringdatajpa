package com.biz.gbck.dao.mysql.po.product;

import com.biz.gbck.dao.redis.ro.product.ProductRo;
import com.biz.gbck.enums.product.GeoLevelEnum;
import com.biz.gbck.enums.product.SaleStatusEnum;
import com.biz.support.jpa.po.BaseEntity;
import com.google.common.collect.Lists;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 区域商品 Po
 *
 * @author david-liu
 * @date 2016年12月15日
 * @reviewer
 * @see
 */
@Entity
@Table(name = "pro_product_geo",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"product_id", "geo_level", "vendor_id"})})
public class GeoProduct extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 6777899828181301694L;

    /**
     * 区域 ID(可能为省, 市, 划分销售区域 ID)
     */
    @Column(name = "geo_ids", columnDefinition = "TEXT")
    private String geoIds;

    /**
     * 区域等级(可能为省, 市, 划分销售区域)
     */
    @Column(name = "geo_level", nullable = false)
    @Enumerated(EnumType.STRING)
    private GeoLevelEnum geoLevel;

    /**
     * 商品最低限价
     */
    @Column
    private Integer limitPrice;

    /**
     * 商家 ID
     */
    @Column(name = "vendor_id", nullable = false)
    private Long vendorId;

    /**
     * 商品信息
     */
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    /**
     * 商品市场价
     */
    @Column
    private Integer marketPrice;

    /**
     * 商品销售价
     */
    @Column
    private Integer salePrice;

    /**
     * 商品上架时间(记录商品最新一次的上架时间)
     */
    @Column(nullable = false)
    private Timestamp onSaleTimestamp;

    /**
     * 商品上下架状态
     */
    @Column
    @Enumerated(EnumType.STRING)
    private SaleStatusEnum saleStatus;

    /**
     * 商品审核状态
     */
    @OneToOne
    @JoinColumn(name = "product_audit_id")
    private ProductAudit productAudit;

    public ProductRo toProductRo() {
        ProductRo ro = new ProductRo();
        Product po = this.product;
        ro.setId(po.getProductCode());
        ro.setVendorId(po.getVendorId());
        ro.setProductCode(po.getProductCode());
        ro.setVendorProductCode(po.getVendorProductCode());
        ro.setName(po.getName());
        ro.setSubTitle(po.getSubTitle());
        ro.setI18nCode(po.getI18nCode());
        ro.setBreif(po.getBreif());
        Brand brand = po.getBrand();
        if (brand != null) {
            ro.setBrandId(brand.getId());
            ro.setBrandName(brand.getName());
        }
        if (po.getCategory() != null) {
            ro.setCategoryId(po.getCategory().getId());
        }
        if (CollectionUtils.isNotEmpty(po.getIntroImages())) {
            ro.setIntroImages(StringUtils.join(po.getIntroImages(), ","));
        }
        ro.setLogo(po.getLogo());
        List<SaleTag> saleTags = po.getSaleTags();
        if (CollectionUtils.isNotEmpty(saleTags)) {
            ro.setSaleTagIds(po.getSaleTagIds());
        }
        List<ApartTag> apartTags = po.getApartTags();
        if (CollectionUtils.isNotEmpty(apartTags)) {
            ro.setApartTagIds(po.getApartTagIds());
        }
        ro.setSeoTitle(po.getSeoTitle());
        ro.setSeoKeywords(po.getSeoKeywords());
        ro.setSeoDescription(po.getSeoDescription());
        if (CollectionUtils.isNotEmpty(po.getImages())) {
            ro.setImages(StringUtils.join(po.getImages(), ","));
        }
        ro.setProductId(po.getId());
        ro.setSaleStatus(this.getSaleStatus().getValue());
        ro.setProductType(po.getProductType().getValue());
        ro.setGeoIds(this.getGeoIds());
        ro.setOnSaleTime(this.getOnSaleTimestamp());
        List<ExtendProperty> properties = po.getProperties();
        if (CollectionUtils.isNotEmpty(properties)) {
            ro.setPropertiesJson(po.getPropertyJson());
        }
        ro.setRapidProduct(po.getRapidProduct());
        ro.setWeight(po.getWeight());
        return ro;
    }

    public GeoLevelEnum getGeoLevel() {
        return geoLevel;
    }

    public void setGeoLevel(GeoLevelEnum geoLevel) {
        this.geoLevel = geoLevel;
    }

    public Integer getLimitPrice() {
        return limitPrice;
    }

    public void setLimitPrice(Integer limitPrice) {
        this.limitPrice = limitPrice;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Integer marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Integer getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Integer salePrice) {
        this.salePrice = salePrice;
    }

    public Timestamp getOnSaleTimestamp() {
        return onSaleTimestamp;
    }

    public void setOnSaleTimestamp(Timestamp onSaleTimestamp) {
        this.onSaleTimestamp = onSaleTimestamp;
    }

    public String getGeoIds() {
        return geoIds;
    }

    public List<String> getGeoIdsString() {
        if (geoIds == null) {
            return null;
        }
        String[] geoIdsArray = geoIds.split(",");
        return Lists.newArrayList(geoIdsArray);
    }

    public void setGeoIds(String geoIds) {
        this.geoIds = geoIds;
    }

    public SaleStatusEnum getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(SaleStatusEnum saleStatus) {
        this.saleStatus = saleStatus;
    }

    public ProductAudit getProductAudit() {
        return productAudit;
    }

    public void setProductAudit(ProductAudit productAudit) {
        this.productAudit = productAudit;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
