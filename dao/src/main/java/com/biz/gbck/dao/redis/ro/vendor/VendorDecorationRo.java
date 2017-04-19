package com.biz.gbck.dao.redis.ro.vendor;

import com.biz.redis.annotation.Ro;
import com.biz.redis.annotation.RoSortedSet;
import com.biz.redis.bean.BaseRedisObject;
import java.io.Serializable;

@Ro(key = "ro:VendorDecorationRo")
@RoSortedSet(key = "list", score = "id")
public class VendorDecorationRo extends BaseRedisObject<Long> implements Serializable {

    private String seoTitle;

    private String seoKeywords;

    private String seoDecription;

    private String topImage;

    private String bannerImage;

    private String bannerImageHyperLink;

    private String vendorDetail;

    private String remark;

    private String hyperlinkImage;

    private String hyperlink;

    private Long vendorId;

    private String vendorResources;


    public String getVendorResources() {
        return vendorResources;
    }

    public void setVendorResources(String vendorResources) {
        this.vendorResources = vendorResources;
    }

    public String getBannerImageHyperLink() {
        return bannerImageHyperLink;
    }

    public void setBannerImageHyperLink(String bannerImageHyperLink) {
        this.bannerImageHyperLink = bannerImageHyperLink;
    }

    public String getHyperlink() {
        return hyperlink;
    }

    public void setHyperlink(String hyperlink) {
        this.hyperlink = hyperlink;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getHyperlinkImage() {
        return hyperlinkImage;
    }

    public void setHyperlinkImage(String hyperlinkImage) {
        this.hyperlinkImage = hyperlinkImage;
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


}
