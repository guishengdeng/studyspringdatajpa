package com.biz.gbck.vo.product.frontend;

import com.biz.gbck.enums.product.DeliverType;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * BBC 商品列表项 Vo
 *
 * @author david-liu
 * @date 2017年01月04日
 * @reviewer
 */
public class ProductListItemVo implements Serializable {

    private static final long serialVersionUID = 2182996746973540544L;

    /**
     * 阿里云推荐记录足迹Id
     */
    private String traceId;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 商户自定义商品编码
     */
    private String vendorProductCode;

    /**
     * 商品类型
     */
    private Integer productType;

    /**
     * 商品 ID
     */
    private Long productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品图标
     */
    private String logo;

    /**
     * 商品市场价(单位:分)
     */
    private Integer marketPrice;

    /**
     * 商品销售价
     */
    private Integer finalPrice;

    /**
     * 商品角标
     */
    private List<ProductApartTagVo> apartTags;

    /**
     * 配送方式
     */
    private DeliverType deliverType;

    /**
     * 商家 ID
     */
    private String vendorId;

    /**
     * 门店 ID
     */
    private String depotCode;

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
     * 预计送达
     */
    private String predictArrival;

    /**
     * 店铺名称
     */
    private String shopName;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Integer marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Integer getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Integer finalPrice) {
        this.finalPrice = finalPrice;
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

    public List<ProductApartTagVo> getApartTags() {
        return apartTags;
    }

    public void setApartTags(List<ProductApartTagVo> apartTags) {
        this.apartTags = apartTags;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getDepotCode() {
        return depotCode;
    }

    public void setDepotCode(String depotCode) {
        this.depotCode = depotCode;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getVendorProductCode() {
        return vendorProductCode;
    }

    public void setVendorProductCode(String vendorProductCode) {
        this.vendorProductCode = vendorProductCode;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getPredictArrival() {
        return predictArrival;
    }

    public void setPredictArrival(String predictArrival) {
        this.predictArrival = predictArrival;
    }

    public DeliverType getDeliverType() {
        return deliverType;
    }

    public void setDeliverType(DeliverType deliverType) {
        this.deliverType = deliverType;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
