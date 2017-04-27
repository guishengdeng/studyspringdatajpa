package com.biz.gbck.dao.mysql.po.product.bbc;

import com.biz.support.jpa.po.BaseEntity;
import javax.persistence.*;

/**
 * 该Po为组合商品明细的原始数据，主要用于商品数据导入时使用，正式的商品数据不用该Po
 * 并且在B组合商品导入时才会使用到该Po
 *
 * @author zhangcheng
 * @date 2017/3/15
 * @reviewer
 * @see
 */
//@Entity
//@Table(name = "pro_temporary_group_product_item")
public class TemporaryGroupProductItem extends BaseEntity {

    /**
     * 商品编码
     */
    @Column
    private String productCode;

    /**
     * 商品名称
     */
    @Column
    private String name;

    /**
     * 组合的数量
     */
    @Column
    private Integer quantity;

    /**
     * 最后更新时间
     */
    @Column
    private String lastModifyTime;

    /**
     * 组合商品主商品ID
     */
    @ManyToOne
    @JoinColumn(name = "temporaryGroupProduct_id")
    private TemporaryGroupProduct temporaryGroupProduct;

    public TemporaryGroupProduct getTemporaryGroupProduct() {
        return temporaryGroupProduct;
    }

    public void setTemporaryGroupProduct(TemporaryGroupProduct temporaryGroupProduct) {
        this.temporaryGroupProduct = temporaryGroupProduct;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(String lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }
}
