package com.biz.gbck.dao.mysql.po.vendor;

import com.biz.gbck.enums.product.VendorTypeEnum;
import com.biz.gbck.enums.vendor.*;
import com.biz.support.jpa.po.BaseEntity;
import java.util.Date;
import javax.persistence.*;

/**
 * @author mounan
 * @Description: A类商铺审核类(同时也是注册类)，字段基本冗余商铺，如果商铺审核通过在保存商铺（vendor）
 * @time:2017年2月9日 下午5:26:16
 */
@Entity
@Table(name = "ven_audit_vendor")
public class AuditVendor extends BaseEntity {

    // 商铺名称
    @Column(length = 100)
    private String vendorName;

    // 店铺名称是否有误
    private Boolean vendorNameIsWrong = Boolean.FALSE;

    // 运营人员身份证正面图片
    private String idCardFrontImg;

    //店铺logo
    private String logo;

    private Boolean logoIsWrong;
    // 运营人员身份证反面照片
    private String idCardBackImg;

    // 运营人员证件是否有误
    private Boolean ownerCardIsWrong = Boolean.FALSE;

    // 真实姓名
    @Column(length = 32)
    private String ownerRealname;

    // 店主名是否有误
    private Boolean ownerRealnameIsWrong = Boolean.FALSE;

    // 身份证
    @Column(length = 18)
    private String ownerIdcard;

    // 店主身份证是否有误
    private Boolean ownerIdcardIsWrong = Boolean.FALSE;

    //店主护照
    private String passportId;

    //店主护照是否有误
    private Boolean passportIdIsWrong = Boolean.FALSE;

    // 手机号
    @Column(length = 15)
    private String ownerMobile;

    // 手机号是否有误
    private Boolean ownerMobileIsWrong = Boolean.FALSE;

    // 开户行
    @Column(length = 50)
    private String openingBank;

    //开户行是否有误
    private Boolean openingBankIsWrong = Boolean.FALSE;

    // 开户行帐号
    @Column(length = 50)
    private String openingAccount;

    //开户行账号是否有误
    private Boolean openingAccountIsWrong = Boolean.FALSE;
    // 审核状态
    @Column(length = 32)
    @Enumerated(EnumType.STRING)
    private AuditStatus auditStatus = AuditStatus.WAITING_AUDIT;

    // 删除状态
    private Boolean deleteStatu = Boolean.FALSE;

	/*
     * //密保问题
	 * 
	 * @Column(length = 100) private String securityQuestion;
	 */

    // 密码答案
    /*
     * @Column(length = 50) private String secretAnswer;
	 */

    // 有效期
    @Column(length = 50)
    private Date expiryDate;

    // 企业名称
    @Column(length = 50)
    private String enterpriseName;

    // 公司名是否有误
    private Boolean enterpriseNameIsWrong = Boolean.FALSE;
    // 营业执照号
    @Column(length = 50)
    private String businessLicenseNumber;

    // 营业执照号是否有误
    private Boolean businessLicenseNumberIsWrong = Boolean.FALSE;

    // 税务登记证号
    @Column(length = 50)
    private String taxRegistrationNumber;

    // 税务登记证号是否有误
    private Boolean taxRegistrationNumberIsWrong = Boolean.FALSE;

    // 企业组织机构代码
    @Column(length = 50)
    private String enterpriseOrganizationCode;

    // 组织机构代码是否有误
    private Boolean enterpriseOrganizationCodeIsWrong = Boolean.FALSE;

    // 法定代表人
    @Column(length = 50)
    private String legalRepresentative;

    // 法定代表人是否有误
    private Boolean legalRepresentativeIsWrong = Boolean.FALSE;

    // 法人身份证
    @Column(length = 50)
    private String legalRepresentativeId;

    // 法人证件是否有误
    private Boolean legalRepresentativeCardIsWrong = Boolean.FALSE;

    // 公司所在省
    private Integer province;

    @Column(length = 19)
    private String provinceName;

    // 公司所在地是否有误
    private Boolean companyLocatedIsWrong = Boolean.FALSE;

    // 公司所在市
    private Integer city;

    @Column(length = 19)
    private String cityName;
    // 公司所在区
    private Integer area;

    @Column(length = 19)
    private String areaName;
    // 公司地址
    @Column(length = 100)
    private String companyAddress;

    // 公司地址是否有误
    private Boolean copanuAddressIsWrong = Boolean.FALSE;

    // 注册资金
    @Column(length = 19)
    private Long registeredCapital;

    //注册资金是否有误
    private Boolean registeredCapitalIsWrong = Boolean.FALSE;

    // 营业执照经营范围
    @Column(length = 1000)
    private String businessLicenseManagementScope;

    // 经营范围是否有误
    private Boolean businessLicenseManagementScopeIsWrong = Boolean.FALSE;

    // 营业执照复印件
    @Column(length = 100)
    private String businessLicensePhotoCopy;

    private Boolean businessLicensePhotoCopyIsWrong;
    // 营业执照有效期开始时间
    @Column(length = 50)
    private Date businessLicenseStartTime;

    // 营业执照有效期
    @Column(length = 50)
    private Date businessLicenseExpiryDate;

    //营业执照有效期是否有误
    private Boolean businessLicenseTimeIsWrong;

    private String businessLicenseTimeIsCheck;
    // 组织机构代码证复印件
    @Column(length = 100)
    private String enterpriseOrganizationCodePhotoCopy;

    private Boolean organizationCodePhotoCopyIsWrong;
    // 组织机构代码证有效期开始时间
    @Column(length = 50)
    private Date enterpriseOrganizationCodeStartTime;

    private Boolean organizationCodeTimeIsWrong;
    // 组织机构代码证有效期结束时间
    @Column(length = 50)
    private Date enterpriseOrganizationCodeEndTime;

    // 税务登记证复印件
    @Column(length = 100)
    private String taxRegistrationPhotoCopy;

    private Boolean taxRegistrationPhotoCopyIsWrong;
    // 税务登记证有效期开始时间
    @Column(length = 50)
    private Date taxRegistrationStartTime;

    private Boolean taxRegistrationTimeIsWrong;
    // 税务登记证有效期结束时间
    @Column(length = 50)
    private Date taxRegistrationEndTime;

    // 食品经营许可证复印件
    @Column(length = 100)
    private String foodBusinessLicensePhotoCopy;

    private Boolean foodBusinessLicensePhotoCopyIsWrong;
    // 食品经营许可证有效期开始时间
    @Column(length = 50)
    private Date foodBusinessLicenseStartTime;

    private Boolean foodBusinessLicenseTimeIsWrong;

    // 食品经营许可证有效期结束时间
    @Column(length = 50)
    private Date foodBusinessLicenseEndTime;

    // 商标注册复印件
    @Column(length = 100)
    private String trademarkRegistrationPhotoCopy;

    private Boolean trademarkRegistrationPhotoCopyIsWrong;
    // 商标注册有效期开始时间
    @Column(length = 50)
    private Date trademarkRegistrationStartTime;

    private Boolean trademarkRegistrationTimeIsWrong;
    // 商标注册有效期结束时间
    @Column(length = 50)
    private Date trademarkRegistrationEndTime;

    // 开户许可证复印件
    @Column(length = 100)
    private String openingPermitPhotoCopy;

    private Boolean openingPermitPhotoCopyIsWrong;
    // 开户许可证有效期开始时间
    @Column(length = 50)
    private Date openingPermitStartTime;

    private Boolean openingPermitTimeIsWrong;
    // 开户许可证有效期结束时间
    @Column(length = 50)
    private Date openingPermitEndTime;

    // 法人身份证正面图片
    private String legalPersonIdCardFrontImg;

    // 法人身份证反面图片
    private String legalPersonCardBackImg;

    // 法人护照图片
    private String legalPeronPassportImg;

    // 商标注册复印件是否有误
    private Boolean tradeMarkRegisterImgIsWrong = Boolean.FALSE;


    // 护照图片
    private String passportImg;


    // 店铺类型
    @Column(length = 32)
    @Enumerated(EnumType.STRING)
    private VendorRegisterType vendorRegisterType;

    // 证件类型
    @Column(length = 32)
    @Enumerated(EnumType.STRING)
    private CertificateType certificateType = CertificateType.ID_CARD;

    // 公司法人证件类型
    @Column(length = 32)
    @Enumerated(EnumType.STRING)
    private CompanyCertificateType companyCertificateType = CompanyCertificateType.ID_CARD;

    // 商铺代码
    private String vendorCode;
    // 运营类型
    @Column(length = 32)
    @Enumerated(EnumType.STRING)
    private VendorOperationType vendorOperationType;

    // 状态
    @Column(length = 32)
    @Enumerated(EnumType.STRING)
    private VendorStatus status;

    @Column(length = 32)
    @Enumerated(EnumType.STRING)
    private VendorTypeEnum vendorType = VendorTypeEnum.TYPE_A;
    //备注
    private String remark;

    //审核备注
    private String auditVendorRemark;
    //用户id
    private Long userId;

    //营业执照副本发放时间
    private Date businessLisenceCopyStartTime;

    //营业执照副本有效期
    private Date businessLisenceCopyEndTime;

    //保证金
    @Column(length = 11)
    private Integer margin;

    //支付单号
    @Column(length = 50)
    private String paymentOrder;

    //手续费
    @Column(length = 11)
    private Integer poundage;

    //是否可以修改
    private Boolean canAdudit;
    //店主证件复印件是否有误
    private Boolean ownerCertificateIsWrong;
    //法人证件是否有误
    private Boolean legalPersonCertificateIsWrong;


    public Boolean getOwnerCertificateIsWrong() {
        return ownerCertificateIsWrong;
    }

    public void setOwnerCertificateIsWrong(Boolean ownerCertificateIsWrong) {
        this.ownerCertificateIsWrong = ownerCertificateIsWrong;
    }

    public Boolean getLegalPersonCertificateIsWrong() {
        return legalPersonCertificateIsWrong;
    }

    public void setLegalPersonCertificateIsWrong(Boolean legalPersonCertificateIsWrong) {
        this.legalPersonCertificateIsWrong = legalPersonCertificateIsWrong;
    }

    public Boolean getCanAdudit() {
        return canAdudit;
    }

    public void setCanAdudit(Boolean canAdudit) {
        this.canAdudit = canAdudit;
    }

    public Boolean getBusinessLicensePhotoCopyIsWrong() {
        return businessLicensePhotoCopyIsWrong;
    }

    public void setBusinessLicensePhotoCopyIsWrong(Boolean businessLicensePhotoCopyIsWrong) {
        this.businessLicensePhotoCopyIsWrong = businessLicensePhotoCopyIsWrong;
    }

    public Boolean getOrganizationCodePhotoCopyIsWrong() {
        return organizationCodePhotoCopyIsWrong;
    }

    public void setOrganizationCodePhotoCopyIsWrong(Boolean organizationCodePhotoCopyIsWrong) {
        this.organizationCodePhotoCopyIsWrong = organizationCodePhotoCopyIsWrong;
    }

    public Boolean getOrganizationCodeTimeIsWrong() {
        return organizationCodeTimeIsWrong;
    }

    public void setOrganizationCodeTimeIsWrong(Boolean organizationCodeTimeIsWrong) {
        this.organizationCodeTimeIsWrong = organizationCodeTimeIsWrong;
    }

    public Boolean getTaxRegistrationPhotoCopyIsWrong() {
        return taxRegistrationPhotoCopyIsWrong;
    }

    public void setTaxRegistrationPhotoCopyIsWrong(Boolean taxRegistrationPhotoCopyIsWrong) {
        this.taxRegistrationPhotoCopyIsWrong = taxRegistrationPhotoCopyIsWrong;
    }

    public Boolean getTaxRegistrationTimeIsWrong() {
        return taxRegistrationTimeIsWrong;
    }

    public void setTaxRegistrationTimeIsWrong(Boolean taxRegistrationTimeIsWrong) {
        this.taxRegistrationTimeIsWrong = taxRegistrationTimeIsWrong;
    }

    public Boolean getFoodBusinessLicensePhotoCopyIsWrong() {
        return foodBusinessLicensePhotoCopyIsWrong;
    }

    public void setFoodBusinessLicensePhotoCopyIsWrong(Boolean foodBusinessLicensePhotoCopyIsWrong) {
        this.foodBusinessLicensePhotoCopyIsWrong = foodBusinessLicensePhotoCopyIsWrong;
    }

    public Boolean getFoodBusinessLicenseTimeIsWrong() {
        return foodBusinessLicenseTimeIsWrong;
    }

    public void setFoodBusinessLicenseTimeIsWrong(Boolean foodBusinessLicenseTimeIsWrong) {
        this.foodBusinessLicenseTimeIsWrong = foodBusinessLicenseTimeIsWrong;
    }

    public Boolean getTrademarkRegistrationPhotoCopyIsWrong() {
        return trademarkRegistrationPhotoCopyIsWrong;
    }

    public void setTrademarkRegistrationPhotoCopyIsWrong(Boolean trademarkRegistrationPhotoCopyIsWrong) {
        this.trademarkRegistrationPhotoCopyIsWrong = trademarkRegistrationPhotoCopyIsWrong;
    }

    public Boolean getTrademarkRegistrationTimeIsWrong() {
        return trademarkRegistrationTimeIsWrong;
    }

    public void setTrademarkRegistrationTimeIsWrong(Boolean trademarkRegistrationTimeIsWrong) {
        this.trademarkRegistrationTimeIsWrong = trademarkRegistrationTimeIsWrong;
    }

    public Boolean getOpeningPermitPhotoCopyIsWrong() {
        return openingPermitPhotoCopyIsWrong;
    }

    public void setOpeningPermitPhotoCopyIsWrong(Boolean openingPermitPhotoCopyIsWrong) {
        this.openingPermitPhotoCopyIsWrong = openingPermitPhotoCopyIsWrong;
    }

    public Boolean getOpeningPermitTimeIsWrong() {
        return openingPermitTimeIsWrong;
    }

    public void setOpeningPermitTimeIsWrong(Boolean openingPermitTimeIsWrong) {
        this.openingPermitTimeIsWrong = openingPermitTimeIsWrong;
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

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public Boolean getVendorNameIsWrong() {
        return vendorNameIsWrong;
    }

    public void setVendorNameIsWrong(Boolean vendorNameIsWrong) {
        this.vendorNameIsWrong = vendorNameIsWrong;
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

    public Boolean getOwnerCardIsWrong() {
        return ownerCardIsWrong;
    }

    public void setOwnerCardIsWrong(Boolean ownerCardIsWrong) {
        this.ownerCardIsWrong = ownerCardIsWrong;
    }

    public String getOwnerRealname() {
        return ownerRealname;
    }

    public void setOwnerRealname(String ownerRealname) {
        this.ownerRealname = ownerRealname;
    }

    public Boolean getOwnerRealnameIsWrong() {
        return ownerRealnameIsWrong;
    }

    public void setOwnerRealnameIsWrong(Boolean ownerRealnameIsWrong) {
        this.ownerRealnameIsWrong = ownerRealnameIsWrong;
    }

    public String getOwnerIdcard() {
        return ownerIdcard;
    }

    public void setOwnerIdcard(String ownerIdcard) {
        this.ownerIdcard = ownerIdcard;
    }

    public Boolean getOwnerIdcardIsWrong() {
        return ownerIdcardIsWrong;
    }

    public void setOwnerIdcardIsWrong(Boolean ownerIdcardIsWrong) {
        this.ownerIdcardIsWrong = ownerIdcardIsWrong;
    }

    public String getOwnerMobile() {
        return ownerMobile;
    }

    public void setOwnerMobile(String ownerMobile) {
        this.ownerMobile = ownerMobile;
    }

    public Boolean getOwnerMobileIsWrong() {
        return ownerMobileIsWrong;
    }

    public void setOwnerMobileIsWrong(Boolean ownerMobileIsWrong) {
        this.ownerMobileIsWrong = ownerMobileIsWrong;
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

    public AuditStatus getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    public boolean isDeleteStatu() {
        return deleteStatu;
    }

    public void setDeleteStatu(boolean deleteStatu) {
        this.deleteStatu = deleteStatu;
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

    public Boolean getEnterpriseNameIsWrong() {
        return enterpriseNameIsWrong;
    }

    public void setEnterpriseNameIsWrong(Boolean enterpriseNameIsWrong) {
        this.enterpriseNameIsWrong = enterpriseNameIsWrong;
    }

    public String getBusinessLicenseNumber() {
        return businessLicenseNumber;
    }

    public void setBusinessLicenseNumber(String businessLicenseNumber) {
        this.businessLicenseNumber = businessLicenseNumber;
    }

    public Boolean getBusinessLicenseNumberIsWrong() {
        return businessLicenseNumberIsWrong;
    }

    public void setBusinessLicenseNumberIsWrong(Boolean businessLicenseNumberIsWrong) {
        this.businessLicenseNumberIsWrong = businessLicenseNumberIsWrong;
    }

    public String getTaxRegistrationNumber() {
        return taxRegistrationNumber;
    }

    public void setTaxRegistrationNumber(String taxRegistrationNumber) {
        this.taxRegistrationNumber = taxRegistrationNumber;
    }

    public Boolean getTaxRegistrationNumberIsWrong() {
        return taxRegistrationNumberIsWrong;
    }

    public void setTaxRegistrationNumberIsWrong(Boolean taxRegistrationNumberIsWrong) {
        this.taxRegistrationNumberIsWrong = taxRegistrationNumberIsWrong;
    }

    public String getEnterpriseOrganizationCode() {
        return enterpriseOrganizationCode;
    }

    public void setEnterpriseOrganizationCode(String enterpriseOrganizationCode) {
        this.enterpriseOrganizationCode = enterpriseOrganizationCode;
    }

    public Boolean getEnterpriseOrganizationCodeIsWrong() {
        return enterpriseOrganizationCodeIsWrong;
    }

    public void setEnterpriseOrganizationCodeIsWrong(Boolean enterpriseOrganizationCodeIsWrong) {
        this.enterpriseOrganizationCodeIsWrong = enterpriseOrganizationCodeIsWrong;
    }

    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
    }

    public Boolean getLegalRepresentativeIsWrong() {
        return legalRepresentativeIsWrong;
    }

    public void setLegalRepresentativeIsWrong(Boolean legalRepresentativeIsWrong) {
        this.legalRepresentativeIsWrong = legalRepresentativeIsWrong;
    }

    public String getLegalRepresentativeId() {
        return legalRepresentativeId;
    }

    public void setLegalRepresentativeId(String legalRepresentativeId) {
        this.legalRepresentativeId = legalRepresentativeId;
    }

    public Boolean getLegalRepresentativeCardIsWrong() {
        return legalRepresentativeCardIsWrong;
    }

    public void setLegalRepresentativeCardIsWrong(Boolean legalRepresentativeCardIsWrong) {
        this.legalRepresentativeCardIsWrong = legalRepresentativeCardIsWrong;
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Boolean getCompanyLocatedIsWrong() {
        return companyLocatedIsWrong;
    }

    public void setCompanyLocatedIsWrong(Boolean companyLocatedIsWrong) {
        this.companyLocatedIsWrong = companyLocatedIsWrong;
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

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public Boolean getCopanuAddressIsWrong() {
        return copanuAddressIsWrong;
    }

    public void setCopanuAddressIsWrong(Boolean copanuAddressIsWrong) {
        this.copanuAddressIsWrong = copanuAddressIsWrong;
    }


    public Boolean getDeleteStatu() {
        return deleteStatu;
    }

    public void setDeleteStatu(Boolean deleteStatu) {
        this.deleteStatu = deleteStatu;
    }


    public Long getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(Long registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public String getBusinessLicenseTimeIsCheck() {
        return businessLicenseTimeIsCheck;
    }

    public void setBusinessLicenseTimeIsCheck(String businessLicenseTimeIsCheck) {
        this.businessLicenseTimeIsCheck = businessLicenseTimeIsCheck;
    }

    public String getBusinessLicenseManagementScope() {
        return businessLicenseManagementScope;
    }

    public void setBusinessLicenseManagementScope(String businessLicenseManagementScope) {
        this.businessLicenseManagementScope = businessLicenseManagementScope;
    }

    public Boolean getBusinessLicenseManagementScopeIsWrong() {
        return businessLicenseManagementScopeIsWrong;
    }

    public void setBusinessLicenseManagementScopeIsWrong(Boolean businessLicenseManagementScopeIsWrong) {
        this.businessLicenseManagementScopeIsWrong = businessLicenseManagementScopeIsWrong;
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

    public Date getBusinessLicenseExpiryDate() {
        return businessLicenseExpiryDate;
    }

    public void setBusinessLicenseExpiryDate(Date businessLicenseExpiryDate) {
        this.businessLicenseExpiryDate = businessLicenseExpiryDate;
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

    public Boolean getTradeMarkRegisterImgIsWrong() {
        return tradeMarkRegisterImgIsWrong;
    }

    public void setTradeMarkRegisterImgIsWrong(Boolean tradeMarkRegisterImgIsWrong) {
        this.tradeMarkRegisterImgIsWrong = tradeMarkRegisterImgIsWrong;
    }

    public String getPassportImg() {
        return passportImg;
    }

    public void setPassportImg(String passportImg) {
        this.passportImg = passportImg;
    }


    public VendorRegisterType getVendorRegisterType() {
        return vendorRegisterType;
    }

    public void setVendorRegisterType(VendorRegisterType vendorRegisterType) {
        this.vendorRegisterType = vendorRegisterType;
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

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getRegisteredCapitalIsWrong() {
        return registeredCapitalIsWrong;
    }

    public void setRegisteredCapitalIsWrong(Boolean registeredCapitalIsWrong) {
        this.registeredCapitalIsWrong = registeredCapitalIsWrong;
    }

    public Boolean getOpeningBankIsWrong() {
        return openingBankIsWrong;
    }

    public void setOpeningBankIsWrong(Boolean openingBankIsWrong) {
        this.openingBankIsWrong = openingBankIsWrong;
    }

    public Boolean getOpeningAccountIsWrong() {
        return openingAccountIsWrong;
    }

    public void setOpeningAccountIsWrong(Boolean openingAccountIsWrong) {
        this.openingAccountIsWrong = openingAccountIsWrong;
    }

    public String getAuditVendorRemark() {
        return auditVendorRemark;
    }

    public void setAuditVendorRemark(String auditVendorRemark) {
        this.auditVendorRemark = auditVendorRemark;
    }

    public VendorTypeEnum getVendorType() {
        return vendorType;
    }

    public void setVendorType(VendorTypeEnum vendorType) {
        this.vendorType = vendorType;
    }

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    public Boolean getPassportIdIsWrong() {
        return passportIdIsWrong;
    }

    public void setPassportIdIsWrong(Boolean passportIdIsWrong) {
        this.passportIdIsWrong = passportIdIsWrong;
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

    public Boolean getBusinessLicenseTimeIsWrong() {
        return businessLicenseTimeIsWrong;
    }

    public void setBusinessLicenseTimeIsWrong(Boolean businessLicenseTimeIsWrong) {
        this.businessLicenseTimeIsWrong = businessLicenseTimeIsWrong;
    }

    public Integer getPoundage() {
        return poundage;
    }

    public void setPoundage(Integer poundage) {
        this.poundage = poundage;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Boolean getLogoIsWrong() {
        return logoIsWrong;
    }

    public void setLogoIsWrong(Boolean logoIsWrong) {
        this.logoIsWrong = logoIsWrong;
    }

}
