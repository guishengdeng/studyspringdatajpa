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
@Table(name = "sto_stock", uniqueConstraints = {@UniqueConstraint(columnNames = {"company_id", "product_id"})})
public class CompanyStock extends BaseEntity {

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
     * 库存数量
     */
    private Integer quantity;

    /**
     * 库存数量
     */
    private Integer lockQuantity;

    /**
     * 实际数量
     */
    private Integer realQuantity;

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

    public Integer getLockQuantity() {
        return lockQuantity;
    }

    public void setLockQuantity(Integer lockQuantity) {
        this.lockQuantity = lockQuantity;
    }

    public Integer getRealQuantity() {
        return realQuantity;
    }

    public void setRealQuantity(Integer realQuantity) {
        this.realQuantity = realQuantity;
    }
}
