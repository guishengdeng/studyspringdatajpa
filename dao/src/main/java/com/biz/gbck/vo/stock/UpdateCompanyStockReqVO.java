package com.biz.gbck.vo.stock;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * 更新合伙人库存请求vo
 *
 * @author lei
 */
public class UpdateCompanyStockReqVO implements Serializable {
    private static final long serialVersionUID = 1740147389692009015L;

    /**
     * 商品id(必选)
     */
    private Long productId;

    /**
     * 合伙人id(必选)
     */
    private Long companyId;

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

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
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
