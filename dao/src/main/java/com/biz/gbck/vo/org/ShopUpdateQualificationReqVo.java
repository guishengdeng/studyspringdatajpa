package com.biz.gbck.vo.org;


import com.biz.gbck.common.vo.CommonReqVoBindUserId;

import javax.validation.constraints.NotNull;

/**
 * Created by defei on 3/16/16.
 */
public class ShopUpdateQualificationReqVo extends CommonReqVoBindUserId {


    /**
     * 商户id
     */
    private Long shopId;


    /**
     * 营业执照
     */
    @NotNull(message = "营业执照不能为空") private String businessLicence;

    /**
     * 门头照片
     */
    @NotNull(message = "门头照片不能为空") private String shopPhoto;

    /**
     * 酒类流通许可证
     */
    private String liquorSellLicence;

    /**
     * 法人身份证
     */
    private String corporateIdPhoto;

    @Override public String toString() {
        return "ShopUpdateQualificationReqVo{" +
            "userId=" + userId +
            ", shopId=" + shopId +
            ", businessLicence='" + businessLicence + '\'' +
            ", shopPhoto='" + shopPhoto + '\'' +
            ", liquorSellLicence='" + liquorSellLicence + '\'' +
            ", corporateIdPhoto='" + corporateIdPhoto + '\'' +
            '}';
    }


    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getBusinessLicence() {
        return businessLicence;
    }

    public void setBusinessLicence(String businessLicence) {
        this.businessLicence = businessLicence;
    }

    public String getShopPhoto() {
        return shopPhoto;
    }

    public void setShopPhoto(String shopPhoto) {
        this.shopPhoto = shopPhoto;
    }

    public String getLiquorSellLicence() {
        return liquorSellLicence;
    }

    public void setLiquorSellLicence(String liquorSellLicence) {
        this.liquorSellLicence = liquorSellLicence;
    }

    public String getCorporateIdPhoto() {
        return corporateIdPhoto;
    }

    public void setCorporateIdPhoto(String corporateIdPhoto) {
        this.corporateIdPhoto = corporateIdPhoto;
    }
}
