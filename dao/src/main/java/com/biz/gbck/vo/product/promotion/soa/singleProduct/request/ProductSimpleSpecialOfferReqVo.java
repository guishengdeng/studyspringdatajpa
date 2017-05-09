package com.biz.gbck.vo.product.promotion.soa.singleProduct.request;

import java.io.Serializable;

/**
 * 商品简单特价ReqVo
 *
 * Created by david-liu on 2017/04/27 15:08.
 */
public class ProductSimpleSpecialOfferReqVo implements Serializable {
    private static final long serialVersionUID = 6294448481244964646L;

    /**
     * 价格组Id
     */
    private Long priceGroupId;

    /**
     * 商品编码
     */
    private String productCode;

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
}
