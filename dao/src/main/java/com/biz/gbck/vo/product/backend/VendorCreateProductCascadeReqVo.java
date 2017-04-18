package com.biz.gbck.vo.product.backend;

import com.biz.gbck.dao.mysql.po.enums.CommonStatusEnum;
import com.biz.gbck.vo.IRequestVo;
import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 商户创建商品配置(款型)请求 Vo
 *
 * @author david-liu
 * @date 2016年12月27日
 * @reviewer
 * @see
 */

public class VendorCreateProductCascadeReqVo implements Serializable, ICreateOrUpdateCascadeVo, IRequestVo {

    private static final long serialVersionUID = 7163128126339396670L;

    /**
     * 商品ID
     */
    private Long id;

    /**
     * 商家 ID
     */
    private Long vendorId;

    /**
     * 配置名称
     */
    private String name;

    /**
     * 分类 ID
     */
    @NotNull(message = "商品分类 ID 不能为空")
    private Long categoryId;

    /**
     * 商品扩展属性 Id
     */
    @NotEmpty(message = "商品扩展属性值 ID 集合不能为空")
    private List<Long> extendPropertyIds;


    /**
     * 被配置的商品ID
     */
    private List<Long> productIds;

    /**
     * 高亮显示商品属性值
     */
    private List<Long> highlightValueIds;

    /**
     * 高亮显示商品IDS
     */
    private List<Long> highlightProductIds;

    /**
     * 状态
     */
    private CommonStatusEnum status;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<Long> getExtendPropertyIds() {
        return this.extendPropertyIds;
    }

    public void setExtendPropertyIds(List<Long> extendPropertyIds) {
        this.extendPropertyIds = extendPropertyIds;
    }

    @Override
    public List<Long> getHighlightValueIds() {
        return this.highlightValueIds;
    }

    public void setHighlightValueIds(List<Long> highlightValueIds) {
        this.highlightValueIds = highlightValueIds;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public List<Long> getHighlightProductIds() {
        return highlightProductIds;
    }

    public void setHighlightProductIds(List<Long> highlightProductIds) {
        this.highlightProductIds = highlightProductIds;
    }

    public CommonStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CommonStatusEnum status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }
}
