package com.biz.gbck.vo.stock;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

/**
 *  批量商品(合伙人)库存 返回vo
 * @author lei
 */
public class CompanyStocksReqVO implements Serializable {

    private static final long serialVersionUID = 2184303947507749926L;
    /**
     * 商品ids(必选)
     */
    private List<Long> productIds;

    /**
     * 合伙人id(必选)
     */
    private Long companyId;

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
