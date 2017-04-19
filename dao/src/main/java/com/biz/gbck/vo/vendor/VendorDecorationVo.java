package com.biz.gbck.vo.vendor;

import com.biz.gbck.vo.IRequestVo;
import java.util.List;
import java.util.Set;

/**
 * 店铺装饰Vo
 *
 * @author LGJ
 * @date 2016/12/28
 */
public class VendorDecorationVo implements IRequestVo {

    private static final long serialVersionUID = 2268978438330360761L;

    private VendorVo vendor;

    private String id;

    private String seoTitle;

    private String seoKeywords;

    private String seoDecription;

    //顶部图
    private String topImage;

    //顶部图链接
    private String topImageHyperLink;

    //banner图
    private List<String> bannerImage;

    private String vendorDetail;

    private String remark;

    //banner图链接
    private List<String> bannerImageHyperLink;

    //超链接图片
    private String hyperlinkImage;

    //超链接
    private String hyperlink;

    //banner图和链接
    private List<DecorationImageAndLink> decorationImageAndLink;

    //店铺图片资源
    private Set<VendorResourceVo> vendorResourceVo;


    public Set<VendorResourceVo> getVendorResourceVo() {
        return vendorResourceVo;
    }

    public void setVendorResourceVo(Set<VendorResourceVo> vendorResourceVo) {
        this.vendorResourceVo = vendorResourceVo;
    }

    public String getTopImageHyperLink() {
        return topImageHyperLink;
    }

    public void setTopImageHyperLink(String topImageHyperLink) {
        this.topImageHyperLink = topImageHyperLink;
    }

    public List<DecorationImageAndLink> getDecorationImageAndLink() {
        return decorationImageAndLink;
    }

    public void setDecorationImageAndLink(List<DecorationImageAndLink> decorationImageAndLink) {
        this.decorationImageAndLink = decorationImageAndLink;
    }

    public String getHyperlink() {
        return hyperlink;
    }

    public void setHyperlink(String hyperlink) {
        this.hyperlink = hyperlink;
    }

    public List<String> getBannerImageHyperLink() {
        return bannerImageHyperLink;
    }

    public void setBannerImageHyperLink(List<String> bannerImageHyperLink) {
        this.bannerImageHyperLink = bannerImageHyperLink;
    }

    public String getHyperlinkImage() {
        return hyperlinkImage;
    }

    public void setHyperlinkImage(String hyperlinkImage) {
        this.hyperlinkImage = hyperlinkImage;
    }

    public VendorVo getVendor() {
        return vendor;
    }

    public void setVendor(VendorVo vendor) {
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


    public List<String> getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(List<String> bannerImage) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
