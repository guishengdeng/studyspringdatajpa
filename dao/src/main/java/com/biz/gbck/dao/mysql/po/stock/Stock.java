package com.biz.gbck.dao.mysql.po.stock;

import com.biz.gbck.enums.org.CompanyLevel;
import com.biz.support.jpa.po.BaseEntity;

import javax.persistence.*;

/**
 * 库存
 *
 * @author lei
 * @date 2017年04月24日
 * @reviewer
 * @see
 */
@Entity
@Table(name = "sto_stock_book", uniqueConstraints = {@UniqueConstraint(columnNames = {"company_id", "product_id"})})
public class Stock extends BaseEntity {

    private static final long serialVersionUID = 5652389386181748226L;

    /**
     * 隔壁仓库、省公司(平台公司)、合伙人
     */
    @Column(name = "company_id", nullable = false)
    private Long companyId;

    //级别
    @Enumerated(value = EnumType.STRING)
    @Column
    private CompanyLevel companyLevel;

    /**
     * 公司名称(冗余)
     */
    @Column(length = 40)
    private String companyName;

    /**
     * 商品Id
     */
    @Column(name = "product_id", nullable = false)
    private Long productId;

    /**
     * 商品编码
     */
    @Column(length = 40)
    private Long productCode;

    /**
     * 商品名称
     */
    @Column(length = 40)
    private Long productName;

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

    public Long getProductCode() {
        return productCode;
    }

    public void setProductCode(Long productCode) {
        this.productCode = productCode;
    }

    public Long getProductName() {
        return productName;
    }

    public void setProductName(Long productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
