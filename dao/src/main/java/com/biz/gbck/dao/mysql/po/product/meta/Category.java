package com.biz.gbck.dao.mysql.po.product;

import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.vo.product.backend.ICategoryVo;
import com.biz.support.jpa.po.BaseEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 * 分类 Po
 *
 * @author david-liu
 * @date 2016年12月14日
 * @reviewer
 * @see
 */
@Entity
@Table(name = "pro_category")
public class Category extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 5027873885402573782L;

    /**
     * 分类名称
     */
    @Column(nullable = false, length = 50)
    private String name;

    /**
     * 分类排序
     */
    @Column
    private Integer idx;

    /**
     * 分类图标
     */
    @Column
    private String logo;

    /**
     * 状态
     */
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private CommonStatusEnum status;

    /**
     * 父分类
     */
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    /**
     * 子分类
     */
    @OrderBy(value = "idx")
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Category> children;

    /**
     * 品牌
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @OrderBy(value = "idx")
    @JoinTable(name = "pro_category_brand",
            joinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "brand_id", referencedColumnName = "id")},
            uniqueConstraints = {@UniqueConstraint(columnNames = {"category_id", "brand_id"})})
    private List<Brand> brands;

    /**
     * 商品扩展属性
     */
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy(value = "idx")
    private List<ProductExtend> productExtends;

    /**
     * 分类筛选条件
     */
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy(value = "idx")
    private List<ProductFilter> productFilters;

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
     * 删除标识(默认是未删除)
     */
    @Column
    private Boolean deleteFlag = Boolean.FALSE;

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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public CommonStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CommonStatusEnum status) {
        this.status = status;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }

    public List<Brand> getBrands() {
        return brands;
    }

    public void setBrands(List<Brand> brands) {
        this.brands = brands;
    }

    public List<ProductExtend> getProductExtends() {
        return productExtends;
    }

    public void setProductExtends(List<ProductExtend> productExtends) {
        this.productExtends = productExtends;
    }

    public List<ProductFilter> getFilters() {
        return productFilters;
    }

    public void setFilters(List<ProductFilter> filters) {
        this.productFilters = filters;
    }

    public List<ProductFilter> getProductFilters() {
        return productFilters;
    }

    public void setProductFilters(List<ProductFilter> productFilters) {
        this.productFilters = productFilters;
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

    public void setDeleteFlag(Boolean delete) {
        deleteFlag = delete;
    }

    /**
     * 通过 VO 获取 Po(未设置 ID 和关联对象)
     *
     * @param categoryVo Vo
     */
    public void fromVo(ICategoryVo categoryVo) {
        this.setName(categoryVo.getName());
        this.setLogo(categoryVo.getLogo());
        this.setStatus(categoryVo.getStatus());
        this.setSeoTitle(categoryVo.getSeoTitle());
        this.setSeoKeywords(categoryVo.getSeoKeywords());
        this.setSeoDescription(categoryVo.getSeoDescription());
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", idx=" + idx +
                ", logo='" + logo + '\'' +
                ", status=" + status +
                ", parent=" + parent +
                ", children=" + children +
                ", brands=" + brands +
                ", productExtends=" + productExtends +
                ", productFilters=" + productFilters +
                ", seoTitle='" + seoTitle + '\'' +
                ", seoKeywords='" + seoKeywords + '\'' +
                ", seoDescription='" + seoDescription + '\'' +
                ", deleteFlag=" + deleteFlag +
                '}';
    }
}
