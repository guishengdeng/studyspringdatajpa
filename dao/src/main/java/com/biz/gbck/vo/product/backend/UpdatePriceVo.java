package com.biz.gbck.vo.product.backend;

import java.io.Serializable;
import java.util.List;

/**
 * @author 江南
 * @date 2017/1/18
 * @reviewer
 */
public class UpdatePriceVo implements Serializable {

    private static final long serialVersionUID = -6342494135943239504L;

    /**
     * 商品ID
     */
    private String productId;

    /**
     * 商品编码吗
     */
    private String productCode;

    /**
     * 市场价
     */
    private String marketPrice;

    /**
     * 销售价
     */
    private String finalPrice;

    /**
     * 成本价
     */
    private Integer costPrice;

    /**
     * 最低限价
     */
    private Integer minPrice;

    /**
     * 价格区域集合
     */
    private List<String> regionIds;

    public UpdatePriceVo() {
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

    public String getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(String finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Integer getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Integer costPrice) {
        this.costPrice = costPrice;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public List<String> getRegionIds() {
        return regionIds;
    }

    public void setRegionIds(List<String> regionIds) {
        this.regionIds = regionIds;
    }
}
