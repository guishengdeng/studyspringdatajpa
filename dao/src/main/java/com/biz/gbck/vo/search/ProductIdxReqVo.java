package com.biz.gbck.vo.search;

import java.io.Serializable;

/**
 * 商品索引请求vo
 *
 * @author david-liu
 * @date 2017年01月20日
 * @reviewer
 */
public class ProductIdxReqVo implements Serializable {
    private static final long serialVersionUID = 7137249240612716222L;

    /**
     * 商品索引类型
     */
    private Integer productType;

    /**
     * 门店编码
     */
    private String depotCode;

    /**
     * 省仓门店编码
     */
    private String warehouseDepotCode;

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
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

    @Override
    public String toString() {
        return "ProductIdxReqVo{" +
                "productType=" + productType +
                ", depotCode='" + depotCode + '\'' +
                ", warehouseDepotCode='" + warehouseDepotCode + '\'' +
                '}';
    }
}
