package com.biz.gbck.transform.product;

import com.biz.gbck.dao.mysql.po.tag.SaleTag;
import com.biz.gbck.vo.product.backend.UpdateSaleTagVo;
import com.google.common.base.Function;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2016/12/26
 */
public class SaleTag2UpdateSaleTagVo implements Function<SaleTag, UpdateSaleTagVo> {
    @Override
    public UpdateSaleTagVo apply(SaleTag input) {
        UpdateSaleTagVo resp = new UpdateSaleTagVo();
        resp.setId(input.getId());
        resp.setName(input.getName());
        resp.setShowName(input.getShowName());
        resp.setIdx(input.getIdx());
        resp.setDescription(input.getDescription());
        resp.setStatus(input.getStatus());
        resp.setCategoryId(input.getCategory().getId());
        return resp;
    }
}
