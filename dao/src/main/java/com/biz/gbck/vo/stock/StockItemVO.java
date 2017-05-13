package com.biz.gbck.vo.stock;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * 更新合伙人锁定库存请求vo
 *
 * @author lei
 */
public class StockItemVO implements Serializable {

    private static final long serialVersionUID = -7226364318509584265L;
    private Long productId;

    /**
     * 数量(正数锁定，负数释放锁定)
     */
    private int quantity;


    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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
