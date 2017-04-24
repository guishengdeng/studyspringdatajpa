package com.biz.gbck.vo.vendor;

import java.io.Serializable;

/**
 * @author yanweijin
 * @date 2017/3/14
 */
public class BrandStreetVo implements Serializable {

    private static final long serialVersionUID = 8246724126611372724L;

    private String brandName;
    private String brandLogo;
    private Long vendorId;
    private String vendorName;

    public BrandStreetVo(String brandName, String brandLogo, Long vendorId, String vendorName) {
        this.brandName = brandName;
        this.brandLogo = brandLogo;
        this.vendorId = vendorId;
        this.vendorName = vendorName;
    }

    public BrandStreetVo(String brandName, String brandLogo, Long vendorId) {
        this(brandName, brandLogo, vendorId, "");
    }


    public BrandStreetVo() {
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandLogo() {
        return brandLogo;
    }

    public void setBrandLogo(String brandLogo) {
        this.brandLogo = brandLogo;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
}
