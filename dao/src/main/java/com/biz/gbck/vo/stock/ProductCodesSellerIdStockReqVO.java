package com.biz.gbck.vo.stock;

import java.io.Serializable;
import java.util.List;

/**
 * 通过商品编码, 上级采购方ID获取库存ReqVo
 *
 * Created by david-liu on 2017/05/02 11:50.
 */
public class ProductCodesSellerIdStockReqVO implements Serializable {
    private static final long serialVersionUID = -7570676304825612048L;

    private Long sellerId;

    private List<Long> productIds;

    public ProductCodesSellerIdStockReqVO() {
    }

    public ProductCodesSellerIdStockReqVO(Long sellerId, List<Long> productIds) {
        this.sellerId = sellerId;
        this.productIds = productIds;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }

    @Override
    public String toString() {
        return "ProductCodesSellerIdStockReqVO{" +
                "sellerId=" + sellerId +
                ", productIds=" + productIds +
                '}';
    }
}
