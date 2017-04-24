package com.biz.gbck.vo.product.frontend;

import java.io.Serializable;
import java.util.List;

/**
 * 秒杀商品详情Vo
 *
 * @author david-liu
 * @date 2017年02月17日
 * @reviewer
 */
public class SeckillProductDetailRespVo implements Serializable {
    private static final long serialVersionUID = 5447904228001180664L;

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
     * 商品市场价(单位: 分)
     *
     * <pre>
     *     1. 如果是A类商品, 使用全国统一价格
     *     2. 如果是B类商品, 使用省仓门店价格
     * </pre>
     */
    private Integer marketPrice;

    /**
     * 商品重量(单位: 克)
     */
    private Integer weight;

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
     * 预计送达时间
     */
    private String predictArrival;

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

    public void setProductImages(List<String> productImages) {
        this.productImages = productImages;
    }

    public Integer getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Integer marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
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

    public String getPredictArrival() {
        return predictArrival;
    }

    public void setPredictArrival(String predictArrival) {
        this.predictArrival = predictArrival;
    }

}
