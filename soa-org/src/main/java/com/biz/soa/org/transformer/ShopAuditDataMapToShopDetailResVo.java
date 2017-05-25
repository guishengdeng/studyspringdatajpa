package com.biz.soa.org.transformer;

import com.biz.gbck.dao.mysql.po.org.ShopDetailPo;
import com.biz.gbck.dao.mysql.po.org.ShopQualificationPo;
import com.biz.gbck.vo.org.ShopAuditDataMap;
import com.biz.gbck.vo.org.ShopAuditVo;
import com.biz.gbck.vo.org.ShopDetailResVo;
import com.biz.service.org.interfaces.ShopService;
import com.google.common.base.Function;
import org.codelogger.utils.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * Created by root on 17-5-18.
 */
public class ShopAuditDataMapToShopDetailResVo implements Function<ShopAuditDataMap, ShopDetailResVo>, Serializable {

    private static final Logger logger = LoggerFactory.getLogger(ShopAuditDataMapToShopDetailResVo.class);
    @Override
    public ShopDetailResVo apply(ShopAuditDataMap input) {
        ShopDetailResVo vo=null;
        if(input != null){
            vo=new ShopDetailResVo();
            ShopAuditVo shopAuditVo = CollectionUtils.getFirstOrNull(input.values());
            if(shopAuditVo !=null){
                ShopDetailPo detailPo=shopAuditVo.getShopDetail();
                ShopQualificationPo qualificationPo=shopAuditVo.getShopQualification();
                if(detailPo != null && qualificationPo != null){
                    if(detailPo.getShop() != null){
                        vo.setShopId(detailPo.getShop().getId());
                    }
                    vo.setShopDetailId(detailPo.getId());
                    vo.setName(detailPo.getName());
                    if(detailPo.getShopType() != null){
                        vo.setShopTypeId(detailPo.getShopType().getId());
                        vo.setShopTypeName(detailPo.getShopType().getName());
                    }
                    vo.setCorporateName(detailPo.getCorporateName());
                    vo.setMobile(detailPo.getMobile());
                    if(detailPo.getShop() != null && detailPo.getShop().getPartner() != null ){
                        vo.setPartnerName(detailPo.getShop().getPartner().getName());
                        vo.setPartnerId(detailPo.getShop().getPartner().getId());
                    }
                    if(detailPo.getProvince() != null){
                        vo.setProvinceId(detailPo.getProvince().getId());
                    }
                   if(detailPo.getCity() != null){
                       vo.setCityId(detailPo.getCity().getId());
                   }
                    if(detailPo.getDistrict() != null){
                        vo.setDistrictId(detailPo.getDistrict().getId());
                    }
                    vo.setDeliveryAddress(detailPo.getDeliveryAddress());
                    vo.setReason(detailPo.getReason());
                    vo.setCreateTime(detailPo.getCreateTime());
                    vo.setAuditStatus(detailPo.getAuditStatus());
                    vo.setCorporateId(qualificationPo.getCorporateId());
                    vo.setShopQualificationId(qualificationPo.getId());
                    vo.setShopPhoto(qualificationPo.getShopPhoto());
                    vo.setBusinessLicence(qualificationPo.getBusinessLicence());
                    vo.setLiquorSellLicence(qualificationPo.getLiquorSellLicence());
                    vo.setCorporateIdPhoto(qualificationPo.getCorporateIdPhoto());
                    vo.setBusinessLicenceName(qualificationPo.getBusinessLicenceName());
                    vo.setBusinessLicenceId(qualificationPo.getBusinessLicenceId());
                    vo.setLiquorSellLicenceId(qualificationPo.getLiquorSellLicenceId());
                    logger.info("ShopDetailResVo vo is {}", vo.toString());
                }
            }
        }
        return vo;
    }
}
