package com.biz.gbck.vo.stock;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 *  单个商品(合伙人)库存 返回vo
 * @author lei
 */
public class PartnerStockRespVO implements Serializable {
    private static final long serialVersionUID = 2184303947507749926L;
    /**return null;
     * 商品id(必选)
     */
    private Long productId;

    /**
     * 合伙人id(必选)
     */
    private Long partnerId;

    /**
     * 库存数量量
     */
    private Integer quantity = 0;

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
