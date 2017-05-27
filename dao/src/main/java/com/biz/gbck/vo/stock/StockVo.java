package com.biz.gbck.vo.stock;

import com.biz.gbck.enums.org.CompanyLevel;

import java.io.Serializable;

/**
 * Created by lzz on 2017/5/19.
 */
public class StockVo implements Serializable {

    private static final long serialVersionUID = -9104301119154510912L;

    /**
     * 隔壁仓库、省公司(平台公司)、合伙人
     */
    private Long companyId;

    /**
     * 级别
     */
    private CompanyLevel companyLevel;

    /**
     * 公司名称(冗余)
     */
    private String companyName;

    /**
     * 商品Id
     */
    private Long productId;

    /**
     * 库存数量
     */
    private Integer quantity;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public CompanyLevel getCompanyLevel() {
        return companyLevel;
    }

    public void setCompanyLevel(CompanyLevel companyLevel) {
        this.companyLevel = companyLevel;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
