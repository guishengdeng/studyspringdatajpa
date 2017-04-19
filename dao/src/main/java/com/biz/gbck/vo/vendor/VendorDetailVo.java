package com.biz.gbck.vo.vendor;

import com.biz.gbck.enums.vendor.AuditStatus;
import com.biz.gbck.enums.vendor.VendorOperationType;
import com.biz.gbck.enums.vendor.VendorRegisterType;
import com.biz.gbck.enums.vendor.VendorStatus;
import com.biz.gbck.enums.product.VendorTypeEnum;
import com.biz.gbck.vo.product.backend.IdNameVo;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author yanweijin
 * @date 2016/12/19
 */
public class VendorDetailVo implements Serializable {

    // ID
    private String id;

    // 商铺名称
    private String vendorName;

    //店铺编号
    private String vendorCode;

    //商铺图片
    private String logo;

    // 店主名
    private String owner;

    // 真实姓名
    private String ownerRealname;

    // 身份证
    private String ownerIdcard;

    // 手机号
    private String ownerMobile;

    // 邮箱
    private String ownerEmail;

    // 运营状态
    private VendorOperationType vendorOperationType;

    // 商铺类型
    private VendorTypeEnum vendorType;

    // 审核状态
    private AuditStatus auditStatus;

    //店铺状态
    private VendorStatus vendorStatus;

    // 有效期
    private Date expiryDate;

    // 审批备注
    private String vendorRemarks;

    // 企业名称
    private String enterpriseName;

    // 营业执照号
    private String businessLicenseNumber;

    // 税务登记证号
    private String taxRegistrationNumber;

    // 企业组织机构代码
    private String enterpriseOrganizationCode;

    // 法定代表人
    private String legalRepresentative;

    // 法人身份证
    private String legalRepresentativeId;

    // 公司负责人姓名
    private String companyRepresentativeName;

    // 公司负责人身份证
    private String companyRepresentativeId;

    // 公司负责人职位
    private String companyRepresentativeposition;

    // 公司联系电话
    private String companyContactNumber;

    // 公司地址
    private String companyAddress;

    // 注册资金
    private String registeredCapital;

    // 营业执照有效期
    private Date businessLicenseExpiryDate;

    // 营业执照经营范围
    private String businessLicenseManagementScope;

    //用户ID
    private String userId;

    //服务态度评分
    private Integer attitudeScore;

    //物流速度评分
    private Integer logisticsScore;

    //描述相符评分
    private Integer descriptionScore;

    //服务态度评论人数
    private Integer attitudeNum;

    //物流速度评分人数
    private Integer logisticsNum;

    //描述相符人数
    private Integer descriptionNum;

    //开户行
    private String openingBank;
    //开户账号
    private String openingAccount;

    private List<IdNameVo> brandOperation;

    private List<String> brandIds;

    private VendorRegisterType vendorRegisterType;


    public List<String> getBrandIds() {
        return brandIds;
    }

    public void setBrandIds(List<String> brandIds) {
        this.brandIds = brandIds;
    }

    public VendorRegisterType getVendorRegisterType() {
        return vendorRegisterType;
    }

    public void setVendorRegisterType(VendorRegisterType vendorRegisterType) {
        this.vendorRegisterType = vendorRegisterType;
    }

    public String getOpeningBank() {
        return openingBank;
    }

    public void setOpeningBank(String openingBank) {
        this.openingBank = openingBank;
    }

    public String getOpeningAccount() {
        return openingAccount;
    }

    public void setOpeningAccount(String openingAccount) {
        this.openingAccount = openingAccount;
    }


    public List<IdNameVo> getBrandOperation() {
        return brandOperation;
    }

    public void setBrandOperation(List<IdNameVo> brandOperation) {
        this.brandOperation = brandOperation;
    }

    public Integer getAttitudeNum() {
        return attitudeNum;
    }

    public void setAttitudeNum(Integer attitudeNum) {
        this.attitudeNum = attitudeNum;
    }

    public Integer getLogisticsNum() {
        return logisticsNum;
    }

    public void setLogisticsNum(Integer logisticsNum) {
        this.logisticsNum = logisticsNum;
    }

    public Integer getDescriptionNum() {
        return descriptionNum;
    }

    public void setDescriptionNum(Integer descriptionNum) {
        this.descriptionNum = descriptionNum;
    }

    public Integer getAttitudeScore() {
        return attitudeScore;
    }

    public void setAttitudeScore(Integer attitudeScore) {
        this.attitudeScore = attitudeScore;
    }

    public Integer getLogisticsScore() {
        return logisticsScore;
    }

    public void setLogisticsScore(Integer logisticsScore) {
        this.logisticsScore = logisticsScore;
    }

    public Integer getDescriptionScore() {
        return descriptionScore;
    }

    public void setDescriptionScore(Integer descriptionScore) {
        this.descriptionScore = descriptionScore;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwnerRealname() {
        return ownerRealname;
    }

    public void setOwnerRealname(String ownerRealname) {
        this.ownerRealname = ownerRealname;
    }

    public String getOwnerIdcard() {
        return ownerIdcard;
    }

    public void setOwnerIdcard(String ownerIdcard) {
        this.ownerIdcard = ownerIdcard;
    }

    public String getOwnerMobile() {
        return ownerMobile;
    }

    public void setOwnerMobile(String ownerMobile) {
        this.ownerMobile = ownerMobile;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public VendorOperationType getVendorOperationType() {
        return vendorOperationType;
    }

    public void setVendorOperationType(VendorOperationType vendorOperationType) {
        this.vendorOperationType = vendorOperationType;
    }

    public VendorTypeEnum getVendorType() {
        return vendorType;
    }

    public void setVendorType(VendorTypeEnum vendorType) {
        this.vendorType = vendorType;
    }

    public AuditStatus getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }


    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getVendorRemarks() {
        return vendorRemarks;
    }

    public void setVendorRemarks(String vendorRemarks) {
        this.vendorRemarks = vendorRemarks;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getBusinessLicenseNumber() {
        return businessLicenseNumber;
    }

    public void setBusinessLicenseNumber(String businessLicenseNumber) {
        this.businessLicenseNumber = businessLicenseNumber;
    }

    public String getTaxRegistrationNumber() {
        return taxRegistrationNumber;
    }

    public void setTaxRegistrationNumber(String taxRegistrationNumber) {
        this.taxRegistrationNumber = taxRegistrationNumber;
    }

    public String getEnterpriseOrganizationCode() {
        return enterpriseOrganizationCode;
    }

    public void setEnterpriseOrganizationCode(String enterpriseOrganizationCode) {
        this.enterpriseOrganizationCode = enterpriseOrganizationCode;
    }

    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
    }

    public String getLegalRepresentativeId() {
        return legalRepresentativeId;
    }

    public void setLegalRepresentativeId(String legalRepresentativeId) {
        this.legalRepresentativeId = legalRepresentativeId;
    }

    public String getCompanyRepresentativeName() {
        return companyRepresentativeName;
    }

    public void setCompanyRepresentativeName(String companyRepresentativeName) {
        this.companyRepresentativeName = companyRepresentativeName;
    }

    public String getCompanyRepresentativeId() {
        return companyRepresentativeId;
    }

    public void setCompanyRepresentativeId(String companyRepresentativeId) {
        this.companyRepresentativeId = companyRepresentativeId;
    }

    public String getCompanyRepresentativeposition() {
        return companyRepresentativeposition;
    }

    public void setCompanyRepresentativeposition(String companyRepresentativeposition) {
        this.companyRepresentativeposition = companyRepresentativeposition;
    }

    public String getCompanyContactNumber() {
        return companyContactNumber;
    }

    public void setCompanyContactNumber(String companyContactNumber) {
        this.companyContactNumber = companyContactNumber;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }


    public String getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(String registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public Date getBusinessLicenseExpiryDate() {
        return businessLicenseExpiryDate;
    }

    public void setBusinessLicenseExpiryDate(Date businessLicenseExpiryDate) {
        this.businessLicenseExpiryDate = businessLicenseExpiryDate;
    }

    public String getBusinessLicenseManagementScope() {
        return businessLicenseManagementScope;
    }

    public void setBusinessLicenseManagementScope(String businessLicenseManagementScope) {
        this.businessLicenseManagementScope = businessLicenseManagementScope;
    }

    public VendorStatus getVendorStatus() {
        return vendorStatus;
    }

    public void setVendorStatus(VendorStatus vendorStatus) {
        this.vendorStatus = vendorStatus;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

}
