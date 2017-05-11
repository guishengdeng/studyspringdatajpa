package com.biz.gbck.vo.stock;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

/**
 *  批量商品(合伙人)库存 返回vo
 * @author lei
 */
public class PartnerStocksReqVO implements Serializable {

    private static final long serialVersionUID = 2184303947507749926L;
    /**
     * 商品ids(必选)
     */
    private List<Long> productIds;

    /**
     * 合伙人id(必选)
     */
    private Long partnerId;

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }

    public Long getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
