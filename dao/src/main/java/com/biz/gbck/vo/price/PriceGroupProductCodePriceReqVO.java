package com.biz.gbck.vo.price;

import java.io.Serializable;

/**
 * 通过商品价格组ID, 商品
 *
 * Created by david-liu on 2017/05/02 15:12.
 */
public class PriceGroupProductCodePriceReqVO implements Serializable {
    private static final long serialVersionUID = -5639343868673627883L;

    /**
     * 价格组ID
     */
    private Long priceGroupId;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 上级采购方ID
     */
    private Long sellerId;

    public PriceGroupProductCodePriceReqVO(Long priceGroupId, String productCode, Long sellerId) {
        this.priceGroupId = priceGroupId;
        this.productCode = productCode;
        this.sellerId = sellerId;
    }

    public Long getPriceGroupId() {
        return priceGroupId;
    }

    public void setPriceGroupId(Long priceGroupId) {
        this.priceGroupId = priceGroupId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }
}
