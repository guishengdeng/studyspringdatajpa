package com.biz.gbck.dao.mysql.po.org;

import com.biz.gbck.dao.mysql.po.geo.CityPo;
import com.biz.gbck.dao.mysql.po.geo.ProvincePo;
import com.biz.gbck.dao.mysql.po.security.Admin;
import com.biz.gbck.enums.partner.ApprovalStatus;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * 合伙人(合作者)
 *
 * @author: liubin
 * @date 4/20/17 09:19
 */
@Entity
@Table(name = "org_partner")
public class PartnerPo extends Company{

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "provinceId")
    private ProvincePo province;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "cityId")
    private CityPo city;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "platform_id")
    private PlatformPo platform;

    @OneToMany(mappedBy = "partner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ShopPo> shopPos;

    /**
     * 联系人2
     */
    @Column(length = 40) private String contactName;

    /**
     * 联系电话2
     */
    @Column(length = 40)
    private String mobile2;

    @Column
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus = ApprovalStatus.UNDER_REVIEW;
    /**
     * 审核时间
     */
    private Timestamp auditTime;
    /**
     * 审核意见
     */
    private String auditOpinion;
    /**
     * 经营品类
     */
    private String category;

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
     * 操作人
     */
    private String operator;

    public ProvincePo getProvince() {
        return province;
    }

    public void setProvince(ProvincePo province) {
        this.province = province;
    }

    public CityPo getCity() {
        return city;
    }

    public void setCity(CityPo city) {
        this.city = city;
    }

    public PlatformPo getPlatform() {
        return platform;
    }

    public void setPlatform(PlatformPo platform) {
        this.platform = platform;
    }

    public List<ShopPo> getShopPos() {
        return shopPos;
    }

    public void setShopPos(List<ShopPo> shopPos) {
        this.shopPos = shopPos;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getMobile2() {
        return mobile2;
    }

    public void setMobile2(String mobile2) {
        this.mobile2 = mobile2;
    }

    public ApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(ApprovalStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
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
