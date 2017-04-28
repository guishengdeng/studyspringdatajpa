package com.biz.gbck.vo.product.gbck.request;

import java.io.Serializable;

/**
 * App商品详情请求Vo
 *
 * Created by david-liu on 2017/04/28 11:59.
 */
public class ProductAppDetailReqVo implements Serializable {
    private static final long serialVersionUID = 2612052700611378983L;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 价格组ID
     */
    private Long priceGroupId;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Long getPriceGroupId() {
        return priceGroupId;
    }

    public void setPriceGroupId(Long priceGroupId) {
        this.priceGroupId = priceGroupId;
    }
}
