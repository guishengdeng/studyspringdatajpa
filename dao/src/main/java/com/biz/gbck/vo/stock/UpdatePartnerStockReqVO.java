package com.biz.gbck.vo.stock;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * 更新合伙人库存请求vo
 *
 * @author lei
 */
public class UpdatePartnerStockReqVO implements Serializable {
    private static final long serialVersionUID = 1740147389692009015L;

    /**
     * 商品id(必选)
     */
    private Long productId;

    /**
     * 合伙人id(必选)
     */
    private Long partnerId;

    /**
     * 库存更新数量(正数即加库存, 负数即减库存)
     */
    private int quantity = 0;


    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
