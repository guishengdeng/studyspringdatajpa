package com.biz.gbck.dao.mysql.po.product.master;

import com.biz.gbck.dao.mysql.po.product.price.Price;
import com.biz.support.jpa.po.BaseEntity;
import javax.persistence.*;

/**
 * 客户组商品
 *
 * Created by david-liu on 2017/05/24 16:28.
 */
@Entity
@Table(name = "pro_product_company_group")
public class CompanyGroupProduct extends BaseEntity {
    private static final long serialVersionUID = 5234297269800138831L;

    /**
     * 客户组ID
     */
    @Column(nullable = false)
    private Long companyGroupId;

    /**
     * 当前客户组价格
     */
    @OneToOne
    @JoinColumn(name = "price_id")
    private Price price;

    /**
     * 公司商品
     */
    @ManyToOne
    @JoinColumn(name = "depot_product_id")
    private DepotProduct depotProduct;

    public Long getCompanyGroupId() {
        return companyGroupId;
    }

    public void setCompanyGroupId(Long companyGroupId) {
        this.companyGroupId = companyGroupId;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public DepotProduct getDepotProduct() {
        return depotProduct;
    }

    public void setDepotProduct(DepotProduct depotProduct) {
        this.depotProduct = depotProduct;
    }
}
