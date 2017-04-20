package com.biz.gbck.dao.mysql.po.vendor;

import com.biz.gbck.dao.mysql.po.product.bbc.Vendor;
import com.biz.support.jpa.po.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ven_register_vendor_brand")
public class RegisterVendorBrand extends BaseEntity {

    private static final long serialVersionUID = -6593553418837474038L;

    @ManyToOne
    private AuditVendor auditVendor;

    @ManyToOne
    private Vendor vendor;
    //经营品牌的商标注册复印件
    private String brandManagementTrademarkRegistrationPhotoCopy;

    // 经营品牌的商标注册复印件是否有误
    private Boolean brandManagementTrademarkRegistrationPhotoCopyIsWrong = Boolean.FALSE;

    // 品牌授权复印件
    private String brandAuthorImg;

    // 品牌授权是否有误
    private Boolean brandAuthorImgIsWrong = Boolean.FALSE;

    // 报关单复印件
    private String manifestImg;

    // 报关单是否有误
    private Boolean manifestImgIsWrong = Boolean.FALSE;


    // 其他
    private String elseImg;

    // 其他文件是否有误
    private Boolean elseImgIsWrong = Boolean.FALSE;

    public String getBrandManagementTrademarkRegistrationPhotoCopy() {
        return brandManagementTrademarkRegistrationPhotoCopy;
    }

    public void setBrandManagementTrademarkRegistrationPhotoCopy(String brandManagementTrademarkRegistrationPhotoCopy) {
        this.brandManagementTrademarkRegistrationPhotoCopy = brandManagementTrademarkRegistrationPhotoCopy;
    }

    public Boolean getBrandManagementTrademarkRegistrationPhotoCopyIsWrong() {
        return brandManagementTrademarkRegistrationPhotoCopyIsWrong;
    }

    public void setBrandManagementTrademarkRegistrationPhotoCopyIsWrong(
            Boolean brandManagementTrademarkRegistrationPhotoCopyIsWrong) {
        this.brandManagementTrademarkRegistrationPhotoCopyIsWrong = brandManagementTrademarkRegistrationPhotoCopyIsWrong;
    }

    public String getBrandAuthorImg() {
        return brandAuthorImg;
    }

    public void setBrandAuthorImg(String brandAuthorImg) {
        this.brandAuthorImg = brandAuthorImg;
    }

    public Boolean getBrandAuthorImgIsWrong() {
        return brandAuthorImgIsWrong;
    }

    public void setBrandAuthorImgIsWrong(Boolean brandAuthorImgIsWrong) {
        this.brandAuthorImgIsWrong = brandAuthorImgIsWrong;
    }

    public String getManifestImg() {
        return manifestImg;
    }

    public void setManifestImg(String manifestImg) {
        this.manifestImg = manifestImg;
    }

    public Boolean getManifestImgIsWrong() {
        return manifestImgIsWrong;
    }

    public void setManifestImgIsWrong(Boolean manifestImgIsWrong) {
        this.manifestImgIsWrong = manifestImgIsWrong;
    }

    public String getElseImg() {
        return elseImg;
    }

    public void setElseImg(String elseImg) {
        this.elseImg = elseImg;
    }

    public Boolean getElseImgIsWrong() {
        return elseImgIsWrong;
    }

    public void setElseImgIsWrong(Boolean elseImgIsWrong) {
        this.elseImgIsWrong = elseImgIsWrong;
    }

    public AuditVendor getAuditVendor() {
        return auditVendor;
    }

    public void setAuditVendor(AuditVendor auditVendor) {
        this.auditVendor = auditVendor;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    @Override
    public String toString() {
        return "RegisterVendorBrand [auditVendor=" + auditVendor + ", vendor=" + vendor
                + ", brandManagementTrademarkRegistrationPhotoCopy=" + brandManagementTrademarkRegistrationPhotoCopy
                + ", brandManagementTrademarkRegistrationPhotoCopyIsWrong="
                + brandManagementTrademarkRegistrationPhotoCopyIsWrong + ", brandAuthorImg=" + brandAuthorImg
                + ", brandAuthorImgIsWrong=" + brandAuthorImgIsWrong + ", manifestImg=" + manifestImg
                + ", manifestImgIsWrong=" + manifestImgIsWrong + ", elseImg=" + elseImg + ", elseImgIsWrong="
                + elseImgIsWrong + "]";
    }


}
