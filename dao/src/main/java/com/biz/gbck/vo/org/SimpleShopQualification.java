package com.biz.gbck.vo.org;

import com.biz.gbck.dao.mysql.po.org.ShopQualificationPo;
import com.biz.gbck.enums.user.AuditRejectReason;

import java.util.List;


public class SimpleShopQualification {

    public Long shopId;

    public String name;

    /**
     * 营业执照
     */
    public String businessLicence;

    /**
     * 门头照片
     */
    public String shopPhoto;

    /**
     * 酒类流通许可证
     */
    public String liquorSellLicence;


    /**
     * 法人身份证
     */
    public String corporateIdPhoto;

    public List<String> rejectReasons;

    public Integer auditStatus;

    public SimpleShopQualification(ShopQualificationPo po) {

        if (po != null) {
            shopId = po.getShop().getId();
            name = po.getShop().getName();
            businessLicence = po.getBusinessLicence();
            shopPhoto = po.getShopPhoto();
            liquorSellLicence = po.getLiquorSellLicence();
            corporateIdPhoto = po.getCorporateIdPhoto();
            String rejectReason = po.getRejectReason();
            rejectReasons = rejectReason == null ?
                null :
                AuditRejectReason.parseRejectReasons(rejectReason.split("\\D"));
            auditStatus = po.getAuditStatus();
        }
    }
}
