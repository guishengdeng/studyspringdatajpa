package com.biz.gbck.dao.mysql.po.product.bbc;

import com.biz.support.jpa.po.BaseEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

//@Entity
//@Table(name = "pro_relevant_product")
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
    private List<Product> relevntProductList;


    public List<Product> getRelevntProductList() {
        return relevntProductList;
    }

    public void setRelevntProductList(List<Product> relevntProductList) {
        this.relevntProductList = relevntProductList;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
