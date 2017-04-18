package com.biz.gbck.dao.mysql.po.product;

import com.biz.gbck.dao.mysql.po.enums.CommonStatusEnum;
import com.biz.gbck.vo.product.backend.ISaleTagVo;
import com.biz.support.jpa.po.BaseEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 * 商品销售标签
 *
 * @author david-liu
 * @date 2016年12月15日
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
    @Column(length = 50, nullable = false)
    private String name;

    /**
     * 标签 Logo
     */
    @Column
    private String logo;

    /**
     * 显示顺序
     */
    @Column(nullable = false)
    private Integer idx;

    /**
     * 富文本
     */
    @Column(columnDefinition = "TEXT")
    private String rawHtml;

    /**
     * 描述
     */
    @Column
    private String description;

    /**
     * 商家 ID
     */
    @Column(nullable = false)
    private String vendorId;

    /**
     * 状态
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
     * 删除标识(true: 已删除, false: 未删除)
     */
    private Boolean deleteFlag = Boolean.FALSE;

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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public String getRawHtml() {
        return rawHtml;
    }

    public void setRawHtml(String rawHtml) {
        this.rawHtml = rawHtml;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CommonStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CommonStatusEnum status) {
        this.status = status;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public void fromVo(ISaleTagVo iSaleTagVo) {
        if (iSaleTagVo.getId() != null) {
            this.setId(Long.valueOf(iSaleTagVo.getId()));
        }
        this.setName(iSaleTagVo.getName());
        this.setIdx(iSaleTagVo.getIdx());
        this.setStatus(iSaleTagVo.getStatus());
        this.setDescription(iSaleTagVo.getDescription());
        this.setLogo(iSaleTagVo.getLogo());
        this.setRawHtml(iSaleTagVo.getRawHtml());
        this.setVendorId(iSaleTagVo.getVendorId());
    }
}
