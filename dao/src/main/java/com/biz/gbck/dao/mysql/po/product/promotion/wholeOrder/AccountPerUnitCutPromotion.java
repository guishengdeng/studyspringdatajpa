package com.biz.gbck.dao.mysql.po.product.promotion.wholeOrder;

import com.biz.gbck.dao.mysql.po.product.master.Product;
import javax.persistence.*;

/**
 * 倍增满减
 * <p>
 * 商品每满X元减Y元，例如：A商品每满100元立减10元，一单中可多次叠加。
 * </p>
 *
 * Created by david-liu on 2017/04/21 16:58.
 */
@Entity
@Table(name = "pro_promotion_account_per_unit_cut")
public class AccountPerUnitCutPromotion extends WholeOrderPromotion {
    private static final long serialVersionUID = 612747451003446872L;

    /**
     * 商品信息
     */
    @JoinColumn(name = "product_id")
    @ManyToOne
    private Product product;

    /**
     * 每满多少钱
     */
    @Column(nullable = false)
    private Integer perUnitAccount;

    /**
     * 减多少钱
     */
    @Column(nullable = false)
    private Integer cutAccount;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getPerUnitAccount() {
        return perUnitAccount;
    }

    public void setPerUnitAccount(Integer perUnitAccount) {
        this.perUnitAccount = perUnitAccount;
    }

    public Integer getCutAccount() {
        return cutAccount;
    }

    public void setCutAccount(Integer cutAccount) {
        this.cutAccount = cutAccount;
    }
}
