package com.biz.gbck.vo.order.req;

import com.biz.gbck.enums.order.AuditStatus;
import com.biz.gbck.vo.user.BaseRequestVo;

import java.sql.Timestamp;

/**
 * 退货单审核请求Vo
 * Created by liqi1 on 2017/5/19.
 */
public class OrderReturnAuditReqVo  {

    /**
     * 退货单号
     */
    private String returnCode;

    //审核人
    private String auditor;

    //审核内容
    private String auditContent;

    /**
     * 退款审核状态
     */
    private AuditStatus auditStatus;

    //审核拒绝原因
    private String rejectReason;

    /**
     * 是否立即退款
     */
    private String isRefundNow;

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

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

    public String getIsRefundNow() {
        return isRefundNow;
    }

    public void setIsRefundNow(String isRefundNow) {
        this.isRefundNow = isRefundNow;
    }
}
