package com.biz.gbck.dao.mysql.po.product.master;

import com.biz.support.jpa.po.BaseEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "pro_relevant_product")
public class RelevantProduct extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 5750362855763754192L;

    /**
     * 商品数据
     */
    @OneToOne
    private Product product;

    /**
     * 关联子商品
     */
    @ManyToMany
    @JoinTable(name = "pro_relevant", joinColumns = {@JoinColumn(name = "relevant_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")})
    private List<Product> relevantProducts;


    public List<Product> getRelevantProducts() {
        return relevantProducts;
    }

    public void setRelevantProducts(List<Product> relevantProducts) {
        this.relevantProducts = relevantProducts;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
