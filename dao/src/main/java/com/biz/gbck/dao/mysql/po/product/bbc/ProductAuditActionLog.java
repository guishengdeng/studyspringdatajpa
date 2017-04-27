package com.biz.gbck.dao.mysql.po.product.bbc;

import com.biz.gbck.enums.product.ProductAuditStatusEnum;
import com.biz.support.jpa.po.BaseEntity;
import javax.persistence.*;

/**
 * 商品审核日志
 *
 * @author david-liu
 * @date 2016年12月22日
 * @reviewer
 * @see
 */
//@Entity
//@Table(name = "pro_product_audit_action_log")
public class ProductAuditActionLog extends BaseEntity {

    private static final long serialVersionUID = -4914037328384619339L;

    @Column(columnDefinition = "TEXT")
    private String beforeAuditInfo;

    @Column(columnDefinition = "TEXT")
    private String afterAuditInfo;

    @Enumerated(value = EnumType.STRING)
    private ProductAuditStatusEnum beforeAuditStatus;

    @Enumerated(value = EnumType.STRING)
    private ProductAuditStatusEnum afterAuditStatus;

    @Column(nullable = false)
    private String auditorName;

    @Column
    private Long productId;

    public String getBeforeAuditInfo() {
        return beforeAuditInfo;
    }

    public void setBeforeAuditInfo(String beforeAuditInfo) {
        this.beforeAuditInfo = beforeAuditInfo;
    }

    public String getAfterAuditInfo() {
        return afterAuditInfo;
    }

    public void setAfterAuditInfo(String afterAuditInfo) {
        this.afterAuditInfo = afterAuditInfo;
    }

    public ProductAuditStatusEnum getBeforeAuditStatus() {
        return beforeAuditStatus;
    }

    public void setBeforeAuditStatus(ProductAuditStatusEnum beforeAuditStatus) {
        this.beforeAuditStatus = beforeAuditStatus;
    }

    public ProductAuditStatusEnum getAfterAuditStatus() {
        return afterAuditStatus;
    }

    public void setAfterAuditStatus(ProductAuditStatusEnum afterAuditStatus) {
        this.afterAuditStatus = afterAuditStatus;
    }

    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
