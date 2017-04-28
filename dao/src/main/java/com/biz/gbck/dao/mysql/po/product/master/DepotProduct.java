package com.biz.gbck.dao.mysql.po.product.master;

import com.biz.gbck.dao.mysql.po.product.price.Price;
import com.biz.gbck.enums.product.SaleStatusEnum;
import com.biz.support.jpa.po.BaseEntity;
import javax.persistence.*;

/**
 * 公司商品(隔壁仓库, 平台公司, 城市合伙人商品)
 *
 * Created by david-liu on 2017/04/21 09:07.
 */
@Entity
@Table(name = "pro_product_depot")
public class DepotProduct extends BaseEntity {
    private static final long serialVersionUID = 8502833232356155046L;

    /**
     * 公司ID
     */
    @Column(nullable = false)
    private Long companyId;

    /**
     * 对应商品
     */
    @JoinColumn(name = "product_id")
    @ManyToOne
    private Product product;

    @JoinColumn(name = "price_id")
    @OneToOne
    private Price price;

    /**
     * 上下架状态
     */
    @Column
    @Enumerated(value = EnumType.STRING)
    private SaleStatusEnum saleStatusEnum;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public SaleStatusEnum getSaleStatusEnum() {
        return saleStatusEnum;
    }

    public void setSaleStatusEnum(SaleStatusEnum saleStatusEnum) {
        this.saleStatusEnum = saleStatusEnum;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
}