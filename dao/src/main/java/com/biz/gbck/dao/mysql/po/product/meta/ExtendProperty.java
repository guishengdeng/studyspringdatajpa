package com.biz.gbck.dao.mysql.po.product.meta;

import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.support.jpa.po.BaseEntity;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 商品扩展属性值
 *
 * @author david-liu
 * @date 2016年12月14日
 * @reviewer
 * @see
 */
@Entity
@Table(name = "pro_product_property")
public class ExtendProperty extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -4819662894291514366L;

    /**
     * 属性值
     */
    @Column(length = 50, nullable = false)
    private String value;

    /**
     * 状态
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CommonStatusEnum status;

    /**
     * 显示顺序
     */
    @Column(nullable = false)
    private Integer idx;

    /**
     * 商品属性值归属的商品扩展属性
     */
    @ManyToOne
    @JoinColumn(name = "product_extend_id", nullable = false)
    private ProductExtend productExtend;

    /**
     * 属性值的删除标示  默认未删除
     */
    @Column
    private Boolean deleteFlag = Boolean.FALSE;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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

    public ProductExtend getProductExtend() {
        return productExtend;
    }

    public void setProductExtend(ProductExtend productExtend) {
        this.productExtend = productExtend;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return Objects.equals(this.getValue(), ((ExtendProperty) o).getValue());
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + value.hashCode();
        return result;
    }
}
