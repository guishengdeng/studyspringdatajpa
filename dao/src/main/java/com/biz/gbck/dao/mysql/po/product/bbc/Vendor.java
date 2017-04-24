package com.biz.gbck.dao.mysql.po.product.bbc;

import com.biz.gbck.dao.mysql.po.vendor.bbc.*;
import com.biz.gbck.enums.product.VendorTypeEnum;
import com.biz.gbck.enums.vendor.*;
import com.biz.support.jpa.po.BaseEntity;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 * @author yanweijin
 * @date 2016/12/19
 */
//@Entity
//@Table(name = "ven_vendor")
public class Vendor extends BaseEntity {

    /**
     * 老官网原始Id
     */
    private String storeId;

    //对应用户id
    private Long userId;
    // 商铺名称
    @Column(length = 100)
    private String vendorName;

    // 商铺编号
    @Column(length = 64, unique = true)
    private String vendorCode;

    // 商铺 logo 图片地址
    @Column(length = 100)
    private String logo;

    // 审批备注
    @Column(length = 1000)
    private String remark;

    // 店铺所有者
    @Column(length = 32)
    private String owner;

    // 运营人员身份证正面图片
    private String idCardFrontImg;

    // 运营人员身份证反面照片
    private String idCardBackImg;

    // 真实姓名
    @Column(length = 32)
    private String ownerRealname;

    // 身份证
    @Column(length = 18)
    private String ownerIdcard;

    //护照
    private String passportId;
    // 手机号
    @Column(length = 15)
    private String ownerMobile;
    // 邮箱
    @Column(length = 100)
    private String ownerEmail;

    // 开户行
    @Column(length = 50)
    private String openingBank;

    // 开户行帐号
    @Column(length = 50)
    private String openingAccount;

    // 审核状态
    @Column(length = 32)
    @Enumerated(EnumType.STRING)
    private AuditStatus auditStatus = AuditStatus.WAITING_AUDIT;

    // 类型
    @Column(length = 32, nullable = false)
    @Enumerated(EnumType.STRING)
    private VendorTypeEnum vendorType;

    // 运营类型
    @Column(length = 32)
    @Enumerated(EnumType.STRING)
    private VendorOperationType vendorOperationType;

    // 状态
    @Column(length = 32)
    @Enumerated(EnumType.STRING)
    private VendorStatus status = VendorStatus.NOT_PERFECT;

    // 用户-角色-商铺关联
    // 通过用户找到角色,进而找到商铺
    @OneToMany(mappedBy = "vendor")
    private List<UserVendorRole> userVendorRoles;

    // 商铺装饰信息
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "decoration_id")
    private VendorDecoration decoration;

    // 商铺选择的快递方式
    @OneToMany(mappedBy = "vendor")
    private List<VendorExpress> vendorExpresses;

    // 商铺支持的运费策略
    @OneToMany(mappedBy = "vendor")
    private List<FreightStrategy> freightStrategies;

    // 删除状态
    private boolean deleteStatu;

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

    // 营业执照号
    @Column(length = 50)
    private String businessLicenseNumber;

    // 税务登记证号
    @Column(length = 50)
    private String taxRegistrationNumber;

    // 企业组织机构代码
    @Column(length = 50)
    private String enterpriseOrganizationCode;

    // 法定代表人
    @Column(length = 50)
    private String legalRepresentative;

    // 法人身份证
    @Column(length = 50)
    private String legalRepresentativeId;

    // 公司负责人姓名
    @Column(length = 50)
    private String companyRepresentativeName;

    // 公司负责人身份证
    @Column(length = 50)
    private String companyRepresentativeId;

    // 公司负责人职位
    @Column(length = 50)
    private String companyRepresentativeposition;

    // 公司联系电话
    @Column(length = 50)
    private String companyContactNumber;

    // 公司所在省
    @Column(length = 11)
    private Integer province;

    // 公司所在市
    @Column(length = 11)
    private Integer city;

    // 公司所在区
    @Column(length = 11)
    private Integer area;
    // 公司地址
    @Column(length = 100)
    private String companyAddress;

    // 注册资金
    @Column(length = 19)
    private Long RegisteredCapital;

    // 营业执照有效期
    @Column(length = 50)
    private Date businessLicenseExpiryDate;

    // 营业执照经营范围
    @Column(length = 1000)
    private String businessLicenseManagementScope;

    // 营业执照复印件
    @Column(length = 100)
    private String businessLicensePhotoCopy;

    // 营业执照有效期开始时间
    @Column(length = 50)
    private Date businessLicenseStartTime;

    // 组织机构代码证复印件
    @Column(length = 100)
    private String enterpriseOrganizationCodePhotoCopy;

    // 组织机构代码证有效期开始时间
    @Column(length = 50)
    private Date enterpriseOrganizationCodeStartTime;

    // 组织机构代码证有效期结束时间
    @Column(length = 50)
    private Date enterpriseOrganizationCodeEndTime;

    // 税务登记证复印件
    @Column(length = 100)
    private String taxRegistrationPhotoCopy;

    // 税务登记证有效期开始时间
    @Column(length = 50)
    private Date taxRegistrationStartTime;

    // 税务登记证有效期结束时间
    @Column(length = 50)
    private Date taxRegistrationEndTime;

    // 食品经营许可证复印件
    @Column(length = 100)
    private String foodBusinessLicensePhotoCopy;

    // 食品经营许可证有效期开始时间
    @Column(length = 50)
    private Date foodBusinessLicenseStartTime;

    // 食品经营许可证有效期结束时间
    @Column(length = 50)
    private Date foodBusinessLicenseEndTime;

    // 商标注册复印件
    @Column(length = 100)
    private String trademarkRegistrationPhotoCopy;

    // 经营品牌的商标注册复印件
    private String brandManagementTrademarkRegistrationPhotoCopy;

    // 商标注册有效期开始时间
    @Column(length = 50)
    private Date trademarkRegistrationStartTime;

    // 商标注册有效期结束时间
    @Column(length = 50)
    private Date trademarkRegistrationEndTime;

    // 开户许可证复印件
    @Column(length = 100)
    private String openingPermitPhotoCopy;

    // 开户许可证有效期开始时间
    @Column(length = 50)
    private Date openingPermitStartTime;

    // 开户许可证有效期结束时间
    @Column(length = 50)
    private Date openingPermitEndTime;

    // 店铺类型
    @Column(length = 32)
    @Enumerated(EnumType.STRING)
    private VendorRegisterType vendorRegisterType;

    // 证件类型
    @Column(length = 32)
    @Enumerated(EnumType.STRING)
    private CertificateType certificateType;

    // 其他
    @Column(length = 50)
    private String elseImg;

    // 法人身份证正面图片
    @Column(length = 50)
    private String legalPersonIdCardFrontImg;

    // 法人身份证反面图片
    @Column(length = 50)
    private String legalPersonCardBackImg;

    // 法人护照图片
    @Column(length = 50)
    private String legalPeronPassportImg;

    // 品牌授权复印件
    @Column(length = 50)
    private String brandAuthorImg;

    // 报关单复印件
    @Column(length = 50)
    private String manifestImg;

    // 护照图片
    @Column(length = 50)
    private String passportImg;

    // 公司法人证件类型
    @Column(length = 32)
    @Enumerated(EnumType.STRING)
    private CompanyCertificateType companyCertificateType;

    // 审核备注
    @Column(length = 500)
    private String auditVendorRemark;

    /**
     * 店铺评价、点击量等统计
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private VendorStatistic vendorStatistic;

    //营业执照副本发放时间
    @Column(length = 50)
    private Date businessLisenceCopyStartTime;

    //营业执照副本有效期
    @Column(length = 50)
    private Date businessLisenceCopyEndTime;
    //
    //	@ManyToMany(fetch = FetchType.LAZY)
    //    @JoinTable(name = "ven_vendor_brand",
    //            joinColumns = {@JoinColumn(name = "vendor_id", referencedColumnName = "id")},
    //            inverseJoinColumns = {@JoinColumn(name = "brand_id", referencedColumnName = "id")},
    //            uniqueConstraints = {@UniqueConstraint(columnNames= {"vendor_id" , "brand_id"})})
    //	private List<Brand> brands;

    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BrandVendorRelation> brandVendorRelations;

    //保证金
    @Column(length = 11)
    private Integer margin;

    //支付单号
    @Column(length = 50)
    private String paymentOrder;

    //手续费
    @Column(length = 11)
    private Integer poundage;

    //省名称
    @Column(length = 20)
    private String provinceName;

    //市名称
    @Column(length = 20)
    private String cityName;

    //区域名称
    @Column(length = 20)
    private String areaName;

    public String getBrandNames() {
        List<BrandVendorRelation> brands = this.getBrandVendorRelations();
        if (brands != null && brands.size() > 0) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < brands.size(); i++) {
                if (brands.get(i).getBrand() != null) {
                    if (i < brands.size() - 1) {
                        sb.append(brands.get(i).getBrand().getName() + ",");
                    } else {
                        sb.append(brands.get(i).getBrand().getName());
                    }
                }
            }
        }
        return null;
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

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public Date getBusinessLicenseStartTime() {
        return businessLicenseStartTime;
    }

    public void setBusinessLicenseStartTime(Date businessLicenseStartTime) {
        this.businessLicenseStartTime = businessLicenseStartTime;
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

    public boolean isDeleteStatu() {
        return deleteStatu;
    }

    public void setDeleteStatu(boolean deleteStatu) {
        this.deleteStatu = deleteStatu;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public List<UserVendorRole> getUserVendorRoles() {
        return userVendorRoles;
    }

    public void setUserVendorRoles(List<UserVendorRole> userVendorRoles) {
        this.userVendorRoles = userVendorRoles;
    }

    public VendorDecoration getDecoration() {
        return decoration;
    }

    public void setDecoration(VendorDecoration decoration) {
        this.decoration = decoration;
    }

    public List<VendorExpress> getVendorExpresses() {
        return vendorExpresses;
    }

    public void setVendorExpresses(List<VendorExpress> vendorExpresses) {
        this.vendorExpresses = vendorExpresses;
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

    public List<FreightStrategy> getFreightStrategies() {
        return freightStrategies;
    }

    public void setFreightStrategies(List<FreightStrategy> freightStrategies) {
        this.freightStrategies = freightStrategies;
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

    public VendorTypeEnum getVendorType() {
        return vendorType;
    }

    public void setVendorType(VendorTypeEnum vendorType) {
        this.vendorType = vendorType;
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

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
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

    public String getElseImg() {
        return elseImg;
    }

    public void setElseImg(String elseImg) {
        this.elseImg = elseImg;
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

    public String getPassportImg() {
        return passportImg;
    }

    public void setPassportImg(String passportImg) {
        this.passportImg = passportImg;
    }

    public CompanyCertificateType getCompanyCertificateType() {
        return companyCertificateType;
    }

    public void setCompanyCertificateType(CompanyCertificateType companyCertificateType) {
        this.companyCertificateType = companyCertificateType;
    }

    public String getAuditVendorRemark() {
        return auditVendorRemark;
    }

    public void setAuditVendorRemark(String auditVendorRemark) {
        this.auditVendorRemark = auditVendorRemark;
    }

    public VendorStatistic getVendorStatistic() {
        return vendorStatistic;
    }

    public void setVendorStatistic(VendorStatistic vendorStatistic) {
        this.vendorStatistic = vendorStatistic;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getBrandManagementTrademarkRegistrationPhotoCopy() {
        return brandManagementTrademarkRegistrationPhotoCopy;
    }

    public void setBrandManagementTrademarkRegistrationPhotoCopy(String
                                                                         brandManagementTrademarkRegistrationPhotoCopy) {
        this.brandManagementTrademarkRegistrationPhotoCopy = brandManagementTrademarkRegistrationPhotoCopy;
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

    public List<BrandVendorRelation> getBrandVendorRelations() {
        return brandVendorRelations;
    }

    public void setBrandVendorRelations(List<BrandVendorRelation> brandVendorRelations) {
        this.brandVendorRelations = brandVendorRelations;
    }
}
