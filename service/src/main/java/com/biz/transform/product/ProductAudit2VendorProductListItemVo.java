package com.biz.transform.product;

import com.biz.gbck.dao.mysql.po.product.ProductAudit;
import com.biz.gbck.enums.product.SaleStatusEnum;
import com.biz.gbck.vo.product.backend.VendorProductListItemVo;
import com.google.common.base.Function;
import javax.annotation.Nullable;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2017/2/22
 */
public class ProductAudit2VendorProductListItemVo implements Function<ProductAudit, VendorProductListItemVo> {

    @Nullable
    @Override
    public VendorProductListItemVo apply(@Nullable ProductAudit input) {
        VendorProductListItemVo respVo = new VendorProductListItemVo();
        respVo.setId(String.valueOf(input.getProductId()));
        respVo.setAuditStatus(input.getAuditStatus().getDescription());
        respVo.setLastUpdateTime(input.getUpdateTimestamp());
        respVo.setProductCode(input.getProductCode());
        respVo.setProductName(input.getName());
        respVo.setSaleStatus(SaleStatusEnum.OFF_SALE.getDescription());
        respVo.setLogo(input.getLogo());
        respVo.setProductAuditId(String.valueOf(input.getId()));
        return respVo;
    }

}
