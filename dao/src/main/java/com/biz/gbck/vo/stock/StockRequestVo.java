package com.biz.gbck.vo.stock;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 单个商品(门店)库存请求vo
 * @author lei
 */
public class StockRequestVo implements Serializable {
    private static final long serialVersionUID = 2184303947507749926L;
    /**
     * 商品编码(必选)
     */
    private String productCode;

    /**
     * 门店编号(可选)
     */
    private String depotCode;

    /**
     * 是否包含省仓(可选)
     */
    private Boolean isProvince = false;


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

    public Boolean getIsProvince() {
        return isProvince;
    }

    public void setIsProvince(Boolean isProvince) {
        this.isProvince = isProvince;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
