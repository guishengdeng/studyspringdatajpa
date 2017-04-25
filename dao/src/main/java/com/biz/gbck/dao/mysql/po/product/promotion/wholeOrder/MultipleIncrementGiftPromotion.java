package com.biz.gbck.dao.mysql.po.product.promotion.wholeOrder;

import com.biz.gbck.dao.mysql.po.product.master.Product;
import java.util.List;
import javax.persistence.*;

/**
 * 倍增满赠
 * <p>
 * 每满X元赠Y瓶酒（赠酒只能是同一种酒），例如：A商品每满100元赠1瓶B，一单中可多次叠加。
 * </p>
 *
 * Created by david-liu on 2017/04/24 09:35.
 */
@Entity
@Table(name = "pro_promotion_multi_incr_gift")
public class MultipleIncrementGiftPromotion extends WholeOrderPromotion {
    private static final long serialVersionUID = -6029276636640994770L;

    /**
     * 可参与促销的指定商品
     */
    @ManyToMany
    @JoinTable(name = "pro_promotion_multi_incr_gift_product",
            joinColumns = {@JoinColumn(name = "promotion_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")})
    private List<Product> products;

    /**
     * 赠品满足金额(每满x元)
     */
    @Column
    private Integer accountLimit;

    /**
     * 赠品
     */
    @JoinColumn(name = "product_id")
    @ManyToOne
    private Product product;

    /**
     * 赠品数量
     */
    @Column
    private Integer giftQuantity;

    /**
     * 赠品数量限制
     */
    @Column
    private Integer totalGiftQuantityLimit;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Integer getAccountLimit() {
        return accountLimit;
    }

    public void setAccountLimit(Integer accountLimit) {
        this.accountLimit = accountLimit;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getGiftQuantity() {
        return giftQuantity;
    }

    public void setGiftQuantity(Integer giftQuantity) {
        this.giftQuantity = giftQuantity;
    }

    public Integer getTotalGiftQuantityLimit() {
        return totalGiftQuantityLimit;
    }

    public void setTotalGiftQuantityLimit(Integer totalGiftQuantityLimit) {
        this.totalGiftQuantityLimit = totalGiftQuantityLimit;
    }
}
