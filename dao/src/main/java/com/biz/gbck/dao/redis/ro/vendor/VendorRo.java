package com.biz.gbck.dao.redis.ro.vendor;

import com.biz.gbck.dao.mysql.po.enums.vendor.*;
import com.biz.gbck.enums.product.VendorTypeEnum;
import com.biz.redis.annotation.Ro;
import com.biz.redis.annotation.RoSortedSet;
import com.biz.redis.bean.BaseRedisObject;
import java.io.Serializable;
import java.util.Date;

/**
 * 商铺RO
 *
 * @author LGJ
 */
@Ro(key = "vendor:VendorRo")
@RoSortedSet(key = "list", score = "id")
public class VendorRo extends BaseRedisObject<Long> implements Serializable {

    private static final long serialVersionUID = 2345571101887386626L;

    // 商铺名称
    private String vendorName;

    // 商铺编号
    private String vendorCode;

    // 商品 logo 图片地址
    private String logo;

    // 审批备注
    private String remark;

    // 店铺所有者
    private String owner;

    // 真实姓名
    private String ownerRealname;

    // 身份证
    private String ownerIdcard;

    //店主护照
    private String passportId;

    // 身份证正面图片
    private String idCardFrontImg;

    // 身份证反面照片
    private String idCardBackImg;

    // 护照照片
    private String passportImg;
    // 手机号
    private String ownerMobile;

    // 邮箱
    private String ownerEmail;

    // 开户行
    private String openingBank;

    // 开户行帐号
    private String openingAccount;

    // 审核状态
    private AuditStatus auditStatus;

    // 类型
    private VendorTypeEnum vendorType;

    // 运营类型
    private VendorOperationType vendorOperationType;

    // 状态
    private VendorStatus status;

    // 用户-角色-商铺关联
    // 通过用户找到角色,进而找到商铺
    /*
     * private List<UserVendorRole> userVendorRoles;
	 */
    // 商铺装饰信息
    private Long decorationId;
    /*
     * //商铺选择的快递方式 private List<VendorExpress> vendorExpresses;
     *
     * //商铺支持的运费策略 private List<FreightStrategy> freightStrategies;
     */
    // 删除状态
    private boolean deleteStatu;

    // 密保问题
    private String securityQuestion;

    // 密码答案
    private String secretAnswer;

    // 有效期
    private Date expiryDate;

    // 企业名称
    private String enterpriseName;

    // 营业执照号
    private String businessLicenseNumber;

    // 证件类型
    private CertificateType certificateType;

    // 税务登记证号
    private String taxRegistrationNumber;

    // 企业组织机构代码
    private String enterpriseOrganizationCode;

    // 法定代表人
    private String legalRepresentative;

    // 店铺注册类型
    private VendorRegisterType vendorRegisterType;

    // 公司证件类型
    private CompanyCertificateType companyCertificateType;

    // 法人证件
    private String legalPersonCertificateValue;

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

    // 营业执照复印件
    private String businessLicensePhotoCopy;

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

    // 经营品牌的商标注册复印件
    private String brandManagementTrademarkRegistrationPhotoCopy;

    // 商标注册有效期结束时间
    private Date trademarkRegistrationEndTime;

    // 开户许可证复印件
    private String openingPermitPhotoCopy;

    // 开户许可证有效期开始时间
    private Date openingPermitStartTime;

    // 开户许可证有效期结束时间
    private Date openingPermitEndTime;

    // 商标注册复印件
    private String tradeMarkRegisterImg;

    // 品牌授权复印件
    private String brandAuthorImg;

    // 报关单复印件
    private String manifestImg;

    // 其他
    private String elseImg;

    // 公司所在省
    private Integer province;

    // 公司所在市
    private Integer city;

    // 公司所在区
    private Integer area;

    // 法人身份证正面图片
    private String legalPersonIdCardFrontImg;

    // 法人身份证反面图片
    private String legalPersonCardBackImg;

    // 法人护照图片
    private String legalPeronPassportImg;

    // 用户ID
    private Long userId;

    private String auditVendorRemark;

    //经营品牌的id集合
    private String brandIds;

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

    //营业执照副本发放时间
    private Date businessLisenceCopyStartTime;

    //营业执照副本有效期
    private Date businessLisenceCopyEndTime;

    //保证金
    private Integer margin;

    //支付单号
    private String paymentOrder;

    //手续费
    private Integer poundage;

    // 营业执照有效期开始时间
    private Date businessLicenseStartTime;

    //省名称
    private String provinceName;

    //市名称
    private String cityName;

    //区域名称
    private String areaName;

    //店铺经营的品牌名字，以","隔开
    private String brandNames;


    public String getBrandNames() {
        return brandNames;
    }

    public void setBrandNames(String brandNames) {
        this.brandNames = brandNames;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }


    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getBrandIds() {
        return brandIds;
    }

    public void setBrandIds(String brandIds) {
        this.brandIds = brandIds;
    }

    public Date getBusinessLicenseStartTime() {
        return businessLicenseStartTime;
    }

    public void setBusinessLicenseStartTime(Date businessLicenseStartTime) {
        this.businessLicenseStartTime = businessLicenseStartTime;
    }

    public Integer getMargin() {
        return margin;
    }

    public void setMargin(Integer margin) {
        this.margin = margin;
    }

    public String getPaymentOrder() {
        return paymentOrder;
    }

    public void setPaymentOrder(String paymentOrder) {
        this.paymentOrder = paymentOrder;
    }

    public Integer getPoundage() {
        return poundage;
    }

    public void setPoundage(Integer poundage) {
        this.poundage = poundage;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Long getDecorationId() {
        return decorationId;
    }

    public void setDecorationId(Long decorationId) {
        this.decorationId = decorationId;
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

    public AuditStatus getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    public VendorTypeEnum getVendorType() {
        return vendorType;
    }

    public void setVendorType(VendorTypeEnum vendorType) {
        this.vendorType = vendorType;
    }

    public VendorOperationType getVendorOperationType() {
        return vendorOperationType;
    }

    public void setVendorOperationType(VendorOperationType vendorOperationType) {
        this.vendorOperationType = vendorOperationType;
    }

    public VendorStatus getStatus() {
        return status;
    }

    public void setStatus(VendorStatus status) {
        this.status = status;
    }

    public boolean isDeleteStatu() {
        return deleteStatu;
    }

    public void setDeleteStatu(boolean deleteStatu) {
        this.deleteStatu = deleteStatu;
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

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
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

    public String getBusinessLicensePhotoCopy() {
        return businessLicensePhotoCopy;
    }

    public void setBusinessLicensePhotoCopy(String businessLicensePhotoCopy) {
        this.businessLicensePhotoCopy = businessLicensePhotoCopy;
    }

    public String getTradeMarkRegisterImg() {
        return tradeMarkRegisterImg;
    }

    public void setTradeMarkRegisterImg(String tradeMarkRegisterImg) {
        this.tradeMarkRegisterImg = tradeMarkRegisterImg;
    }

    public String getBrandAuthorImg() {
        return brandAuthorImg;
    }

    public void setBrandAuthorImg(String brandAuthorImg) {
        this.brandAuthorImg = brandAuthorImg;
    }

    public String getManifestImg() {
        return manifestImg;
    }

    public void setManifestImg(String manifestImg) {
        this.manifestImg = manifestImg;
    }

    public String getElseImg() {
        return elseImg;
    }

    public void setElseImg(String elseImg) {
        this.elseImg = elseImg;
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public CertificateType getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(CertificateType certificateType) {
        this.certificateType = certificateType;
    }

    public CompanyCertificateType getCompanyCertificateType() {
        return companyCertificateType;
    }

    public void setCompanyCertificateType(CompanyCertificateType companyCertificateType) {
        this.companyCertificateType = companyCertificateType;
    }

    public String getLegalPersonCertificateValue() {
        return legalPersonCertificateValue;
    }

    public void setLegalPersonCertificateValue(String legalPersonCertificateValue) {
        this.legalPersonCertificateValue = legalPersonCertificateValue;
    }

    public String getOpeningAccount() {
        return openingAccount;
    }

    public void setOpeningAccount(String openingAccount) {
        this.openingAccount = openingAccount;
    }

    public String getIdCardFrontImg() {
        return idCardFrontImg;
    }

    public void setIdCardFrontImg(String idCardFrontImg) {
        this.idCardFrontImg = idCardFrontImg;
    }

    public String getIdCardBackImg() {
        return idCardBackImg;
    }

    public void setIdCardBackImg(String idCardBackImg) {
        this.idCardBackImg = idCardBackImg;
    }

    public String getPassportImg() {
        return passportImg;
    }

    public void setPassportImg(String passportImg) {
        this.passportImg = passportImg;
    }

    public String getLegalPersonIdCardFrontImg() {
        return legalPersonIdCardFrontImg;
    }

    public void setLegalPersonIdCardFrontImg(String legalPersonIdCardFrontImg) {
        this.legalPersonIdCardFrontImg = legalPersonIdCardFrontImg;
    }

    public String getLegalPersonCardBackImg() {
        return legalPersonCardBackImg;
    }

    public void setLegalPersonCardBackImg(String legalPersonCardBackImg) {
        this.legalPersonCardBackImg = legalPersonCardBackImg;
    }

    public String getLegalPeronPassportImg() {
        return legalPeronPassportImg;
    }

    public void setLegalPeronPassportImg(String legalPeronPassportImg) {
        this.legalPeronPassportImg = legalPeronPassportImg;
    }

    public VendorRegisterType getVendorRegisterType() {
        return vendorRegisterType;
    }

    public void setVendorRegisterType(VendorRegisterType vendorRegisterType) {
        this.vendorRegisterType = vendorRegisterType;
    }

    public String getAuditVendorRemark() {
        return auditVendorRemark;
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

    public void setAuditVendorRemark(String auditVendorRemark) {
        this.auditVendorRemark = auditVendorRemark;
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


    public String getBrandManagementTrademarkRegistrationPhotoCopy() {
        return brandManagementTrademarkRegistrationPhotoCopy;
    }

    public void setBrandManagementTrademarkRegistrationPhotoCopy(String brandManagementTrademarkRegistrationPhotoCopy) {
        this.brandManagementTrademarkRegistrationPhotoCopy = brandManagementTrademarkRegistrationPhotoCopy;
    }

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    public Date getBusinessLisenceCopyStartTime() {
        return businessLisenceCopyStartTime;
    }

    public void setBusinessLisenceCopyStartTime(Date businessLisenceCopyStartTime) {
        this.businessLisenceCopyStartTime = businessLisenceCopyStartTime;
    }

    public Date getBusinessLisenceCopyEndTime() {
        return businessLisenceCopyEndTime;
    }

    public void setBusinessLisenceCopyEndTime(Date businessLisenceCopyEndTime) {
        this.businessLisenceCopyEndTime = businessLisenceCopyEndTime;
    }

}
