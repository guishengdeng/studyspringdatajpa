package com.biz.gbck.transform.product;

import com.biz.gbck.dao.mysql.po.product.ProductAudit;
import com.biz.gbck.vo.product.backend.VendorProductAuditListItemVo;
import com.google.common.base.Function;

/**
 * 转换器(商户商品审核记录 --> 商户商品审核记录列表项 Vo)
 *
 * @author david-liu
 * @date 2016年12月25日
 * @reviewer
 * @see
 */
public class ProductAudit2VendorProductAuditListItemVo implements Function<ProductAudit, VendorProductAuditListItemVo> {

    @Override
    public VendorProductAuditListItemVo apply(ProductAudit productAudit) {
        VendorProductAuditListItemVo vo = new VendorProductAuditListItemVo();
        vo.setId(String.valueOf(productAudit.getId()));
        vo.setProductCode(productAudit.getProductCode());
        vo.setProductName(productAudit.getName());
        vo.setAuditCreateTime(productAudit.getUpdateTimestamp());
        vo.setAuditMessage(productAudit.getAuditMessage());
        vo.setAuditStatus(productAudit.getAuditStatus().getDescription());
        vo.setLocked(productAudit.getLocked());
        return vo;
    }
}
