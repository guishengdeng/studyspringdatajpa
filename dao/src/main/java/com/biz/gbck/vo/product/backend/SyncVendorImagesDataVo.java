package com.biz.gbck.vo.product.backend;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 导出商户相关图片数据的Vo
 *
 * @author zhangcheng
 * @date 2017/3/22
 * @reviewer
 * @see
 */
public class SyncVendorImagesDataVo implements Serializable {

    private static final long serialVersionUID = -8323517448641845854L;

    /**
     * 店铺名称
     */
    private String vendorName;

    /**
     * 商标注册复印件
     */
    private String trademarkRegistrationPhotoCopy;

    /**
     * 税务登记证复印件
     */
    private String taxRegistrationPhotoCopy;

    /**
     * 商铺logo
     */
    private String logo;

    /**
     * 组织机构代码证复印件
     */
    private String enterpriseOrganizationCodePhotoCopy;

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getTrademarkRegistrationPhotoCopy() {
        return trademarkRegistrationPhotoCopy;
    }

    public void setTrademarkRegistrationPhotoCopy(String trademarkRegistrationPhotoCopy) {
        this.trademarkRegistrationPhotoCopy = trademarkRegistrationPhotoCopy;
    }

    public String getTaxRegistrationPhotoCopy() {
        return taxRegistrationPhotoCopy;
    }

    public void setTaxRegistrationPhotoCopy(String taxRegistrationPhotoCopy) {
        this.taxRegistrationPhotoCopy = taxRegistrationPhotoCopy;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getEnterpriseOrganizationCodePhotoCopy() {
        return enterpriseOrganizationCodePhotoCopy;
    }

    public void setEnterpriseOrganizationCodePhotoCopy(String enterpriseOrganizationCodePhotoCopy) {
        this.enterpriseOrganizationCodePhotoCopy = enterpriseOrganizationCodePhotoCopy;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
