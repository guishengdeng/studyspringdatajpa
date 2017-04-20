package com.biz.gbck.dao.mysql.po.vendor;

import com.biz.gbck.dao.mysql.converter.VendorResourceConverter;
import com.biz.gbck.dao.mysql.po.product.bbc.Vendor;
import com.biz.support.jpa.po.BaseEntity;
import java.util.Set;
import javax.persistence.*;

/**
 * 店铺装饰
 *
 * @author yanweijin
 * @date 2016/12/19
 */
@Entity
@Table(name = "ven_vendor_decoration")
public class VendorDecoration extends BaseEntity {

    @OneToOne(fetch = FetchType.EAGER)
    private Vendor vendor;

    @Column(length = 100)
    private String seoTitle;

    @Column(length = 200)
    private String seoKeywords;

    @Column(length = 500)
    private String seoDecription;

    @Column(length = 100)
    private String topImage;

    @Column(length = 500)
    private String bannerImage;

    @Column(length = 500)
    private String bannerImageHyperLink;

    @Column(columnDefinition = "TEXT")
    private String vendorDetail;

    @Column(length = 300)
    private String remark;

    @Column(length = 100)
    private String hyperlinkImage;

    @Column(length = 100)
    private String hyperlink;

    /**
     * 店铺图片资源,用于替代 banner 图,左侧广告图等资源
     */
    @Convert(converter = VendorResourceConverter.class)
    @Column(columnDefinition = "TEXT")
    private Set<VendorResource> vendorResources;

    public String getHyperlink() {
        return hyperlink;
    }

    public void setHyperlink(String hyperlink) {
        this.hyperlink = hyperlink;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public String getSeoTitle() {
        return seoTitle;
    }

    public void setSeoTitle(String seoTitle) {
        this.seoTitle = seoTitle;
    }

    public String getSeoKeywords() {
        return seoKeywords;
    }

    public void setSeoKeywords(String seoKeywords) {
        this.seoKeywords = seoKeywords;
    }

    public String getSeoDecription() {
        return seoDecription;
    }

    public void setSeoDecription(String seoDecription) {
        this.seoDecription = seoDecription;
    }

    public String getTopImage() {
        return topImage;
    }

    public void setTopImage(String topImage) {
        this.topImage = topImage;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public String getVendorDetail() {
        return vendorDetail;
    }

    public void setVendorDetail(String vendorDetail) {
        this.vendorDetail = vendorDetail;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getHyperlinkImage() {
        return hyperlinkImage;
    }

    public void setHyperlinkImage(String hyperlinkImage) {
        this.hyperlinkImage = hyperlinkImage;
    }

    public String getBannerImageHyperLink() {
        return bannerImageHyperLink;
    }

    public void setBannerImageHyperLink(String bannerImageHyperLink) {
        this.bannerImageHyperLink = bannerImageHyperLink;
    }

    public Set<VendorResource> getVendorResources() {
        return vendorResources;
    }

    public void setVendorResources(Set<VendorResource> vendorResources) {
        this.vendorResources = vendorResources;
    }
}
