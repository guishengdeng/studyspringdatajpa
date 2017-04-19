package com.biz.gbck.vo.stock;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 单个商品(门店)库存 返回vo
 *
 * @author lei
 */
public class StockResponseVo implements Serializable {
    private static final long serialVersionUID = -6353648674141485981L;
    /**
     * 门店编号
     */
    private String depotCode;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 库存总量
     */
    private Integer quantity = 0;

    public StockResponseVo(String productCode, String depotCode, Integer quantity) {
        this.productCode = productCode;
        this.depotCode = depotCode;
        this.quantity = quantity;
    }

    public StockResponseVo(String productCode, Integer quantity) {
        this.productCode = productCode;
        this.quantity = quantity;
    }

    public StockResponseVo() {
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
