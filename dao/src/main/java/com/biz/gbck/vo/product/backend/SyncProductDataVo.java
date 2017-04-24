package com.biz.gbck.vo.product.backend;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 同步商品数据Vo
 *
 * @author zhangcheng
 * @date 2017/2/9
 * @reviewer
 * @see
 */
public class SyncProductDataVo implements Serializable {

    private static final long serialVersionUID = 7609046395268707430L;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品子标题（商品副标题）
     */
    private String subTitle;

    /**
     * 商品中台编码
     */
    private String productCode;

    /**
     * 商品国际条码（商品条形码）
     */
    private String barCode;

    /**
     * 商品简介
     */
    private String brief;

    /**
     * 商品Logo
     */
    private String logo;

    /**
     * 商品图片（商品轮播图）
     */
    private List<String> productImages;

    /**
     * 介绍图片
     */
    private List<String> introImages;

    /**
     * 商品大分类(如: 白酒, 洋酒, 啤酒, 果酒, 黄酒, 酒具)
     */
    private String category;

    /**
     * 商品小分类(例: 进口啤酒_百威)
     */
    private String brand;

    /**
     * 香型（商品的扩展属性）
     */
    private String flavor;

    /**
     * 酒精度（商品的扩展属性）
     */
    private Integer alc;

    /**
     * 商品销售标签(如: 整箱购, 清仓大促)
     */
    private Collection<String> saleTags;

    /**
     * 商品创建时间
     */
    private Timestamp createTime;

    /**
     * 原麦汁浓度（商品的扩展属性）
     */
    private Integer wort;

    /**
     * 保质期(单位: 天)（商品的扩展属性）
     */
    private Integer shelfLife;

    /**
     * 原料/葡萄品种（商品的扩展属性）
     */
    private String material;

    /**
     * 制作工艺（商品的扩展属性）
     */
    private String craft;

    /**
     * 原产国（商品的扩展属性）
     */
    private String country;

    /**
     * 产地/产区（商品的扩展属性）
     */
    private String origin;

    /**
     * 葡萄年份（商品的扩展属性）
     */
    private Integer year;

    /**
     * 商品重量(单位: 克)
     */
    private Integer weight;

    /**
     * 商品净含量(单位: 毫升)（商品的扩展属性）
     */
    private Integer netContent;

    /**
     * 商品规格(瓶/箱/支)（商品的扩展属性）
     */
    private String standard;

    /**
     * 商品上架时间
     */
    private Timestamp onSaleTime;

    /**
     * 商品总销量
     */
    private Integer totalSalesVolume;

    /**
     * 商品总点击量
     */
    private Integer totalViewVolume;

    /**
     * 商品评价总数
     */
    private Integer evaluationCount;

    /**
     * 商品评分(如果5分, 存50)
     */
    private Integer productScore;

    /**
     * 商品销售价
     */
    private Integer price;

    /**
     * 市场价
     */
    private Integer marketPrice;

    /**
     * 成本价
     */
    private Integer costPrice;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Integer marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Integer getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Integer costPrice) {
        this.costPrice = costPrice;
    }

    public Integer getTotalSalesVolume() {
        return totalSalesVolume;
    }

    public void setTotalSalesVolume(Integer totalSalesVolume) {
        this.totalSalesVolume = totalSalesVolume;
    }

    public Integer getTotalViewVolume() {
        return totalViewVolume;
    }

    public void setTotalViewVolume(Integer totalViewVolume) {
        this.totalViewVolume = totalViewVolume;
    }

    public Integer getEvaluationCount() {
        return evaluationCount;
    }

    public void setEvaluationCount(Integer evaluationCount) {
        this.evaluationCount = evaluationCount;
    }

    public Integer getProductScore() {
        return productScore;
    }

    public void setProductScore(Integer productScore) {
        this.productScore = productScore;
    }

    public Timestamp getOnSaleTime() {
        return onSaleTime;
    }

    public void setOnSaleTime(Timestamp onSaleTime) {
        this.onSaleTime = onSaleTime;
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

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public Integer getAlc() {
        return alc;
    }

    public void setAlc(Integer alc) {
        this.alc = alc;
    }

    public Collection<String> getSaleTags() {
        return saleTags;
    }

    public void setSaleTags(Collection<String> saleTags) {
        this.saleTags = saleTags;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getWort() {
        return wort;
    }

    public void setWort(Integer wort) {
        this.wort = wort;
    }

    public Integer getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(Integer shelfLife) {
        this.shelfLife = shelfLife;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getCraft() {
        return craft;
    }

    public void setCraft(String craft) {
        this.craft = craft;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getNetContent() {
        return netContent;
    }

    public void setNetContent(Integer netContent) {
        this.netContent = netContent;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
