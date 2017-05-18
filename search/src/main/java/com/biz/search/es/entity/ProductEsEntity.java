package com.biz.search.es.entity;

import com.biz.gbck.vo.search.ProductIdxVO;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 商品{@code ElasticSearch Entity}
 *
 * @author david-liu
 * @date 2017年04月12日
 * @reviewer
 */
@Document(indexName = "depotnextdoor_products", type = "product")
public class ProductEsEntity implements Serializable {

    private static final long serialVersionUID = -7782509752271841542L;

    /**
     * ID(商品ID + 价格组ID)
     */
    @Id
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String id;

    /**
     * 商品Id
     */
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String productId;

    /**
     * 价格组ID
     */
    @Field(type = FieldType.Long, index = FieldIndex.not_analyzed, store = true)
    private Long priceGroup;

    /**
     * 库存
     */
    @Field(type = FieldType.Integer, index = FieldIndex.not_analyzed, store = true)
    private Integer stock;

    /**
     * 售价
     */
    @Field(type = FieldType.Integer, index = FieldIndex.not_analyzed, store = true)
    private Integer salePrice;

    /**
     * 移动平均价
     */
    @Field(type = FieldType.Integer, index = FieldIndex.not_analyzed, store = true)
    private Integer dynamicAveragePrice;

    /**
     * 名称
     */
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String name;

    /**
     * 子标题
     */
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String subTitle;

    /**
     * 商品编码
     */
    @Field(type = FieldType.String, analyzer = "ik", searchAnalyzer = "ik_smart", store = true)
    private String productCode;

    /**
     * 商品国际通行码
     */
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String i18nCode;

    /**
     * 品牌
     */
    @Field(type = FieldType.String, store = true)
    private String brand;

    /**
     * 品牌ID
     */
    @Field(type = FieldType.Long, store = true)
    private Long brandId;

    /**
     * 分类
     */
    @Field(type = FieldType.String, store = true)
    private String category;

    /**
     * 分类ID
     */
    @Field(type = FieldType.Long, store = true)
    private Long categoryId;

    /**
     * 扩展属性
     */
    @Field(type = FieldType.String, analyzer = "ik", searchAnalyzer = "ik_smart", store = true)
    private String properties;

    /**
     * 商品扩展属性
     */
    @Field(type = FieldType.String, analyzer = "ik", searchAnalyzer = "ik_smart", store = true)
    private String propertyTexts;

    /**
     * 销售标签
     */
    @Field(type = FieldType.String, analyzer = "ik", searchAnalyzer = "ik_smart", store = true)
    private String saleTags;

    /**
     * 角标
     */
    @Field(type = FieldType.String, analyzer = "ik", searchAnalyzer = "ik_smart", store = true)
    private String apartTags;

    /**
     * 是否二维码管控
     */
    @Field(type = FieldType.Boolean, index = FieldIndex.not_analyzed, store = true)
    private Boolean isControlByQRCode;

    /**
     * 是否是流通商品
     */
    @Field(type = FieldType.Boolean, index = FieldIndex.not_analyzed, store = true)
    private Boolean isCircularFlow;

    /**
     * 是否是组合商品
     */
    @Field(type = FieldType.Boolean, index = FieldIndex.not_analyzed, store = true)
    private Boolean isRapidProduct;

    /**
     * 是否上架
     */
    @Field(type = FieldType.Integer, index = FieldIndex.not_analyzed, store = true)
    private Integer saleStatus;

    /**
     * 销量
     */
    @Field(type = FieldType.Integer, index = FieldIndex.not_analyzed, store = true)
    private Integer salesVolume;

    @Field(type = FieldType.Long, index = FieldIndex.not_analyzed, store = true)
    private Long lastUpdateTimestamp;

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

    public Integer getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getPropertyTexts() {
        return propertyTexts;
    }

    public void setPropertyTexts(String propertyTexts) {
        this.propertyTexts = propertyTexts;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getLastUpdateTimestamp() {
        return lastUpdateTimestamp;
    }

    public void setLastUpdateTimestamp(Long lastUpdateTimestamp) {
        this.lastUpdateTimestamp = lastUpdateTimestamp;
    }

    public ProductEsEntity(ProductIdxVO idxVO, Long lastUpdateTimestamp) {
        this.setId(idxVO.getId());
        this.setProductId(String.valueOf(idxVO.getProductId()));
        this.setPriceGroup(idxVO.getPriceGroup());
        this.setStock(idxVO.getStock());
        this.setSalePrice(idxVO.getSalePrice());
        this.setDynamicAveragePrice(idxVO.getDynamicAveragePrice());
        this.setName(idxVO.getName());
        this.setSubTitle(idxVO.getSubTitle());
        this.setProductCode(idxVO.getProductCode());
        this.setI18nCode(idxVO.getI18nCode());
        this.setBrand(idxVO.getBrandName());
        this.setBrandId(idxVO.getBrandId());
        this.setCategory(idxVO.getCategoryName());
        this.setCategoryId(idxVO.getCategoryId());
        this.setProperties(idxVO.getProperties());
        this.setPropertyTexts(idxVO.getPropertyTexts());
        this.setSaleTags(idxVO.getSaleTags());
        this.setApartTags(idxVO.getApartTags());
        this.setControlByQRCode(idxVO.getControlByQRCode());
        this.setCircularFlow(idxVO.getCircularFlow());
        this.setRapidProduct(idxVO.getRapidProduct());
        this.setSaleStatus(idxVO.getSaleStatus());
        this.setSalesVolume(idxVO.getSalesVolume());
        this.setLastUpdateTimestamp(lastUpdateTimestamp);
    }

    public ProductEsEntity() {
    }

    public ProductEsEntity(ProductIdxVO idxVO) {
        this.setId(idxVO.getId());
        this.setProductId(String.valueOf(idxVO.getProductId()));
        this.setPriceGroup(idxVO.getPriceGroup());
        this.setStock(idxVO.getStock());
        this.setSalePrice(idxVO.getSalePrice());
        this.setDynamicAveragePrice(idxVO.getDynamicAveragePrice());
        this.setName(idxVO.getName());
        this.setSubTitle(idxVO.getSubTitle());
        this.setProductCode(idxVO.getProductCode());
        this.setI18nCode(idxVO.getI18nCode());
        this.setBrand(idxVO.getBrandName());
        this.setBrandId(idxVO.getBrandId());
        this.setCategory(idxVO.getCategoryName());
        this.setCategoryId(idxVO.getCategoryId());
        this.setProperties(idxVO.getProperties());
        this.setPropertyTexts(idxVO.getPropertyTexts());
        this.setSaleTags(idxVO.getSaleTags());
        this.setApartTags(idxVO.getApartTags());
        this.setControlByQRCode(idxVO.getControlByQRCode());
        this.setCircularFlow(idxVO.getCircularFlow());
        this.setRapidProduct(idxVO.getRapidProduct());
        this.setSaleStatus(idxVO.getSaleStatus());
        this.setSalesVolume(idxVO.getSalesVolume());
    }
}
