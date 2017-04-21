package com.biz.gbck.dao.mysql.po.product.price;

import com.biz.gbck.dao.mysql.po.product.master.Product;
import com.biz.support.jpa.po.BaseEntity;
import java.util.List;
import javax.persistence.*;

/**
 * 价格组
 *
 * Created by david-liu on 2017/04/21 09:59.
 */
@Entity
@Table(name = "pro_price_group")
public class PriceGroup extends BaseEntity {
    private static final long serialVersionUID = -1168897622538664501L;

    /**
     * 客户组Id
     */
    @Column(nullable = false)
    private Long companyGroupId;

    /**
     * 商品信息
     */
    @JoinColumn(name = "product_id")
    private Product product;

    /**
     * 价格信息
     */
    @OneToOne(mappedBy = "priceGroup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Price price;

    /**
     * 行为日志
     */
    @OneToMany(mappedBy = "priceGroup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PriceActionLog> actionLogs;

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<PriceActionLog> getActionLogs() {
        return actionLogs;
    }

    public void setActionLogs(List<PriceActionLog> actionLogs) {
        this.actionLogs = actionLogs;
    }
}
