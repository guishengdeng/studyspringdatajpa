package com.biz.gbck.vo.product.backend;

import com.biz.gbck.enums.product.SaleStatusEnum;
import java.io.Serializable;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2017/3/27
 */
public class ProductGroupOnSaleVo implements Serializable {

    private static final long serialVersionUID = -4552933976588845479L;

    /**
     * 商品ID
     */
    private Long id;

    /**
     * 商品价格
     */
    private Double price;

    /**
     * 商品上下架状态
     */
    private SaleStatusEnum saleStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public SaleStatusEnum getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(SaleStatusEnum saleStatus) {
        this.saleStatus = saleStatus;
    }
}
