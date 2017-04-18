package com.biz.gbck.vo.stock;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 更新门店库存请求vo
 *
 * @author lei
 */
public class UpdateDepotStockRequestVo implements Serializable {
    private static final long serialVersionUID = 1740147389692009015L;

    /**
     * 商品编码(必选)
     */
    private String productCode;

    /**
     * 门店编码(必选)
     */
    private String depotCode;

    /**
     * 库存更新数量(正数即加库存, 负数即减库存)
     */
    private int quantity = 0;

    public UpdateDepotStockRequestVo(String productCode, String depotCode, int quantity) {
        this.productCode = productCode;
        this.depotCode = depotCode;
        this.quantity = quantity;
    }

    public UpdateDepotStockRequestVo(String productCode, int quantity) {
        this.productCode = productCode;
        this.quantity = quantity;
    }

    public String getDepotCode() {
        return depotCode;
    }

    public void setDepotCode(String depotCode) {
        this.depotCode = depotCode;
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
