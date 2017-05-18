package com.biz.gbck.dao.mysql.po.purchase;

import com.biz.gbck.enums.product.ProductUnit;
import com.biz.support.jpa.converter.ListStringConverter;

import javax.persistence.*;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * ProductItem
 *
 * @author lei
 * @date 2017年04月26日
 * @reviewer
 * @see
 */
@Embeddable
public class ProductItem {

    //二维码
    @Convert(converter = ListStringConverter.class)
    @Column(length = 800)
    private List<String> qrCodes  = newArrayList();

    /**
     * 商品Id
     */
    private Long productId;

    /**
     * 中台商品编号
     */
    @Column(length = 50)
    private String productCode;//商品编码

    /**
     * 商品名称
     */
    @Column(length = 50, nullable = false)
    private String name;

    /**
     * 规格
     */
    @Column(length = 50, nullable = false)
    private String standard;

    /**
     * 单位
     */
    @Column
    @Enumerated(EnumType.STRING)
    private ProductUnit unit;

    /**
     * 包装
     */
    @Column(length = 20)
    private String pack;

    /**
     * 商品图片
     */
    @Column(length = 100)
    private String logo;

    /**
     * 数量
     */
    @Column(nullable = false)
    private Integer quantity;

    /**
     * 价格
     */
    @Column(nullable = false)
    private Integer price;

    /**
     * 成本价
     */
    @Column(nullable = false)
    private Integer costPrice;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public ProductUnit getUnit() {
        return unit;
    }

    public void setUnit(ProductUnit unit) {
        this.unit = unit;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Integer costPrice) {
        this.costPrice = costPrice;
    }
}
