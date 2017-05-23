package com.biz.gbck.vo.stock;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 *  单个商品库存 返回vo
 * @author lei
 */
public class CompanyStockRespVO implements Serializable {
    private static final long serialVersionUID = 2184303947507749926L;
    /**return null;
     * 商品id(必选)
     */
    private Long productId;

    /**
     * 合伙人id(必选)
     */
    private Long companyId;

    /**
     * 库存数量量
     */
    private Integer quantity = 0;

    public CompanyStockRespVO() {
    }

    public CompanyStockRespVO(Long companyId, Long productId, Integer quantity) {
        this();
        this.companyId = companyId;
        this.productId = productId;
        this.quantity = quantity;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
