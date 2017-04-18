package com.biz.gbck.dao.mysql.po.product;

import com.biz.gbck.dao.mysql.po.enums.CommonStatusEnum;
import com.biz.support.jpa.po.BaseEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 * 商品搜索过滤条件
 *
 * @author david-liu
 * @date 2016年12月14日
 * @reviewer
 * @see
 */
@Entity
@Table(name = "pro_product_filter", uniqueConstraints = {@UniqueConstraint(columnNames = {"idx", "label", "field"})})
public class ProductFilter extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -5195659686354702868L;

    /**
     * 过滤条件归属分类
     */
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    /**
     * 显示顺序
     */
    @Column(nullable = false)
    private Integer idx;

    /**
     * 显示名称
     */
    @Column(length = 50, nullable = false)
    private String label;

    /**
     * 过滤字段
     */
    @Column(length = 50, nullable = false)
    private String field;

    /**
     * 是否显示首字母
     */
    @Column(nullable = false)
    private Boolean usePrefix;

    /**
     * 是否显示更多
     */
    @Column(nullable = false)
    private Boolean hasMore;

    /**
     * 是否显示图片
     */
    @Column(nullable = false)
    private Boolean showImage;

    /**
     * 状态
     */
    @Column
    @Enumerated(value = EnumType.STRING)
    private CommonStatusEnum status;

    /**
     * 商品过滤条件条件值
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy(value = "idx")
    private List<ProductFilterItem> filterItems;

    /**
     * 删除标识(默认是未删除)
     */
    @Column
    private Boolean deleteFlag = Boolean.FALSE;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Boolean getUsePrefix() {
        return usePrefix;
    }

    public void setUsePrefix(Boolean usePrefix) {
        this.usePrefix = usePrefix;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }

    public Boolean getShowImage() {
        return showImage;
    }

    public void setShowImage(Boolean showImage) {
        this.showImage = showImage;
    }

    public CommonStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CommonStatusEnum status) {
        this.status = status;
    }

    public List<ProductFilterItem> getFilterItems() {
        return filterItems;
    }

    public void setFilterItems(List<ProductFilterItem> filterItems) {
        this.filterItems = filterItems;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

}
