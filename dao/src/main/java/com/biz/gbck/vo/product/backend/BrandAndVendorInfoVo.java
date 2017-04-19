package com.biz.gbck.vo.product.backend;

import java.io.Serializable;

public class BrandAndVendorInfoVo implements Serializable {

    private static final long serialVersionUID = -1177232237949943388L;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 品牌图标
     */
    private String brandLogo;

    /**
     * 品牌图标访问地址
     */
    private String brandLogoImgUrl;

    /**
     * 品牌链接，相对于根目录，如 brand/1.html
     */
    private String brandLink;

    /**
     * 品牌店名称
     */
    private String vendorName;

    /**
     * 品牌店图标
     */
    private String vendorLogo;

    /**
     * 品牌店图标访问地址
     */
    private String vendorLogoImgUrl;

    /**
     * 品牌店访问链接，相对于根目录，如 v/1.html
     */
    private String vendorLink;

    /**
     * {@linkplain BrandAndVendorInfoVo#brandName}
     */
    public String getBrandName() {

        return brandName;
    }

    /**
     * {@linkplain BrandAndVendorInfoVo#brandName}
     */
    public void setBrandName(String brandName) {

        this.brandName = brandName;
    }

    /**
     * {@linkplain BrandAndVendorInfoVo#brandLogo}
     */
    public String getBrandLogo() {

        return brandLogo;
    }

    /**
     * {@linkplain BrandAndVendorInfoVo#brandLogo}
     */
    public void setBrandLogo(String brandLogo) {

        this.brandLogo = brandLogo;
    }

    /**
     * {@linkplain BrandAndVendorInfoVo#brandLogoImgUrl}
     */
    public String getBrandLogoImgUrl() {

        return brandLogoImgUrl;
    }

    /**
     * {@linkplain BrandAndVendorInfoVo#brandLogoImgUrl}
     */
    public void setBrandLogoImgUrl(String brandLogoImgUrl) {

        this.brandLogoImgUrl = brandLogoImgUrl;
    }

    /**
     * {@linkplain BrandAndVendorInfoVo#brandLink}
     */
    public String getBrandLink() {

        return brandLink;
    }

    /**
     * {@linkplain BrandAndVendorInfoVo#brandLink}
     */
    public void setBrandLink(String brandLink) {

        this.brandLink = brandLink;
    }

    /**
     * {@linkplain BrandAndVendorInfoVo#vendorName}
     */
    public String getVendorName() {

        return vendorName;
    }

    /**
     * {@linkplain BrandAndVendorInfoVo#vendorName}
     */
    public void setVendorName(String vendorName) {

        this.vendorName = vendorName;
    }

    /**
     * {@linkplain BrandAndVendorInfoVo#vendorLogo}
     */
    public String getVendorLogo() {

        return vendorLogo;
    }

    /**
     * {@linkplain BrandAndVendorInfoVo#vendorLogo}
     */
    public void setVendorLogo(String vendorLogo) {

        this.vendorLogo = vendorLogo;
    }

    /**
     * {@linkplain BrandAndVendorInfoVo#vendorLogoImgUrl}
     */
    public String getVendorLogoImgUrl() {

        return vendorLogoImgUrl;
    }

    /**
     * {@linkplain BrandAndVendorInfoVo#vendorLogoImgUrl}
     */
    public void setVendorLogoImgUrl(String vendorLogoImgUrl) {

        this.vendorLogoImgUrl = vendorLogoImgUrl;
    }

    /**
     * {@linkplain BrandAndVendorInfoVo#vendorLink}
     */
    public String getVendorLink() {

        return vendorLink;
    }

    /**
     * {@linkplain BrandAndVendorInfoVo#vendorLink}
     */
    public void setVendorLink(String vendorLink) {

        this.vendorLink = vendorLink;
    }
}
