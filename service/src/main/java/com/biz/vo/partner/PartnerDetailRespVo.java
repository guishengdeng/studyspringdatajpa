package com.biz.vo.partner;

import com.biz.gbck.enums.partner.ApprovalStatus;

import java.sql.Timestamp;

/**
 * Created by haibin.tang on 2017/5/10.
 */
public class PartnerDetailRespVo {

    private Long id;
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
     * 注册资金
     */
    private String capital;

    /**
     * 经营规模
     */
    private String businessScale;

    /**
     * 意向销售区域
     */
    private String intentionSaleArea;

    /**
     * 目前销售区域
     */
    private String currentSaleArea;

    /**
     * 团队人数
     */
    private String teamSize;

    /**
     * 车辆数量
     */
    private String vehicleSize;

    /**
     * 仓库面积
     */
    private String storageSpace;

    /**
     * 客户数量
     */
    private String customerNumber;

    /**
     * 营业执照
     */
    private String businessLicense;

    /**
     * 酒水通行证
     */
    private String winePermit;
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
    private Timestamp auditTime;
    /**
     * 审核意见
     */
    private String auditOpinion;

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
        return auditTime;
    }

    public void setOperatorTime(Timestamp operatorTime) {
        this.auditTime = operatorTime;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getBusinessScale() {
        return businessScale;
    }

    public void setBusinessScale(String businessScale) {
        this.businessScale = businessScale;
    }

    public String getIntentionSaleArea() {
        return intentionSaleArea;
    }

    public void setIntentionSaleArea(String intentionSaleArea) {
        this.intentionSaleArea = intentionSaleArea;
    }

    public String getCurrentSaleArea() {
        return currentSaleArea;
    }

    public void setCurrentSaleArea(String currentSaleArea) {
        this.currentSaleArea = currentSaleArea;
    }

    public String getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(String teamSize) {
        this.teamSize = teamSize;
    }

    public String getVehicleSize() {
        return vehicleSize;
    }

    public void setVehicleSize(String vehicleSize) {
        this.vehicleSize = vehicleSize;
    }

    public String getStorageSpace() {
        return storageSpace;
    }

    public void setStorageSpace(String storageSpace) {
        this.storageSpace = storageSpace;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getWinePermit() {
        return winePermit;
    }

    public void setWinePermit(String winePermit) {
        this.winePermit = winePermit;
    }

    public Timestamp getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Timestamp auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditOpinion() {
        return auditOpinion;
    }

    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
    }
}
