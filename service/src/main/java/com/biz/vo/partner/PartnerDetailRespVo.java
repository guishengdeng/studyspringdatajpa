package com.biz.vo.partner;

import com.biz.gbck.enums.partner.ApprovalStatus;

import java.sql.Timestamp;

/**
 * Created by haibin.tang on 2017/5/10.
 */
public class PartnerDetailRespVo {

    private String id;
    /**
     * 公司名称
     */
    private String name;
    /**
     * 主要联系人
     */
    private String corporateName;
    /**
     * 联系电话
     */
    private String mobile;

    /**
     * 经营品类
     */
    private String category;
    /**
     * 申请时间
     */
    private Timestamp createTimestamp;
    /**
     * 审核状态
     */
    private ApprovalStatus approvalStatus;
    /**
     * 操作时间
     */
    private Timestamp updateTimestamp;
    /**
     * 操作人
     */
    private String operator;

    /**
     * 审核时间
     */
    private Timestamp operatorTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public ApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(ApprovalStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public Timestamp getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(Timestamp updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Timestamp getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Timestamp operatorTime) {
        this.operatorTime = operatorTime;
    }
}
