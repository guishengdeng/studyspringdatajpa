package com.biz.gbck.dao.mysql.po.product;

import com.biz.gbck.enums.product.SaleStatusEnum;
import com.biz.support.jpa.po.BaseEntity;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.*;

/**
 * 门店商品
 *
 * @author david-liu
 * @date 2016年12月16日
 * @reviewer
 * @see
 */
@Entity
@Table(name = "pro_product_depot", uniqueConstraints = {@UniqueConstraint(columnNames = {"product_id", "depot_id"})})
public class DepotProduct extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -5692684536049410988L;

    /**
     * 门店商品商品信息
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    /**
     * 门店ID
     */
    @Column(name = "depot_id", nullable = false)
    private Long depotId;

    /**
     * 门店授权价
     */
    @Column(nullable = false)
    private Integer depotPrice;

    /**
     * 商品市场价
     */
    @Column(nullable = false)
    private Integer marketPrice;

    /**
     * 商品销售价
     */
    @Column(nullable = false)
    private Integer salePrice;

    /**
     * 商品上下架状态
     */
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private SaleStatusEnum saleStatus;

    /**
     * 商品上架时间(商品最新一次上架时间)
     */
    @Column(nullable = false)
    private Timestamp onSaleTime;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getDepotId() {
        return depotId;
    }

    public void setDepotId(Long depotId) {
        this.depotId = depotId;
    }

    public Integer getDepotPrice() {
        return depotPrice;
    }

    public void setDepotPrice(Integer depotPrice) {
        this.depotPrice = depotPrice;
    }

    public Integer getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Integer marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Integer getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Integer salePrice) {
        this.salePrice = salePrice;
    }

    public SaleStatusEnum getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(SaleStatusEnum saleStatus) {
        this.saleStatus = saleStatus;
    }

    public Timestamp getOnSaleTime() {
        return onSaleTime;
    }

    public void setOnSaleTime(Timestamp onSaleTime) {
        this.onSaleTime = onSaleTime;
    }
}
