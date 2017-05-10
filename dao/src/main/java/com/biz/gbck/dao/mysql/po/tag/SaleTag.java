package com.biz.gbck.dao.mysql.po.tag;

import com.biz.gbck.dao.mysql.po.product.master.Product;
import com.biz.gbck.dao.mysql.po.product.meta.Category;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.vo.product.backend.ISaleTagVo;
import com.biz.support.jpa.po.BaseEntity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 * 商品销售标签
 *
 * @author lzz
 * @date 2017年5月4日
 * @reviewer
 * @see
 */
@Entity
@Table(name = "pro_sale_tag")
public class SaleTag extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 4832975674570352636L;

    /**
     * 标签名称
     */
    @Column(length = 150, nullable = false)
    private String name;

    /**
     * 显示顺序
     */
    @Column(nullable = false)
    private Integer idx;

    /**
     * 前台展示
     */
    @Column
    private String showName;

    /**
     * 标签
     */
    @Column
    private String tag;
    /**
     * 描述(后台备注)
     */
    @Column
    private String description;

    /**
     * 标签的启用或禁用状态
     */
    @Column
    @Convert(converter = SaleStatusEnum.Converter.class)
    private SaleStatusEnum saleStatus;

    /**
     * 状态（删除或没被删除）
     */
    @Column
    @Enumerated(value = EnumType.STRING)
    private CommonStatusEnum status;

    @ManyToMany
    @JoinTable(name = "pro_sale_tag_product",
            joinColumns = {@JoinColumn(name = "sale_tag_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")})
    private List<Product> products;


    /**
     * 分类
     */
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SaleStatusEnum getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(SaleStatusEnum saleStatus) {
        this.saleStatus = saleStatus;
    }

    public CommonStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CommonStatusEnum status) {
        this.status = status;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public void fromVo(ISaleTagVo iSaleTagVo) {
        if (iSaleTagVo.getId() != null) {
            this.setId(iSaleTagVo.getId());
        }
        this.setName(iSaleTagVo.getName());
        this.setIdx(iSaleTagVo.getIdx());
        this.setSaleStatus(iSaleTagVo.getSaleStatus());
        this.setStatus(iSaleTagVo.getStatus());
        this.setDescription(iSaleTagVo.getDescription());
        this.setShowName(iSaleTagVo.getShowName());
    }
}
