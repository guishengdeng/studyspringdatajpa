package com.biz.gbck.vo.depot;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 门店及省仓门店ResponseVo
 *
 * @author zhangcheng
 * @date 2017/1/22
 * @reviewer
 * @see
 */
public class DepotAndWarehouseDepotRespVo implements Serializable {

    private static final long serialVersionUID = -3773836163420838462L;

    /**
     * 门店编码
     */
    private String depotCode;

    /**
     * 该门店所对应的省仓门店编码
     */
    private String warehouseDepotCode;

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
        return new ToStringBuilder(this)
                .append("depotCode", depotCode)
                .append("warehouseDepotCode", warehouseDepotCode)
                .toString();
    }
}
