package com.biz.gbck.dao.mysql.po.product;

import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.support.jpa.po.BaseEntity;
import java.io.Serializable;
import javax.persistence.*;

/**
 * 商品分类筛选条件条件项
 *
 * @author david-liu
 * @date 2016年12月14日
 * @reviewer
 * @see
 */
@Entity
@Table(name = "pro_product_filter_item")
public class ProductFilterItem extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -4227584101928089236L;

    /**
     * 商品过滤条件条件项归属的商品过滤条件
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "filter_id")
    private ProductFilter productFilter;

    /**
     * 商品首字母
     */
    @Column(length = 20)
    private String prefix;

    /**
     * 条件项显示名称
     */
    @Column(length = 20, nullable = false)
    private String label;

    /**
     * 条件项属性值
     */
    @Column(length = 20, nullable = false)
    private String value;

    /**
     * 显示顺序
     */
    @Column(nullable = false)
    private Integer idx;

    /**
     * 显示图片
     */
    @Column
    private String image;

    /**
     * 是否特殊显示
     */
    @Column
    private Boolean highlightShow = false;
    
    /**
     * 状态
     */
    @Column
    @Enumerated(value = EnumType.STRING)
    private CommonStatusEnum status;
    
    /**
     * 删除状态
     */
    @Column
    private Boolean deleteFlag = Boolean.FALSE;
    
    public ProductFilter getProductFilter() {
        return productFilter;
    }

    public void setProductFilter(ProductFilter productFilter) {
        this.productFilter = productFilter;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getHighlightShow() {
        return highlightShow;
    }

    public void setHighlightShow(Boolean highlightShow) {
        this.highlightShow = highlightShow;
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
    
}
