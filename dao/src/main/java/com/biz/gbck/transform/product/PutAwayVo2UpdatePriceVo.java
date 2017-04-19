package com.biz.gbck.transform.product;

import com.biz.gbck.vo.product.backend.PutAwayVo;
import com.biz.gbck.vo.product.backend.UpdatePriceVo;
import com.google.common.base.Function;

/**
 * @author 江南
 * @date 2017/1/18
 * @reviewer
 */
public class PutAwayVo2UpdatePriceVo implements Function<PutAwayVo, UpdatePriceVo> {

    @Override
    public UpdatePriceVo apply(PutAwayVo input) {
        UpdatePriceVo resp = new UpdatePriceVo();
        resp.setFinalPrice(input.getFinalPrice());
        resp.setMarketPrice(input.getMarketPrice());
        resp.setProductCode(input.getProductCode());
        resp.setProductId(input.getProductId());
        resp.setRegionIds(input.getIds());
        resp.setMinPrice(input.getMinPrice());
        return resp;
    }
}
