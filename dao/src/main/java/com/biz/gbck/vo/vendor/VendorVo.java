package com.biz.gbck.vo.vendor;

import com.biz.gbck.dao.mysql.po.enums.vendor.AuditStatus;
import com.biz.gbck.dao.mysql.po.enums.vendor.VendorOperationType;
import com.biz.gbck.dao.mysql.po.enums.vendor.VendorStatus;
import com.biz.gbck.enums.product.VendorTypeEnum;
import com.biz.gbck.vo.IRequestVo;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class VendorVo implements IRequestVo {

    // ID
    private String id;

    // 商铺名称
    private String vendorName;

    // 商铺编号
    private String vendorCode;

    // 商铺logo 图片地址
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

    // 开户行
    private String openingBank;

    // 开户行帐号
    private String OpeningAccount;

    // 密保问题
    private String securityQuestion;

    // 密码答案
    private String secretAnswer;

    // 更新时间
    private Timestamp updateTime;

    // 申请时间
    private Timestamp createTime;
    // 有效期
    private String expiryDate;

    // 运营状态
    private VendorOperationType vendorOperationType;

    // 商铺类型
    private VendorTypeEnum vendorType;

    // 审核状态
    private AuditStatus auditStatus;

    // 店铺状态
    private VendorStatus vendorStatus;

    // 店铺装饰
    private VendorDecorationVo decoration;

    // 店铺备注
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
    private Long RegisteredCapital;

    // 营业执照有效期
    private Date businessLicenseExpiryDate;

    // 营业执照经营范围
    private String businessLicenseManagementScope;

    // 经营品牌
    private String brandOperation;

    // 营业执照复印件
    private String businessLicensePhotoCopy;

    // 营业执照有效期开始时间
    private Date businessLicenseStartTime;

    // 营业执照有效期结束时间
    private Date businessLicenseEndTime;

    // 组织机构代码证复印件
    private String enterpriseOrganizationCodePhotoCopy;

    // 组织机构代码证有效期开始时间
    private Date enterpriseOrganizationCodeStartTime;

    // 组织机构代码证有效期结束时间
    private Date enterpriseOrganizationCodeEndTime;

    // 税务登记证复印件
    private String taxRegistrationPhotoCopy;

    // 税务登记证有效期开始时间
    private Date taxRegistrationStartTime;

    // 税务登记证有效期结束时间
    private Date taxRegistrationEndTime;

    // 食品经营许可证复印件
    private String foodBusinessLicensePhotoCopy;

    // 食品经营许可证有效期开始时间
    private Date foodBusinessLicenseStartTime;

    // 食品经营许可证有效期结束时间
    private Date foodBusinessLicenseEndTime;

    // 商标注册复印件
    private String trademarkRegistrationPhotoCopy;

    // 商标注册有效期开始时间
    private Date trademarkRegistrationStartTime;

    // 商标注册有效期结束时间
    private Date trademarkRegistrationEndTime;

    // 开户许可证复印件
    private String openingPermitPhotoCopy;

    // 开户许可证有效期开始时间
    private Date openingPermitStartTime;

    // 开户许可证有效期结束时间
    private Date openingPermitEndTime;

    ///店铺统计相关
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

    //描述相符评分
    private Integer descriptionNum;

    //是否收藏
    private boolean favoriteFlag = false;
    //品牌名
    private List<String> brandNames;

    private List<String> brandId;

    private List<String> categoryId;

    private List<String> brandVendorRelationId;
    //页面展示品牌
    private List<VendorBrandVo> vendorBrandVo;


    public List<String> getBrandVendorRelationId() {
        return brandVendorRelationId;
    }

    public void setBrandVendorRelationId(List<String> brandVendorRelationId) {
        this.brandVendorRelationId = brandVendorRelationId;
    }

    public List<VendorBrandVo> getVendorBrandVo() {
        return vendorBrandVo;
    }

    public void setVendorBrandVo(List<VendorBrandVo> vendorBrandVo) {
        this.vendorBrandVo = vendorBrandVo;
    }

    public List<String> getBrandId() {
        return brandId;
    }

    public void setBrandId(List<String> brandId) {
        this.brandId = brandId;
    }

    public List<String> getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(List<String> categoryId) {
        this.categoryId = categoryId;
    }

    public List<String> getBrandNames() {
        return brandNames;
    }

    public void setBrandNames(List<String> brandNames) {
        this.brandNames = brandNames;
    }

    public String getBusinessLicensePhotoCopy() {
        return businessLicensePhotoCopy;
    }

    public void setBusinessLicensePhotoCopy(String businessLicensePhotoCopy) {
        this.businessLicensePhotoCopy = businessLicensePhotoCopy;
    }

    public Date getBusinessLicenseStartTime() {
        return businessLicenseStartTime;
    }

    public void setBusinessLicenseStartTime(Date businessLicenseStartTime) {
        this.businessLicenseStartTime = businessLicenseStartTime;
    }

    public Date getBusinessLicenseEndTime() {
        return businessLicenseEndTime;
    }

    public void setBusinessLicenseEndTime(Date businessLicenseEndTime) {
        this.businessLicenseEndTime = businessLicenseEndTime;
    }

    public String getEnterpriseOrganizationCodePhotoCopy() {
        return enterpriseOrganizationCodePhotoCopy;
    }

    public void setEnterpriseOrganizationCodePhotoCopy(String enterpriseOrganizationCodePhotoCopy) {
        this.enterpriseOrganizationCodePhotoCopy = enterpriseOrganizationCodePhotoCopy;
    }

    public Date getEnterpriseOrganizationCodeStartTime() {
        return enterpriseOrganizationCodeStartTime;
    }

    public void setEnterpriseOrganizationCodeStartTime(Date enterpriseOrganizationCodeStartTime) {
        this.enterpriseOrganizationCodeStartTime = enterpriseOrganizationCodeStartTime;
    }

    public Date getEnterpriseOrganizationCodeEndTime() {
        return enterpriseOrganizationCodeEndTime;
    }

    public void setEnterpriseOrganizationCodeEndTime(Date enterpriseOrganizationCodeEndTime) {
        this.enterpriseOrganizationCodeEndTime = enterpriseOrganizationCodeEndTime;
    }

    public String getTaxRegistrationPhotoCopy() {
        return taxRegistrationPhotoCopy;
    }

    public void setTaxRegistrationPhotoCopy(String taxRegistrationPhotoCopy) {
        this.taxRegistrationPhotoCopy = taxRegistrationPhotoCopy;
    }

    public Date getTaxRegistrationStartTime() {
        return taxRegistrationStartTime;
    }

    public void setTaxRegistrationStartTime(Date taxRegistrationStartTime) {
        this.taxRegistrationStartTime = taxRegistrationStartTime;
    }

    public Date getTaxRegistrationEndTime() {
        return taxRegistrationEndTime;
    }

    public void setTaxRegistrationEndTime(Date taxRegistrationEndTime) {
        this.taxRegistrationEndTime = taxRegistrationEndTime;
    }

    public String getFoodBusinessLicensePhotoCopy() {
        return foodBusinessLicensePhotoCopy;
    }

    public void setFoodBusinessLicensePhotoCopy(String foodBusinessLicensePhotoCopy) {
        this.foodBusinessLicensePhotoCopy = foodBusinessLicensePhotoCopy;
    }

    public Date getFoodBusinessLicenseStartTime() {
        return foodBusinessLicenseStartTime;
    }

    public void setFoodBusinessLicenseStartTime(Date foodBusinessLicenseStartTime) {
        this.foodBusinessLicenseStartTime = foodBusinessLicenseStartTime;
    }

    public Date getFoodBusinessLicenseEndTime() {
        return foodBusinessLicenseEndTime;
    }

    public void setFoodBusinessLicenseEndTime(Date foodBusinessLicenseEndTime) {
        this.foodBusinessLicenseEndTime = foodBusinessLicenseEndTime;
    }

    public String getTrademarkRegistrationPhotoCopy() {
        return trademarkRegistrationPhotoCopy;
    }

    public void setTrademarkRegistrationPhotoCopy(String trademarkRegistrationPhotoCopy) {
        this.trademarkRegistrationPhotoCopy = trademarkRegistrationPhotoCopy;
    }

    public Date getTrademarkRegistrationStartTime() {
        return trademarkRegistrationStartTime;
    }

    public void setTrademarkRegistrationStartTime(Date trademarkRegistrationStartTime) {
        this.trademarkRegistrationStartTime = trademarkRegistrationStartTime;
    }

    public Date getTrademarkRegistrationEndTime() {
        return trademarkRegistrationEndTime;
    }

    public void setTrademarkRegistrationEndTime(Date trademarkRegistrationEndTime) {
        this.trademarkRegistrationEndTime = trademarkRegistrationEndTime;
    }

    public String getOpeningPermitPhotoCopy() {
        return openingPermitPhotoCopy;
    }

    public void setOpeningPermitPhotoCopy(String openingPermitPhotoCopy) {
        this.openingPermitPhotoCopy = openingPermitPhotoCopy;
    }

    public Date getOpeningPermitStartTime() {
        return openingPermitStartTime;
    }

    public void setOpeningPermitStartTime(Date openingPermitStartTime) {
        this.openingPermitStartTime = openingPermitStartTime;
    }

    public Date getOpeningPermitEndTime() {
        return openingPermitEndTime;
    }

    public void setOpeningPermitEndTime(Date openingPermitEndTime) {
        this.openingPermitEndTime = openingPermitEndTime;
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

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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

    public String getOpeningBank() {
        return openingBank;
    }

    public void setOpeningBank(String openingBank) {
        this.openingBank = openingBank;
    }

    public String getOpeningAccount() {
        return OpeningAccount;
    }

    public void setOpeningAccount(String getOpeningAccount) {
        this.OpeningAccount = getOpeningAccount;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public VendorDecorationVo getDecoration() {
        return decoration;
    }

    public void setDecoration(VendorDecorationVo decoration) {
        this.decoration = decoration;
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


    public Long getRegisteredCapital() {
        return RegisteredCapital;
    }

    public void setRegisteredCapital(Long registeredCapital) {
        RegisteredCapital = registeredCapital;
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

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecretAnswer() {
        return secretAnswer;
    }

    public void setSecretAnswer(String secretAnswer) {
        this.secretAnswer = secretAnswer;
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

    public boolean isFavoriteFlag() {
        return favoriteFlag;
    }

    public AuditStatus getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    public VendorStatus getVendorStatus() {
        return vendorStatus;
    }

    public void setVendorStatus(VendorStatus vendorStatus) {
        this.vendorStatus = vendorStatus;
    }


    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getBrandOperation() {
        return brandOperation;
    }

    public void setBrandOperation(String brandOperation) {
        this.brandOperation = brandOperation;
    }

    public Integer getAttitudeNum() {
        return attitudeNum;
    }

    public void setAttitudeNum(Integer attitudeNum) {
        this.attitudeNum = attitudeNum;
    }

    public Integer getAttitudeScore() {
        return attitudeScore;
    }

    public void setAttitudeScore(Integer attitudeScore) {
        this.attitudeScore = attitudeScore;
    }

    public Integer getDescriptionNum() {
        return descriptionNum;
    }

    public void setDescriptionNum(Integer descriptionNum) {
        this.descriptionNum = descriptionNum;
    }

    public Integer getDescriptionScore() {
        return descriptionScore;
    }

    public void setDescriptionScore(Integer descriptionScore) {
        this.descriptionScore = descriptionScore;
    }

    public Integer getLogisticsNum() {
        return logisticsNum;
    }

    public void setLogisticsNum(Integer logisticsNum) {
        this.logisticsNum = logisticsNum;
    }

    public Integer getLogisticsScore() {
        return logisticsScore;
    }

    public void setLogisticsScore(Integer logisticsScore) {
        this.logisticsScore = logisticsScore;
    }

    public boolean getFavoriteFlag() {
        return favoriteFlag;
    }

    public void setFavoriteFlag(boolean favoriteFlag) {
        this.favoriteFlag = favoriteFlag;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
