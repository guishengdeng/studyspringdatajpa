package com.biz.gbck.transform.product;

import com.biz.gbck.dao.mysql.po.product.SaleTag;
import com.biz.gbck.vo.product.backend.SaleTagListItemVo;
import com.google.common.base.Function;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2016/12/22
 */
public class SaleTag2SaleTagListItemVo implements Function<SaleTag, SaleTagListItemVo> {
    @Override
    public SaleTagListItemVo apply(SaleTag input) {
        SaleTagListItemVo resp = new SaleTagListItemVo();
        resp.setId(String.valueOf(input.getId()));
        resp.setStatus(input.getStatus());
        resp.setName(input.getName());
        resp.setCreateTimeStamp(input.getCreateTimestamp());
        resp.setIdx(String.valueOf(input.getIdx()));
        return resp;
    }
}
