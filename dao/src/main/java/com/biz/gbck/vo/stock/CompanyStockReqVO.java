package com.biz.gbck.vo.stock;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 *  单个商品库存 返回vo

 *
 * @author lei
 */
public class CompanyStockReqVO implements Serializable {
    private static final long serialVersionUID = 2184303947507749926L;
    /**
     * 商品id(必选)
     */
    private Long productId;

    /**
     * 合伙人id(必选)
     */
    private Long companyId;

    public CompanyStockReqVO() {
    }

    public CompanyStockReqVO(Long companyId, Long productId) {
        this();
        this.companyId = companyId;
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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
