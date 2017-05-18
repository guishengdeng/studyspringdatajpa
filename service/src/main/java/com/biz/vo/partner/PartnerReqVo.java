package com.biz.vo.partner;

import com.biz.gbck.enums.partner.ApprovalStatus;

/**
 * Created by haibin.tang on 2017/5/10.
 */
public class PartnerReqVo {
    private Long id;
    /**
     * 审核状态
     */
    private ApprovalStatus approvalStatus;
    /**
     * 公司名称
     */
    private String name;
    /**
     * 联系人
     */
    private String corporateName;
    /**
     * 提交审核开始时间
     */
    private String submitStartTime;
    /**
     * 提交审核结束时间
     */
    private String submitEndTime;
    /**
     * 操作开始时间
     */
    private String optionStartTime;
    /**
     * 操作结束时间
     */
    private String optionEndTime;
    /**
     * 审核意见
     */
    private String auditOpinion;
    /**
     * 操作人
     */
    private String operator;

    public ApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuditOpinion() {
        return auditOpinion;
    }

    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
    }

    public void setApprovalStatus(ApprovalStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCorporateName() {
        return corporateName;
    }

    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName;
    }

    public String getSubmitStartTime() {
        return submitStartTime;
    }

    public void setSubmitStartTime(String submitStartTime) {
        this.submitStartTime = submitStartTime;
    }

    public String getSubmitEndTime() {
        return submitEndTime;
    }

    public void setSubmitEndTime(String submitEndTime) {
        this.submitEndTime = submitEndTime;
    }

    public String getOptionStartTime() {
        return optionStartTime;
    }

    public void setOptionStartTime(String optionStartTime) {
        this.optionStartTime = optionStartTime;
    }

    public String getOptionEndTime() {
        return optionEndTime;
    }

    public void setOptionEndTime(String optionEndTime) {
        this.optionEndTime = optionEndTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
