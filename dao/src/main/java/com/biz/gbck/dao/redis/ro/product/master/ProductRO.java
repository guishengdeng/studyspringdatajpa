package com.biz.gbck.dao.redis.ro.product.master;

import com.alibaba.fastjson.JSON;
import com.biz.core.util.StringTool;
import com.biz.gbck.enums.product.SaleStatusEnum;
import com.biz.gbck.vo.product.ProductPropertyContentVo;
import com.biz.gbck.vo.product.PropertyItemVo;
import com.biz.gbck.vo.product.RapidProductItemVo;
import com.biz.redis.annotation.Ro;
import com.biz.redis.annotation.RoSortedSet;
import com.biz.redis.bean.BaseRedisObject;
import com.google.common.collect.Lists;
import java.sql.Timestamp;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 商品Ro(RoId是商品ID)
 * Created by david-liu on 2017/04/21 11:53.
 */
@Ro(key = "product:ProductRo")
@RoSortedSet(key = "list", score = "createTimestamp")
public class ProductRO extends BaseRedisObject<Long> {
    private static final long serialVersionUID = -2204675391624489385L;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 商品名
     */
    private String name;

    /**
     * 商品副标题
     */
    private String subTitle;

    /**
     * 商品国际编码
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
     * 商品品牌名称
     */
    private String brandName;

    /**
     * 商品分类 ID
     */
    private Long categoryId;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 商品 Logo
     */
    private String logo;

    /**
     * 商品主图
     */
    private String images;

    /**
     * 商品详情图片
     */
    private String introImages;

    /**
     * 销售标签 ID (以逗号分割)
     */
    private String saleTagIds;

    /**
     * 销售标签文字
     */
    private String saleTags;

    /**
     * 角标 ID (以逗号分割)
     */
    private String apartTagIds;

    /**
     * 角标图片(以逗号分割)
     */
    private String apartTagImages;

    /**
     * 商品扩展属性(存放PropertyItemVo List以Json序列化之后的字符串)
     */
    private String propertiesJson;

    /**
     * 商品重量(单位: 克)
     */
    private Integer weight;

    /**
     * 最小起售量
     */
    private Integer minQuantity;

    /**
     * Seo 标题
     */
    private String seoTitle;

    /**
     * Seo 关键字
     */
    private String seoKeywords;

    /**
     * Seo 描述信息
     */
    private String seoDescription;

    /**
     * 上下架状态
     */
    private Integer saleStatus;

    /**
     * 商品平均评分
     */
    private Integer score;

    /**
     * 商品总评分
     */
    private Integer totalScore;

    /**
     * 商品评价总数
     */
    private Integer scoreCount;

    /**
     * 历史销量
     */
    private Integer salesVolume;

    /**
     * 点击量
     */
    private Integer viewVolume;

    /**
     * 上架时间
     */
    private Timestamp onSaleTime;

    /**
     * 是否是组合商品
     */
    private Boolean isRapidProduct = Boolean.FALSE;

    /**
     * 组合商品明细(以json字符串存储)
     */
    private String rapidProductInfo;

    /**
     * 是否二维码管控(默认二维码管控)
     */
    private Boolean isControlByQRCode = Boolean.TRUE;

    /**
     * 是否是流通商品(默认非流通商品)
     */
    private Boolean isCircularFlow = Boolean.FALSE;

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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getSaleTagIds() {
        return saleTagIds;
    }

    public void setSaleTagIds(String saleTagIds) {
        this.saleTagIds = saleTagIds;
    }

    public String getApartTagIds() {
        return apartTagIds;
    }

    public void setApartTagIds(String apartTagIds) {
        this.apartTagIds = apartTagIds;
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

    public Integer getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(Integer saleStatus) {
        this.saleStatus = saleStatus;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getScoreCount() {
        return scoreCount;
    }

    public void setScoreCount(Integer scoreCount) {
        this.scoreCount = scoreCount;
    }

    public Integer getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }

    public Integer getViewVolume() {
        return viewVolume;
    }

    public void setViewVolume(Integer viewVolume) {
        this.viewVolume = viewVolume;
    }

    public Timestamp getOnSaleTime() {
        return onSaleTime;
    }

    public void setOnSaleTime(Timestamp onSaleTime) {
        this.onSaleTime = onSaleTime;
    }

    public Boolean getRapidProduct() {
        return isRapidProduct;
    }

    public void setRapidProduct(Boolean rapidProduct) {
        isRapidProduct = rapidProduct;
    }

    public String getPropertiesJson() {
        return propertiesJson;
    }

    public void setPropertiesJson(String propertiesJson) {
        this.propertiesJson = propertiesJson;
    }

    public List<RapidProductItemVo> getRapidProductInfoVo() {
        return StringUtils.isEmpty(rapidProductInfo) ? Lists.<RapidProductItemVo>newArrayList() : JSON.parseArray(rapidProductInfo, RapidProductItemVo.class);
    }

    public String getRapidProductInfo() {
        return this.rapidProductInfo;
    }

    public void setRapidProductInfo(String rapidProductInfo) {
        if (StringUtils.isEmpty(rapidProductInfo)) {
            this.rapidProductInfo = "";
        } else {
            this.rapidProductInfo = rapidProductInfo;
        }
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getIntroImages() {
        return introImages;
    }

    public void setIntroImages(String introImages) {
        this.introImages = introImages;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<PropertyItemVo> getProperties() {
        if (StringUtils.isBlank(this.propertiesJson)) {
            return Lists.newArrayList();
        } else {
            return JSON.parseArray(propertiesJson, PropertyItemVo.class);
        }
    }

    public boolean isOnSale() {
        return this.saleStatus != null && this.saleStatus == SaleStatusEnum.ON_SALE.getValue();
    }

    public String getSaleTags() {
        return saleTags;
    }

    public void setSaleTags(String saleTags) {
        this.saleTags = saleTags;
    }

    public Integer getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(Integer minQuantity) {
        this.minQuantity = minQuantity;
    }

    public String getApartTagImages() {
        return apartTagImages;
    }

    public void setApartTagImages(String apartTagImages) {
        this.apartTagImages = apartTagImages;
    }

    public String getApartTagImage() {
        if (StringUtils.isBlank(this.apartTagImages)) {
            return null;
        } else {
            return StringTool.split(this.apartTagImages, ",").get(0);
        }
    }

    public ProductPropertyContentVo getProductProperty() {
        ProductPropertyContentVo vo = new ProductPropertyContentVo();
        vo.setProductCode(this.productCode);
        List<PropertyItemVo> propertyItems = this.getProperties();
        List<PropertyItemVo> appendProperties = Lists.newArrayList(
                new PropertyItemVo("名称", this.name),
                new PropertyItemVo("分类", this.categoryName),
                new PropertyItemVo("品牌", this.brandName)
        );
        CollectionUtils.addAll(appendProperties, propertyItems.iterator());
        vo.setItems(appendProperties);
        return vo;
    }

    @Override
    public String toString() {
        return "ProductRO{" +
                ", productCode='" + productCode + '\'' +
                ", name='" + name + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", i18nCode='" + i18nCode + '\'' +
                ", breif='" + breif + '\'' +
                ", brandId=" + brandId +
                ", brandName='" + brandName + '\'' +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", logo='" + logo + '\'' +
                ", images='" + images + '\'' +
                ", introImages='" + introImages + '\'' +
                ", saleTagIds='" + saleTagIds + '\'' +
                ", apartTagIds='" + apartTagIds + '\'' +
                ", propertiesJson='" + propertiesJson + '\'' +
                ", weight=" + weight +
                ", seoTitle='" + seoTitle + '\'' +
                ", seoKeywords='" + seoKeywords + '\'' +
                ", seoDescription='" + seoDescription + '\'' +
                ", saleStatus=" + saleStatus +
                ", score=" + score +
                ", totalScore=" + totalScore +
                ", scoreCount=" + scoreCount +
                ", salesVolume=" + salesVolume +
                ", viewVolume=" + viewVolume +
                ", onSaleTime=" + onSaleTime +
                ", isRapidProduct=" + isRapidProduct +
                ", rapidProductInfo='" + rapidProductInfo + '\'' +
                ", isControlByQRCode=" + isControlByQRCode +
                ", isCircularFlow=" + isCircularFlow +
                '}';
    }
}
