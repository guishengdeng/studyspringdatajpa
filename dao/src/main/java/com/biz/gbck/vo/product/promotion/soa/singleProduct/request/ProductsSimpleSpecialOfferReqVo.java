package com.biz.gbck.vo.product.promotion.soa.singleProduct.request;

import java.io.Serializable;
import java.util.List;

/**
 * 获取批量商品的简单特价ReqVo
 *
 * Created by david-liu on 2017/04/27 14:46.
 */
public class ProductsSimpleSpecialOfferReqVo implements Serializable {
    private static final long serialVersionUID = -279848031880732351L;

    /**
     * 价格组ID
     */
    private Long priceGroupId;

    /**
     * 商品编码集合
     */
    private List<String> productCodes;

    public Long getPriceGroupId() {
        return priceGroupId;
    }

    public void setPriceGroupId(Long priceGroupId) {
        this.priceGroupId = priceGroupId;
    }

    public List<String> getProductCodes() {
        return productCodes;
    }

    public void setProductCodes(List<String> productCodes) {
        this.productCodes = productCodes;
    }
}
