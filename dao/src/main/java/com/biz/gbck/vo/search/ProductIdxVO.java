package com.biz.gbck.vo.search;

import java.io.Serializable;

/**
 * 商品索引Vo
 * Created by david-liu on 2017/05/03 10:41.
 */
public class ProductIdxVO implements Serializable {
    private static final long serialVersionUID = -9127902471785824831L;

    /**
     * ProductEsEntity id: 商品编码 + 价格组ID
     */
    private String id;

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 价格组ID
     */
    private Long priceGroup;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 售价
     */
    private Integer salePrice;

    /**
     * 移动平均价
     */
    private Integer dynamicAveragePrice;

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
     * 商品国际通行码
     */
    private String i18nCode;

    /**
     * 品牌ID
     */
    private String brand;

    /**
     * 分类ID
     */
    private String category;

    /**
     * 商品属性
     */
    private String properties;

    /**
     * 商品销售标签
     */
    private String saleTags;

    /**
     * 商品角标
     */
    private String apartTags;

    /**
     * 商品是否二维码管控
     */
    private Boolean isControlByQRCode;

    /**
     * 商品是否是流通商品
     */
    private Boolean isCircularFlow;

    /**
     * 商品是否是组合商品
     */
    private Boolean isRapidProduct;

    /**
     * 上下架状态
     */
    private Integer saleStatus;

    /**
     * 销量
     */
    private Integer salesVolume;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getPriceGroup() {
        return priceGroup;
    }

    public void setPriceGroup(Long priceGroup) {
        this.priceGroup = priceGroup;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Integer salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getDynamicAveragePrice() {
        return dynamicAveragePrice;
    }

    public void setDynamicAveragePrice(Integer dynamicAveragePrice) {
        this.dynamicAveragePrice = dynamicAveragePrice;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getSaleTags() {
        return saleTags;
    }

    public void setSaleTags(String saleTags) {
        this.saleTags = saleTags;
    }

    public String getApartTags() {
        return apartTags;
    }

    public void setApartTags(String apartTags) {
        this.apartTags = apartTags;
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

    public Boolean getRapidProduct() {
        return isRapidProduct;
    }

    public void setRapidProduct(Boolean rapidProduct) {
        isRapidProduct = rapidProduct;
    }

    public Integer getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(Integer saleStatus) {
        this.saleStatus = saleStatus;
    }

    public Integer getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }
}
