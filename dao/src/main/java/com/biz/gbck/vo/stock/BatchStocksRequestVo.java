package com.biz.gbck.vo.stock;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 多门店多商品库存请求vo
 * @author lei
 */
public class BatchStocksRequestVo implements Serializable {
    private static final long serialVersionUID = 4372920007617904178L;
    /**
     * 商品编码(必选)
     */
    private List<String> productCodes;

    /**
     * 门店编号(可选)
     */
    private String depotCode;

    /**
     * 是否包含省仓
     */
    private Boolean isProvince = false;

    public String getDepotCode() {
        return depotCode;
    }

    public void setDepotCode(String depotCode) {
        this.depotCode = depotCode;
    }

    public List<String> getProductCodes() {
        return productCodes;
    }

    public void setProductCodes(List<String> productCodes) {
        this.productCodes = productCodes;
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
