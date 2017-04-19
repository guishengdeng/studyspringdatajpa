package com.biz.gbck.vo.product.backend;

import com.biz.gbck.enums.CommonStatusEnum;

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
     * 销售标签 Logo
     */
    private String logo;

    /**
     * 销售标签显示顺序
     */
    private Integer idx;

    /**
     * 销售标签富文本信息
     */
    private String rawHtml;

    /**
     * 销售标签描述信息
     */
    private String description;

    /**
     * 销售标签状态
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
}
