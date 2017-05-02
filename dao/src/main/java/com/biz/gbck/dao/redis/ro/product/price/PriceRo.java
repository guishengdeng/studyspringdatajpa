package com.biz.gbck.dao.redis.ro.product.price;

import com.biz.redis.annotation.Ro;
import com.biz.redis.bean.BaseRedisObject;

/**
 * 商品Ro(RoID为: 商品编码 + 客户组ID
 * Created by david-liu on 2017/04/21 13:49.
 */
@Ro(key = "product:PriceRo")
public class PriceRo extends BaseRedisObject<String> {
    private static final long serialVersionUID = -5965702705818904640L;

    /**
     * 价格ID
     */
    private Long priceId;

    /**
     * 客户组ID
     */
    private Long companyGroupId;

    /**
     * 价格组ID
     */
    private Long priceGroupId;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 采购价
     */
    private Integer purchasePrice;

    /**
     * 移动平均价
     */
    private Integer dynamicAveragePrice;

    /**
     * 销售价
     */
    private Integer salePrice;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Integer purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Integer getDynamicAveragePrice() {
        return dynamicAveragePrice;
    }

    public void setDynamicAveragePrice(Integer dynamicAveragePrice) {
        this.dynamicAveragePrice = dynamicAveragePrice;
    }

    public Integer getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Integer salePrice) {
        this.salePrice = salePrice;
    }

    public Long getCompanyGroupId() {
        return companyGroupId;
    }

    public void setCompanyGroupId(Long companyGroupId) {
        this.companyGroupId = companyGroupId;
    }

    public Long getPriceGroupId() {
        return priceGroupId;
    }

    public void setPriceGroupId(Long priceGroupId) {
        this.priceGroupId = priceGroupId;
    }

    public Long getPriceId() {
        return priceId;
    }

    public void setPriceId(Long priceId) {
        this.priceId = priceId;
    }
}
