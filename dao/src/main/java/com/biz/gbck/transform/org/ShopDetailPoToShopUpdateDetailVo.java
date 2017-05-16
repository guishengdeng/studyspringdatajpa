package com.biz.gbck.transform.org;


import com.biz.gbck.dao.mysql.po.org.ShopDetailPo;
import com.biz.gbck.enums.user.AuditRejectReason;
import com.biz.gbck.enums.user.AuditStatus;
import com.biz.gbck.vo.org.ShopUpdateDetailVo;
import com.google.common.base.Function;
import org.codelogger.utils.CollectionUtils;

import java.io.Serializable;
import java.util.Objects;

public class ShopDetailPoToShopUpdateDetailVo
    implements Function<ShopDetailPo, ShopUpdateDetailVo>, Serializable {

    public ShopUpdateDetailVo apply(ShopDetailPo po) {
        ShopUpdateDetailVo vo = null;
        if (po != null) {
            vo = new ShopUpdateDetailVo();
            vo.setCityId(po.getCity().getId());
            vo.setCityName(po.getCity().getName());
            vo.setDistrictId(po.getDistrict().getId());
            vo.setDistrictName(po.getDistrict().getName());
            vo.setProvinceId(po.getProvince().getId());
            vo.setProvinceName(po.getProvince().getName());
            vo.setDeliveryAddress(po.getDeliveryAddress());
            vo.setAuditStatus(po.getAuditStatus());
            vo.setReason(po.getReason());
            Integer auditStatus = po.getAuditStatus();
            String msg = null;
            if (Objects.equals(auditStatus, AuditStatus.AUDIT_FAILED.getValue())) {
                msg = CollectionUtils.join(AuditRejectReason
                    .parseRejectReasons(po.getRejectReason().split("\\D")), ",");
            } else if (Objects.equals(auditStatus,
                AuditStatus.NORMAL_AND_NEW_UPDATE_AUDIT_FAILED.getValue())) {
                msg = "您请求更改收货地址不成功";
            } else if (Objects.equals(auditStatus,
                AuditStatus.NORMAL_AND_HAS_NEW_UPDATE_WAIT_FOR_AUDIT.getValue())) {
                msg = "资料正在审核中，请耐心等候";
            }
            vo.setRejectReasons(msg);
        }
        return vo;
    }

}
