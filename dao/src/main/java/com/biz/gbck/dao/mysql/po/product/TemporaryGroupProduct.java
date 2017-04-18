package com.biz.gbck.dao.mysql.po.product;

import com.biz.support.jpa.converter.ListStringConverter;
import com.biz.support.jpa.po.BaseEntity;
import java.util.List;
import javax.persistence.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 该Po为组合商品原始数据，主要用于商品数据导入时使用，正式的商品数据不用该Po
 * 并且在B组合商品导入时才会使用到该Po
 * @author zhangcheng
 * @date 2017/3/15
 * @reviewer
 * @see
 */
@Entity
@Table(name = "pro_temporary_group_product")
public class TemporaryGroupProduct extends BaseEntity {

    /**
     * 组合商品中台编号
     */
    @Column(nullable = false)
    private String mixProductCode;

    /**
     * 组合商品描述
     */
    @Column(nullable = false)
    private String mixProductDescription;

    /**
     * 组合商品定价百分比
     */
    @Column
    private String mixProductPercentage;

    /**
     * 组合商品明细
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "pro_temporary_group_product_group_item",
            joinColumns = {@JoinColumn(name = "pro_temporary_group_product_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "pro_temporary_group_product_item_id", referencedColumnName = "id")})
    private List<TemporaryGroupProductItem> items;

    /**
     * 组合商品的价格
     */
    @Column
    private String mixPrice;

    /**
     * 组合商品Logo
     */
    @Column
    private String logo;

    /**
     * 组合商品图片（商品轮播图）
     */
    @Column(columnDefinition = "TEXT")
    @Convert(converter = ListStringConverter.class)
    private List<String> productImages;

    /**
     * 组合介绍图片
     */
    @Column(columnDefinition = "TEXT")
    @Convert(converter = ListStringConverter.class)
    private List<String> introImages;

    /**
     * 组合商品的分类名称
     */
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMixProductCode() {
        return mixProductCode;
    }

    public void setMixProductCode(String mixProductCode) {
        this.mixProductCode = mixProductCode;
    }

    public String getMixProductDescription() {
        return mixProductDescription;
    }

    public void setMixProductDescription(String mixProductDescription) {
        this.mixProductDescription = mixProductDescription;
    }

    public String getMixProductPercentage() {
        return mixProductPercentage;
    }

    public void setMixProductPercentage(String mixProductPercentage) {
        this.mixProductPercentage = mixProductPercentage;
    }

    public List<TemporaryGroupProductItem> getItems() {
        return items;
    }

    public void setItems(List<TemporaryGroupProductItem> items) {
        this.items = items;
    }

    public String getMixPrice() {
        return mixPrice;
    }

    public void setMixPrice(String mixPrice) {
        this.mixPrice = mixPrice;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<String> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<String> productImages) {
        this.productImages = productImages;
    }

    public List<String> getIntroImages() {
        return introImages;
    }

    public void setIntroImages(List<String> introImages) {
        this.introImages = introImages;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
