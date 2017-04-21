package com.biz.gbck.dao.mysql.po.product.meta;

import com.biz.gbck.dao.mysql.po.product.master.Product;
import com.biz.support.jpa.po.BaseEntity;
import java.io.Serializable;
import javax.persistence.*;

/**
 * 商品统计数据 Po
 *
 * @author david-liu
 * @date 2016年12月19日
 * @reviewer
 * @see
 */
@Entity
@Table(name = "pro_product_statistic")
public class ProductStatistic extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 8959410362741036319L;

    /**
     * 商品数据
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    /**
     * 商品总销量
     */
    @Column
    private Integer totalSalesVolume;

    /**
     * 商品总点击量
     */
    @Column
    private Integer totalViewVolume;

    /**
     * 商品评价总数
     */
    @Column
    private Integer evaluationCount;

    /**
     * 商品评分(如果5分, 存50)
     */
    @Column
    private Integer productScore;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getTotalSalesVolume() {
        return totalSalesVolume;
    }

    public void setTotalSalesVolume(Integer totalSalesVolume) {
        this.totalSalesVolume = totalSalesVolume;
    }

    public Integer getTotalViewVolume() {
        return totalViewVolume;
    }

    public void setTotalViewVolume(Integer totalViewVolume) {
        this.totalViewVolume = totalViewVolume;
    }

    public Integer getEvaluationCount() {
        return evaluationCount;
    }

    public void setEvaluationCount(Integer evaluationCount) {
        this.evaluationCount = evaluationCount;
    }

    public Integer getProductScore() {
        return productScore;
    }

    public void setProductScore(Integer productScore) {
        this.productScore = productScore;
    }
}
