package com.biz.gbck.vo.search.bbc;

import java.io.Serializable;

/**
 * B类商品价格请求Vo
 *
 * @author david-liu
 * @date 2017年01月20日
 * @reviewer
 */
public class TypeBPriceReqVo implements Serializable {
    private static final long serialVersionUID = -7397390483123672830L;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 门店编码
     */
    private String depotCode;

    /**
     * 省仓门店编码
     */
    private String warehouseDepotCode;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getDepotCode() {
        return depotCode;
    }

    public void setDepotCode(String depotCode) {
        this.depotCode = depotCode;
    }

    public String getWarehouseDepotCode() {
        return warehouseDepotCode;
    }

    public void setWarehouseDepotCode(String warehouseDepotCode) {
        this.warehouseDepotCode = warehouseDepotCode;
    }
}
