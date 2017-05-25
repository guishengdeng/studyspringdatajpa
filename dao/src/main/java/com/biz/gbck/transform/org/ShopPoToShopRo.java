package com.biz.gbck.transform.org;

import com.biz.gbck.dao.mysql.po.org.ShopPo;
import com.biz.gbck.dao.mysql.po.org.UserPo;
import com.biz.gbck.dao.redis.ro.org.ShopRo;
import com.google.common.base.Function;
import org.apache.commons.collections.CollectionUtils;

import java.io.Serializable;

public class ShopPoToShopRo implements Function<ShopPo, ShopRo>, Serializable {

    @Override
    public ShopRo apply(ShopPo po) {
        ShopRo ro = null;
        if (po != null) {
            ro = new ShopRo();
            ro.setId(String.valueOf(po.getId()));
            ro.setName(po.getName());
            ro.setCorporateName(po.getCorporateName());
            ro.setAvatar(po.getAvatar());
            ro.setMobile(po.getMobile());
            String userIds = null;
            if (CollectionUtils.isNotEmpty(po.getUsers())) {
                StringBuilder stringBuilder = new StringBuilder();
                for (UserPo userPo : po.getUsers()) {
                    stringBuilder.append(userPo.getId()).append(",");
                }
                userIds = stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString();
            }
            ro.setUsers(userIds);
            ro.setShopTypeId(po.getShopType() != null ? po.getShopType().getId() : null);
            ro.setMobile(po.getMobile());
            ro.setTel(po.getTel());
//            ro.setDepotId(po.getDepot() == null ? null : po.getDepot().getId());
//            ro.setDeliveryDepotId(po.getDeliveryDepot()==null ? null :  po.getDeliveryDepot().getId());
            ro.setSupportPaymentIds(po.getSupportPaymentIds());
            ro.setDisabledPaymentIds(po.getDisabledPaymentIds());
            ro.setLatitude(po.getLatitude());
            ro.setLatitude(po.getLatitude());
            ro.setDeliveryName(po.getDeliveryName());
            ro.setDeliveryMobile(po.getDeliveryMobile());
            ro.setDeliveryAddress(po.getDeliveryAddress());
            ro.setShopAddress(po.getShopAddress());
            ro.setProvinceId(po.getProvince() != null ? (long) po.getProvince().getId() : null);
            ro.setCityId(po.getCity() != null ? (long) po.getCity().getId() : null);
            ro.setDistrictId(po.getDistrict() != null ? (long) po.getDistrict().getId() : null);
            ro.setBusinessLicenceId(po.getBusinessLicenceId());
            ro.setBusinessLicenceName(po.getBusinessLicenceName());
            ro.setBusinessLicence(po.getBusinessLicence());
            ro.setShopPhoto(po.getShopPhoto());
            ro.setLiquorSellLicenceId(po.getLiquorSellLicenceId());
            ro.setLiquorSellLicence(po.getLiquorSellLicence());
            ro.setCorporateId(po.getCorporateId());
            ro.setCorporateIdPhoto(po.getCorporateIdPhoto());
            ro.setPaymentPassword(po.getPaymentPassword());

//            String priceTags = "";
//            if (CollectionUtils.isNotEmpty(po.getPriceTags())) {
//                StringBuilder stringBuilder = new StringBuilder();
//                for (PriceTagPo priceTagPo : po.getPriceTags()) {
//                    stringBuilder.append(priceTagPo.getId()).append(",");
//                }
//                priceTags = stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString();
//            }
//            ro.setPriceTags(priceTags);

//            String businessTags = "";
//            if (CollectionUtils.isNotEmpty(po.getBusinessTags())) {
//                StringBuilder stringBuilder = new StringBuilder();
//                for (BusinessTagPo businessTagPo : po.getBusinessTags()) {
//                    stringBuilder.append(businessTagPo.getId()).append(",");
//                }
//                businessTags = stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString();
//            }
//            ro.setBusinessTags(businessTags);

//            String saleAreas = "";
//            if (CollectionUtils.isNotEmpty(po.getSaleAreas())) {
//                StringBuilder stringBuilder = new StringBuilder();
//                for (SaleAreaPo saleAreaPo : po.getSaleAreas()) {
//                    stringBuilder.append(saleAreaPo.getId()).append(",");
//                }
//                saleAreas = stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString();
//            }
//            ro.setSaleAreas(saleAreas);

            ro.setInviterCode(po.getInviterCode());
            ro.setDetailAuditStatus(po.getDetailAuditStatus());
            ro.setQualificationAuditStatus(po.getQualificationAuditStatus());
            ro.setStatus(po.getStatus().getValue());
            ro.setSupportPaymentIds(po.getSupportPaymentIds());
            ro.setCreateTime(po.getCreateTime());
            ro.setParentId(po.getParent() == null ? null : po.getParent().getId());
            ro.setChannel(po.getChannel());
            ro.setChannelUserId(po.getChannelUserId());
//            ro.setShopLevel(po.getShopLevel());
            if(po.getPartner() != null){
                ro.setPartnerId(po.getPartner().getId());
            }
        }
        return ro;
    }

}
