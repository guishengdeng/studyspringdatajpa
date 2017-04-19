package com.biz.gbck.dao.mysql.po.product;

import com.biz.support.jpa.po.BaseEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2017/4/6
 */

@Entity
@Table(name = "pro_relevance_product")
public class RelevanceProduct extends BaseEntity implements Serializable {

    /**
     * 商品数据
     */
    @OneToOne
    private Product product;

    /**
     * 关联子商品
     */
    @ManyToMany
    @JoinTable(name = "pro_relevance", joinColumns = {@JoinColumn(name = "relevance_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")})
    private List<Product> childRelevanceProducts;


    public List<Product> getChildRelevanceProducts() {
        return childRelevanceProducts;
    }

    public void setChildRelevanceProducts(List<Product> childRelevanceProducts) {
        this.childRelevanceProducts = childRelevanceProducts;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
