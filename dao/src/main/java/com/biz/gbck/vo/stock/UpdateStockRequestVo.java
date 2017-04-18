package com.biz.gbck.vo.stock;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 更新库存请求vo
 * @author lei
 */
public class UpdateStockRequestVo implements Serializable {

    private static final long serialVersionUID = -3799964725562870860L;

    /**
     * 商品编码(必选)
     */
    private String productCode;

    /**
     * 库存更新数量(正数即加库存, 负数即减库存)
     */
    private int quantity = 0;

    public UpdateStockRequestVo() {
    }

    public UpdateStockRequestVo(String productCode, int quantity) {
        this.productCode = productCode;
        this.quantity = quantity;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
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
