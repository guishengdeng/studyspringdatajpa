package com.biz.transform.product;

import com.biz.gbck.dao.mysql.po.product.SaleTag;
import com.biz.gbck.vo.product.backend.UpdateSaleTagVo;
import com.google.common.base.Function;
import javax.annotation.Nullable;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2016/12/26
 */
public class SaleTag2UpdateSaleTagVo implements Function<SaleTag, UpdateSaleTagVo> {
    @Nullable
    @Override
    public UpdateSaleTagVo apply(@Nullable SaleTag input) {
        UpdateSaleTagVo resp = new UpdateSaleTagVo();
        resp.setId(input.getId());
        resp.setName(input.getName());
        resp.setLogo(input.getLogo());
        resp.setIdx(input.getIdx());
        resp.setRawHtml(input.getRawHtml());
        resp.setDescription(input.getDescription());
        resp.setStatus(input.getStatus());
        resp.setVendorId(input.getVendorId());
        resp.setCategoryId(input.getCategory().getId());
        return resp;
    }
}
