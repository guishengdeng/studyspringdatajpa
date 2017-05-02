package com.biz.gbck.dao.mysql.po.product.price;

import com.biz.support.jpa.po.BaseEntity;
import javax.persistence.*;

/**
 * 价格
 *
 * Created by david-liu on 2017/04/21 09:45.
 */
@Entity
@Table(name = "pro_price")
public class Price extends BaseEntity {

    private static final long serialVersionUID = -9051921442149287058L;

    /**
     * 商品编码
     */
    @Column(length = 50, nullable = false)
    private String productCode;

    /**
     * 价格组
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PriceGroup priceGroup;

    /**
     * 采购价
     */
    @Column(nullable = false)
    private Integer purchasePrice;

    /**
     * 移动平均价
     */
    @Column(nullable = false)
    private Integer dynamicAveragePrice;

    /**
     * 销售价
     */
    @Column(nullable = false)
    private Integer salePrice;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public PriceGroup getPriceGroup() {
        return priceGroup;
    }

    public void setPriceGroup(PriceGroup priceGroup) {
        this.priceGroup = priceGroup;
    }

    public Integer getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Integer purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Integer getDynamicAveragePrice() {
        return dynamicAveragePrice;
    }

    public void setDynamicAveragePrice(Integer dynamicAveragePrice) {
        this.dynamicAveragePrice = dynamicAveragePrice;
    }

    public Integer getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Integer salePrice) {
        this.salePrice = salePrice;
    }
}
