package com.biz.gbck.dao.mysql.po.product.meta;

import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.support.jpa.po.BaseEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 商品扩展属性(商品规格参数)
 *
 * @author david-liu
 * @date 2016年12月14日
 * @reviewer
 * @see
 */
@Entity
@Table(name = "pro_product_extend")
public class ProductExtend extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -3827374459002228845L;

    /**
     * 商品扩展属性名称
     */
    @Column(length = 50)
    @NotNull(message = "名字不能为空")
    @NotBlank(message = "名字不能为空")
    private String name;

    /**
     * 商品分类
     */
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    /**
     * 显示顺序
     */
    @Column(nullable = false)
    private Integer idx;

    /**
     * 状态
     */
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private CommonStatusEnum status;

    /**
     * 扩展属性属性值
     */
    @OneToMany(mappedBy = "productExtend", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy(value = "idx")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<ExtendProperty> properties;

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

    public CommonStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CommonStatusEnum status) {
        this.status = status;
    }

    public List<ExtendProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<ExtendProperty> properties) {
        this.properties = properties;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

}
