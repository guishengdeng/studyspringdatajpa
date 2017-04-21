package com.biz.gbck.dao.mysql.po.order;

import com.biz.gbck.enums.order.AuditStatus;
import com.biz.support.jpa.po.BaseEntity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 退货审核
 *
 * @author lei
 * @date 2017年04月20日
 * @reviewer
 * @see
 */
@Embeddable
public class OrderRefundAudit extends BaseEntity {

    private static final long serialVersionUID = 4580991351968768185L;

    //审核人
    @Column(length = 50)
    private String auditor;

    //审核内容
    @Column(length = 100)
    private String auditContent;

    /**
     * 退款审核状态
     */
    @Convert(converter = AuditStatus.Converter.class)
    private AuditStatus auditStatus = AuditStatus.WAITING_AUDIT;

    //审核拒绝原因
    @Column(length = 150)
    private String rejectReason;

    //审核时间
    private Timestamp auditTimestamp;

    //退款时间
    private Timestamp refundTimestamp;

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

    public AuditStatus getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public Timestamp getAuditTimestamp() {
        return auditTimestamp;
    }

    public void setAuditTimestamp(Timestamp auditTimestamp) {
        this.auditTimestamp = auditTimestamp;
    }

    public Timestamp getRefundTimestamp() {
        return refundTimestamp;
    }

    public void setRefundTimestamp(Timestamp refundTimestamp) {
        this.refundTimestamp = refundTimestamp;
    }
}
