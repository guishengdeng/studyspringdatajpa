package com.biz.gbck.vo.price;

import java.io.Serializable;
import java.util.List;

/**
 * 商品价格ReqVo
 *
 * Created by david-liu on 2017/05/02 11:33.
 */
public class PriceGroupProductCodesPriceReqVO implements Serializable {
    private static final long serialVersionUID = 7310166382112876563L;

    /**
     * 价格组Id
     */
    private Long priceGroupId;

    /**
     * 商品编码集合
     */
    private List<String> productCodes;

    public PriceGroupProductCodesPriceReqVO(Long priceGroupId, List<String> productCodes) {
        this.priceGroupId = priceGroupId;
        this.productCodes = productCodes;
    }

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

    @Override
    public String toString() {
        return "ProductPriceReqVo{" +
                "priceGroupId=" + priceGroupId +
                ", productCodes=" + productCodes +
                '}';
    }
}
