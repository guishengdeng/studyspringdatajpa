package com.biz.gbck.dao.mysql.po.stock;

import com.biz.support.jpa.po.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 库存
 *
 * @author lei
 * @date 2017年04月24日
 * @reviewer
 * @see
 */
@Entity
@Table(name = "sto_stock_book")
public class Stock extends BaseEntity {

    private static final long serialVersionUID = 5652389386181748226L;

    /**
     * 隔壁仓库、省公司(平台公司)、合伙人
     */
    private Long companyId;

    /**
     * 公司名称(冗余)
     */
    @Column(length = 40)
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
