package com.biz.gbck.vo.product.backend;

import com.biz.gbck.dao.mysql.po.tag.SaleStatusEnum;
import com.biz.gbck.enums.CommonStatusEnum;

/**
 * @author lzz
 * @usage
 * @reviewer
 * @since 2017/5/3
 */
public class CreateSaleTagVo implements ISaleTagVo {

    private static final long serialVersionUID = -1891330539302004612L;

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
     * 销售标签描述信息
     */
    private String description;

    /**
     * 标签的启用禁用状态
     */
    private SaleStatusEnum saleStatus;

    /**
     * 状态（删除状态）
     */
    private CommonStatusEnum status;

    /**
     * 商家 ID
     */
    private String vendorId;

    /**
     * 分类ID
     */
    private Long categoryId;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
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

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }
}
