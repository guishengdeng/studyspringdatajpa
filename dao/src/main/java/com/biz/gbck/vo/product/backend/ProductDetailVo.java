package com.biz.gbck.vo.product.backend;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 商品详情Vo
 *
 * <pre>
 *     1. 如果是A类商品的详情, stock字段设置为全国库存, 价格信息为全国统一价格
 *     2. 如果是B类商品的详情, stock字段和价格信息不设置(底层页中包含售货地址, 伴随着收货地址的变更, 价格库存信息会有变动)
 * </pre>
 *
 * @author david-liu
 * @date 2017年02月05日
 * @reviewer
 */
public class ProductDetailVo implements Serializable {

    private static final long serialVersionUID = 3883301566878841899L;

    /**
     * 商品ID
     */
    private String productId;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品子标题
     */
    private String subTitle;

    /**
     * 商家Id
     */
    private String vendorId;
    /**
     * 商家名称
     */
    private String vendorName;
    /**
     * 商品类型
     */
    private Integer productType;

    /**
     * 商品主图(轮播图)
     */
    private List<String> productImages;

    /**
     * 商品全国库存(A类商品使用全国库存)
     */
    private Integer countryStock;

    /**
     * 商品市场价(单位: 分)
     *
     * <pre>
     *     1. 如果是A类商品, 使用全国统一价格
     *     2. 如果是B类商品, 使用就近门店的价格(如果不存在就近门店, 该字段为空)
     * </pre>
     */
    private Integer marketPrice;

    /**
     * 商品市场价(单位：元)
     */
    private String convertMarketPrice;

    /**
     * 商品销售价(单位: 分)
     *
     * <pre>
     *     1. 如果是A类商品, 使用全国统一价格
     *     2. 如果是B类商品, 使用就近门店的价格(如果不存在就近门店, 该字段为空)
     * </pre>
     */
    private Integer finalPrice;

    /**
     * 商品销售价(单位：元)
     */
    private String convertFinalPrice;

    /**
     * 商品重量(单位: 克)
     */
    private Integer weight;

    /**
     * 就近门店的库存
     *
     * <pre>
     *     1. 如果是A类商品, 该字段置为空
     *     2. 如果是B类商品, 使用就近门店的库存(如果不存在就近门店, 该字段为空)
     * </pre>
     */
    private Integer depotStock;

    /**
     * 全省库存
     *
     * <pre>
     *     1. 如果是A类商品, 该字段置为空
     *     2. 如果是B类商品, 使用全省库存
     * </pre>
     */
    private Integer provinceStock;

    /**
     * 省仓门店市场价(单位: 分, B类商品使用)
     */
    private Integer warehouseDepotMarketPrice;

    /**
     * 省仓门店销售价(单位: 分, B类商品使用)
     */
    private Integer warehouseDepotFinalPrice;

    /**
     * 品牌ID
     */
    private String brandId;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 分类ID
     */
    private String categoryId;

    /**
     * 商品logo
     */
    private String logo;

    /**
     * 介绍图片
     */
    private List<String> introImages;

    /**
     * 预计送达时间
     */
    private String predictArrival;

    /**
     * 是否展示'送达'
     */
    private Integer show;

    public String getPredictArrival() {
        return predictArrival;
    }

    public void setPredictArrival(String predictArrival) {
        this.predictArrival = predictArrival;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public List<String> getProductImages() {
        return productImages;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setProductImages(List<String> productImages) {
        this.productImages = productImages;
    }

    public Integer getCountryStock() {
        return countryStock;
    }

    public void setCountryStock(Integer countryStock) {
        this.countryStock = countryStock;
    }

    public Integer getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Integer marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getConvertMarketPrice() {
        return convertMarketPrice;
    }

    public void setConvertMarketPrice(String convertMarketPrice) {
        this.convertMarketPrice = convertMarketPrice;
    }

    public Integer getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Integer finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getConvertFinalPrice() {
        return convertFinalPrice;
    }

    public void setConvertFinalPrice(String convertFinalPrice) {
        this.convertFinalPrice = convertFinalPrice;
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

    public Integer getDepotStock() {
        return depotStock;
    }

    public void setDepotStock(Integer depotStock) {
        this.depotStock = depotStock;
    }

    public Integer getProvinceStock() {
        return provinceStock;
    }

    public void setProvinceStock(Integer provinceStock) {
        this.provinceStock = provinceStock;
    }

    public Integer getWarehouseDepotMarketPrice() {
        return warehouseDepotMarketPrice;
    }

    public void setWarehouseDepotMarketPrice(Integer warehouseDepotMarketPrice) {
        this.warehouseDepotMarketPrice = warehouseDepotMarketPrice;
    }

    public Integer getWarehouseDepotFinalPrice() {
        return warehouseDepotFinalPrice;
    }

    public void setWarehouseDepotFinalPrice(Integer warehouseDepotFinalPrice) {
        this.warehouseDepotFinalPrice = warehouseDepotFinalPrice;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public List<String> getIntroImages() {
        return introImages;
    }

    public void setIntroImages(List<String> introImages) {
        this.introImages = introImages;
    }

    public Integer getShow() {
        return show;
    }

    public void setShow(Integer show) {
        this.show = show;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
