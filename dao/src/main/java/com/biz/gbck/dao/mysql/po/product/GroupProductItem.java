package com.biz.gbck.dao.mysql.po.product;

import com.biz.support.jpa.po.BaseEntity;
import java.io.Serializable;
import javax.persistence.*;

/**
 * 组合商品(商品项)
 *
 * @author david-liu
 * @date 2017年01月23日
 * @reviewer
 */
@Entity
@Table(name = "pro_group_product_item")
public class GroupProductItem extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 7770078323137367331L;

    /**
     * 组合商品主商品ID
     */
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private GroupProduct parent;

    /**
     * 商品项商品
     */
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    /**
     * 数量
     */
    @Column
    private Integer quantity;

    public GroupProduct getParent() {
        return parent;
    }

    public void setParent(GroupProduct parent) {
        this.parent = parent;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "GroupProductItem{" +
                "parent=" + parent +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
