package com.biz.gbck.dao.mysql.po.product;

import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.vo.product.backend.IBrandVo;
import com.biz.support.jpa.po.BaseEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 * 品牌 Po
 *
 * @author david-liu
 * @date 2016年12月14日
 * @reviewer
 * @see
 */
@Entity
@Table(name = "pro_brand")
public class Brand extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -8962025626015252979L;

    /**
     * 名称
     */
    @Column(nullable = false, length = 50)
    private String name;

    /**
     * 首字母
     */
    @Column(length = 5)
    private String prefix;

    /**
     * 英文名称
     */
    @Column(length = 50)
    private String nameEn;

    /**
     * 品牌编码
     */
    @Column(length = 50)
    private String brandCode;

    /**
     * 品牌 Logo
     */
    @Column
    private String logo;

    /**
     * 品牌描述
     */
    @Column(columnDefinition = "TEXT")
    private String description;

    /**
     * 品牌排序
     */
    @Column
    private Integer idx;

    /**
     * 状态
     */
    @Column
    @Enumerated(value = EnumType.STRING)
    private CommonStatusEnum status;

    /**
     * 品牌分类
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @OrderBy(value = "idx")
    @JoinTable(name = "pro_category_brand",
            joinColumns = {@JoinColumn(name = "brand_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")},
            uniqueConstraints = {@UniqueConstraint(columnNames = {"brand_id", "category_id"})})
    private List<Category> categories;

    /**
     * 店铺
     */
    //    @ManyToMany(fetch = FetchType.LAZY)
    //    @JoinTable(name = "ven_vendor_brand",
    //            joinColumns = {@JoinColumn(name = "brand_id", referencedColumnName = "id")},
    //            inverseJoinColumns = {@JoinColumn(name = "vendor_id", referencedColumnName = "id")},
    //            uniqueConstraints = {@UniqueConstraint(columnNames= {"brand_id" , "vendor_id"})})
    //    private List<Vendor> vendors;
    /**
     * 品牌商品
     */
    @OneToMany
    private List<Product> products;

    /**
     * 品牌商品审核记录
     */
    @OneToMany
    private List<ProductAudit> productAudits;

    /**
     * SEO 标题
     */
    @Column(length = 50)
    private String seoTitle;

    /**
     * SEO 关键字
     */
    @Column(length = 50)
    private String seoKeywords;

    /**
     * SEO 描述
     */
    @Column
    private String seoDescription;

    /**
     * 删除标识
     */
    @Column
    private Boolean deleteFlag = Boolean.FALSE;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getSeoTitle() {
        return seoTitle;
    }

    public void setSeoTitle(String seoTitle) {
        this.seoTitle = seoTitle;
    }

    public String getSeoKeywords() {
        return seoKeywords;
    }

    public void setSeoKeywords(String seoKeywords) {
        this.seoKeywords = seoKeywords;
    }

    public String getSeoDescription() {
        return seoDescription;
    }

    public void setSeoDescription(String seoDescription) {
        this.seoDescription = seoDescription;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public void fromVo(IBrandVo vo) {
        this.setStatus(vo.getStatus());
        this.setBrandCode(vo.getBrandCode());
        this.setDescription(vo.getDescription());
        this.setLogo(vo.getLogo());
        this.setName(vo.getName());
        this.setNameEn(vo.getNameEn());
        this.setSeoDescription(vo.getSeoDescription());
        this.setSeoKeywords(vo.getSeoKeywords());
        this.setSeoTitle(vo.getSeoTitle());
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<ProductAudit> getProductAudits() {
        return productAudits;
    }

    public void setProductAudits(List<ProductAudit> productAudits) {
        this.productAudits = productAudits;
    }
}
