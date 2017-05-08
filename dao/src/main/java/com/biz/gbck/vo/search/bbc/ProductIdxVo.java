package com.biz.gbck.vo.search.bbc;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 商品索引Vo
 *
 * @author david-liu
 * @date 2017年01月19日
 * @reviewer
 */
public class ProductIdxVo implements Serializable {
    private static final long serialVersionUID = 7311765060378612727L;

    /**
     * 索引ID(VendorID_ProductCode)
     */
    private String id;

    /**
     * 商品ID
     */
    private String productId;

    /**
     * 商家Id
     */
    private Long vendorId;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品子标题
     */
    private String subTitle;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 商品国际通行号
     */
    private String i18nCode;

    /**
     * 商品简介
     */
    private String brief;

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
     * 分类ID
     */
    private Long category;

    /**
     * 品牌Id
     */
    private Long brand;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 商品销售标签(使用逗号分割)
     */
    private String saleTags;

    /**
     * 商品销售标签Id(使用逗号分割)
     */
    private String saleTagIds;

    /**
     * 商品角标
     */
    private String apartTags;

    /**
     * 商品角标Id(使用逗号分割)
     */
    private String apartTagIds;

    /**
     * 商品销售价
     */
    private Integer finalPrice;

    /**
     * 商品成本价
     */
    private Integer costPrice;

    /**
     * 商品最低限价
     */
    private Integer limitPrice;

    /**
     * 商品市场价
     */
    private Integer marketPrice;

    /**
     * 商品扩展属性(存属性ID_属性值, 以逗号分割)
     */
    private String properties;

    /**
     * 商品扩展属性(存属性名:属性值, 以逗号分割)
     */
    private String propertyTexts;

    /**
     * 1级会员价
     */
    private Integer price1;

    /**
     * 2级会员价
     */
    private Integer price2;

    /**
     * 3级会员价
     */
    private Integer price3;
    /**
     * 4级会员价
     */
    private Integer price4;
    /**
     * 5级会员价
     */
    private Integer price5;
    /**
     * 6级会员价
     */
    private Integer price6;
    /**
     * 7级会员价
     */
    private Integer price7;
    /**
     * 8级会员价
     */
    private Integer price8;
    /**
     * 9级会员价
     */
    private Integer price9;
    /**
     * 10级会员价
     */
    private Integer price10;
    /**
     * 11级会员价
     */
    private Integer price11;
    /**
     * 12级会员价
     */
    private Integer price12;
    /**
     * 13级会员价
     */
    private Integer price13;
    /**
     * 14级会员价
     */
    private Integer price14;
    /**
     * 15级会员价
     */
    private Integer price15;
    /**
     * 16级会员价
     */
    private Integer price16;
    /**
     * 17级会员价
     */
    private Integer price17;
    /**
     * 18级会员价
     */
    private Integer price18;
    /**
     * 19级会员价
     */
    private Integer price19;
    /**
     * 20级会员价
     */
    private Integer price20;

    /**
     * 上架时间
     */
    private Timestamp onSaleTime;

    /**
     * 门店编码
     */
    private String depotCode;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * geoId集合(可销售区域Id以逗号分割)
     */
    private String geoIds;

    /**
     * 商品类型(A类/B类)
     */
    private Integer productType;

    /**
     * 上下架状态
     */
    private Integer saleStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
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

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
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

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public Long getBrand() {
        return brand;
    }

    public void setBrand(Long brand) {
        this.brand = brand;
    }

    public String getSaleTags() {
        return saleTags;
    }

    public void setSaleTags(String saleTags) {
        this.saleTags = saleTags;
    }

    public String getSaleTagIds() {
        return saleTagIds;
    }

    public void setSaleTagIds(String saleTagIds) {
        this.saleTagIds = saleTagIds;
    }

    public String getApartTags() {
        return apartTags;
    }

    public void setApartTags(String apartTags) {
        this.apartTags = apartTags;
    }

    public String getApartTagIds() {
        return apartTagIds;
    }

    public void setApartTagIds(String apartTagIds) {
        this.apartTagIds = apartTagIds;
    }

    public Integer getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Integer finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Integer getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Integer costPrice) {
        this.costPrice = costPrice;
    }

    public Integer getLimitPrice() {
        return limitPrice;
    }

    public void setLimitPrice(Integer limitPrice) {
        this.limitPrice = limitPrice;
    }

    public Integer getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Integer marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public Integer getPrice1() {
        return price1;
    }

    public void setPrice1(Integer price1) {
        this.price1 = price1;
    }

    public Integer getPrice2() {
        return price2;
    }

    public void setPrice2(Integer price2) {
        this.price2 = price2;
    }

    public Integer getPrice3() {
        return price3;
    }

    public void setPrice3(Integer price3) {
        this.price3 = price3;
    }

    public Integer getPrice4() {
        return price4;
    }

    public void setPrice4(Integer price4) {
        this.price4 = price4;
    }

    public Integer getPrice5() {
        return price5;
    }

    public void setPrice5(Integer price5) {
        this.price5 = price5;
    }

    public Integer getPrice6() {
        return price6;
    }

    public void setPrice6(Integer price6) {
        this.price6 = price6;
    }

    public Integer getPrice7() {
        return price7;
    }

    public void setPrice7(Integer price7) {
        this.price7 = price7;
    }

    public Integer getPrice8() {
        return price8;
    }

    public void setPrice8(Integer price8) {
        this.price8 = price8;
    }

    public Integer getPrice9() {
        return price9;
    }

    public void setPrice9(Integer price9) {
        this.price9 = price9;
    }

    public Integer getPrice10() {
        return price10;
    }

    public void setPrice10(Integer price10) {
        this.price10 = price10;
    }

    public Integer getPrice11() {
        return price11;
    }

    public void setPrice11(Integer price11) {
        this.price11 = price11;
    }

    public Integer getPrice12() {
        return price12;
    }

    public void setPrice12(Integer price12) {
        this.price12 = price12;
    }

    public Integer getPrice13() {
        return price13;
    }

    public void setPrice13(Integer price13) {
        this.price13 = price13;
    }

    public Integer getPrice14() {
        return price14;
    }

    public void setPrice14(Integer price14) {
        this.price14 = price14;
    }

    public Integer getPrice15() {
        return price15;
    }

    public void setPrice15(Integer price15) {
        this.price15 = price15;
    }

    public Integer getPrice16() {
        return price16;
    }

    public void setPrice16(Integer price16) {
        this.price16 = price16;
    }

    public Integer getPrice17() {
        return price17;
    }

    public void setPrice17(Integer price17) {
        this.price17 = price17;
    }

    public Integer getPrice18() {
        return price18;
    }

    public void setPrice18(Integer price18) {
        this.price18 = price18;
    }

    public Integer getPrice19() {
        return price19;
    }

    public void setPrice19(Integer price19) {
        this.price19 = price19;
    }

    public Integer getPrice20() {
        return price20;
    }

    public void setPrice20(Integer price20) {
        this.price20 = price20;
    }

    public Timestamp getOnSaleTime() {
        return onSaleTime;
    }

    public void setOnSaleTime(Timestamp onSaleTime) {
        this.onSaleTime = onSaleTime;
    }

    public String getDepotCode() {
        return depotCode;
    }

    public void setDepotCode(String depotCode) {
        this.depotCode = depotCode;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getGeoIds() {
        return geoIds;
    }

    public void setGeoIds(String geoIds) {
        this.geoIds = geoIds;
    }


    public Integer getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(Integer saleStatus) {
        this.saleStatus = saleStatus;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public String getPropertyTexts() {
        return propertyTexts;
    }

    public void setPropertyTexts(String propertyTexts) {
        this.propertyTexts = propertyTexts;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
