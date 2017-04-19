package com.biz.gbck.vo.depot;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 门店特价请求Vo
 *
 * @author david-liu
 * @date 2017年02月05日
 * @reviewer
 */
public class DepotPromotionReqVo implements Serializable {
    private static final long serialVersionUID = 161766844074059971L;

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

    public DepotPromotionReqVo(String productCode, String depotCode, String warehouseDepotCode) {
        this.productCode = productCode;
        this.depotCode = depotCode;
        this.warehouseDepotCode = warehouseDepotCode;
    }

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
        return ToStringBuilder.reflectionToString(this);
    }
}
