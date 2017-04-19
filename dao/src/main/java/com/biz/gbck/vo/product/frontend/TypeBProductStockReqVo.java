package com.biz.gbck.vo.product.frontend;

import java.io.Serializable;

/**
 * 商品库存请求Vo
 *
 * @author david-liu
 * @date 2017年02月17日
 * @reviewer
 */
public class TypeBProductStockReqVo implements Serializable {
    private static final long serialVersionUID = 525762071516605995L;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 门店编码(当开启了快喝模式情况下, 获取门店库存必传)
     */
    private String depotCode;

    /**
     * 省仓门店编码(查询B类商品库存必传)
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

    @Override
    public String toString() {
        return "ProductStockReqVo{" +
                "productCode='" + productCode + '\'' +
                ", depotCode='" + depotCode + '\'' +
                ", warehouseDepotCode='" + warehouseDepotCode + '\'' +
                '}';
    }
}
