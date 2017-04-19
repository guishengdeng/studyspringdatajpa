package com.biz.gbck.vo.product.backend;

import java.io.Serializable;

/**
 * 平台商品审核列表项 Vo
 *
 * @author david-liu
 * @date 2016年12月25日
 * @reviewer
 * @see
 */
public class PlatformProductAuditListItemVo implements Serializable {

    private static final long serialVersionUID = 3439627477221337164L;

    /**
     * 商品审核记录 ID
     */
    private String id;

    /**
     * 商家名称
     */
    private String vendorName;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 所属分类
     */
    private String categoryName;

    /**
     * 商品品牌
     */
    private String brandName;

    /**
     * 审核状态
     */
    private String auditStatus;

    /**
     * 提交时间
     */
    private String updateTimeStamp;

    public String getUpdateTimeStamp() {
        return updateTimeStamp;
    }

    public void setUpdateTimeStamp(String updateTimeStamp) {
        this.updateTimeStamp = updateTimeStamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }
}
