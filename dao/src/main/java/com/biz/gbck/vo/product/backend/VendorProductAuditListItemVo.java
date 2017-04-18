package com.biz.gbck.vo.product.backend;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 商户被审核商品列表(包含等待审核和审核被拒绝的商品)
 *
 * @author david-liu
 * @date 2016年12月25日
 * @reviewer
 * @see
 */
public class VendorProductAuditListItemVo implements Serializable {

    private static final long serialVersionUID = 8666624818676912600L;

    /**
     * ID
     */
    private String id;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 提交审核日期
     */
    private Timestamp auditCreateTime;

    /**
     * 审核状态
     */
    private String auditStatus;

    /**
     * 审核信息
     */
    private String auditMessage;

    /**
     * 商品是否被平台端锁定
     */
    private Boolean locked;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Timestamp getAuditCreateTime() {
        return auditCreateTime;
    }

    public void setAuditCreateTime(Timestamp auditCreateTime) {
        this.auditCreateTime = auditCreateTime;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAuditMessage() {
        return auditMessage;
    }

    public void setAuditMessage(String auditMessage) {
        this.auditMessage = auditMessage;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }
}
