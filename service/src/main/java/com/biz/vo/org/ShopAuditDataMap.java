package com.biz.vo.org;

import com.biz.gbck.dao.mysql.po.org.ShopDetailPo;
import com.biz.gbck.dao.mysql.po.org.ShopQualificationPo;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by defei on 3/31/16.
 */
public class ShopAuditDataMap extends HashMap<Long, ShopAuditVo> {

    public ShopAuditDataMap(List<ShopDetailPo> shopDetails,
                            List<ShopQualificationPo> shopQualifications) {

        if (CollectionUtils.isNotEmpty(shopDetails)) {
            for (ShopDetailPo shopDetail : shopDetails) {
                getShopAuditVo(shopDetail.getShop().getId()).setShopDetail(shopDetail);
            }
        }
        if (CollectionUtils.isNotEmpty(shopQualifications)) {
            for (ShopQualificationPo shopQualification : shopQualifications) {
                getShopAuditVo(shopQualification.getShop().getId())
                    .setShopQualification(shopQualification);
            }
        }
    }

    private ShopAuditVo getShopAuditVo(Long shopId) {
        ShopAuditVo shopAuditVo = get(shopId);
        if (shopAuditVo == null) {
            shopAuditVo = new ShopAuditVo();
            put(shopId, shopAuditVo);
        }
        return shopAuditVo;
    }

}
