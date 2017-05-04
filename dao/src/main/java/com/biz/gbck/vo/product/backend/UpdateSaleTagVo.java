package com.biz.gbck.vo.product.backend;

import com.biz.gbck.enums.CommonStatusEnum;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2016/12/23
 */
public class UpdateSaleTagVo implements ISaleTagVo {

    private static final long serialVersionUID = 340105508162783184L;

    /**
     * ID
     */
    private Long id;

    /**
     * 销售标签名称
     */
    private String name;

    /**
     * 前台展示
     */
    private String showName;

    /**
     * 标签
     */
    private String tag;
    /**
     * 销售标签显示顺序
     */
    private Integer idx;


    /**
     * 描述(后台备注)
     */
    private String description;

    /**
     * 启用状态
     */
    private String saleStatus;

    /**
     * 状态(是否删除状态)
     */
    private CommonStatusEnum status;

    /**
     * 商家 ID
     */
    private String vendorId;

    /**
     * 分类Id
     */
    private Long categoryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    @Override
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

    public String getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(String saleStatus) {
        this.saleStatus = saleStatus;
    }

}
