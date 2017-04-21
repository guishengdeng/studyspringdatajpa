package com.biz.gbck.dao.mysql.po.purchase;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.sql.Timestamp;

/**
 * 采购／退货 审核
 *
 * @author lei
 * @date 2017年04月21日
 * @reviewer
 * @see
 */
@Embeddable
public class PurchaseAudit {

    //审核人
    @Column(length = 50)
    private String auditor;

    //审核意见
    @Column(length = 100)
    private String auditContent;

    //审核时间
    private Timestamp auditTimestamp;

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getAuditContent() {
        return auditContent;
    }

    public void setAuditContent(String auditContent) {
        this.auditContent = auditContent;
    }

    public Timestamp getAuditTimestamp() {
        return auditTimestamp;
    }

    public void setAuditTimestamp(Timestamp auditTimestamp) {
        this.auditTimestamp = auditTimestamp;
    }
}
