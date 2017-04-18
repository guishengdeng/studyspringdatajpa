package com.biz.gbck.dao.mysql.po.product;

import com.biz.gbck.dao.mysql.po.enums.CommonStatusEnum;
import com.biz.support.jpa.po.BaseEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 商品规格参数配置
 *
 * @author david-liu
 * @date 2016年12月16日
 * @reviewer
 * @see
 */
@Entity
@Table(name = "pro_product_cascade", uniqueConstraints = {@UniqueConstraint(columnNames = {"source_product_id", "vendor_id"})})
public class ProductCascade extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -7016215207841303508L;

    /**
     * 被配置商品
     */
    @JoinColumn(name = "source_product_id")
    @OneToOne
    private Product sourceProduct;

    /**
     * 商家 ID
     */
    @Column(nullable = false, name = "vendor_id")
    private Long vendorId;

    /**
     * 规格配置名称
     */
    @Column(length = 50)
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    /**
     * 商品配置属性
     */
    @ManyToMany
    @JoinTable(name = "pro_product_cascade_extend_properties",
            joinColumns = {@JoinColumn(name = "cascade_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "extend_property_id", referencedColumnName = "id")}
    )
    private List<ExtendProperty> extendProperties;

    /**
     * 配置目标
     */
    @ManyToMany
    @JoinTable(name = "pro_product_cascade_product",
            joinColumns = {@JoinColumn(name = "cascade_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")})
    private List<Product> products;

    /**
     * 高亮显示配置目标
     */
    @ManyToMany
    @JoinTable(name = "pro_product_cascade_product_highlight",
            joinColumns = {@JoinColumn(name = "cascade_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")})
    private List<Product> highlightProducts;

    /**
     * 特殊显示属性
     */
    @ManyToMany
    @JoinTable(name = "pro_product_cascade_highlight_extend_properties",
            joinColumns = {@JoinColumn(name = "cascade_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "extend_property_id", referencedColumnName = "id")}
    )
    private List<ExtendProperty> highlightValues;

    /**
     * 状态
     */
    @Column
    @Enumerated(value = EnumType.STRING)
    private CommonStatusEnum status;

    /**
     * 删除标识符
     */
    @Column
    private Boolean deleteFlag = Boolean.FALSE;


    public Product getSourceProduct() {
        return sourceProduct;
    }

    public void setSourceProduct(Product sourceProduct) {
        this.sourceProduct = sourceProduct;
    }

    public List<ExtendProperty> getExtendProperties() {
        return extendProperties;
    }

    public void setExtendProperties(List<ExtendProperty> extendProperties) {
        this.extendProperties = extendProperties;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<ExtendProperty> getHighlightValues() {
        return highlightValues;
    }

    public void setHighlightValues(List<ExtendProperty> highlightValues) {
        this.highlightValues = highlightValues;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CommonStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CommonStatusEnum status) {
        this.status = status;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public List<Product> getHighlightProducts() {
        return highlightProducts;
    }

    public void setHighlightProducts(List<Product> highlightProducts) {
        this.highlightProducts = highlightProducts;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
