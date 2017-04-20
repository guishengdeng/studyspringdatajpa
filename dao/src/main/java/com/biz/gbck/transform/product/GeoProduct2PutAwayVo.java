package com.biz.gbck.transform.product;

import com.biz.core.util.MoneyConverter;
import com.biz.gbck.dao.mysql.po.product.bbc.GeoProduct;
import com.biz.gbck.vo.product.backend.PutAwayVo;
import com.google.common.base.Function;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2017/2/24
 */
public class GeoProduct2PutAwayVo implements Function<GeoProduct, PutAwayVo> {
    @Override
    public PutAwayVo apply(GeoProduct input) {
        PutAwayVo respVo = new PutAwayVo();
        respVo.setVendorId(input.getVendorId());
        respVo.setProductCode(input.getProduct().getProductCode());
        if (input.getSalePrice() != null) {
            respVo.setFinalPrice(MoneyConverter.instance.fen2yuan(input.getSalePrice()));
        }
        respVo.setGeoLevel(input.getGeoLevel());
        respVo.setIds(input.getGeoIdsString());
        respVo.setMinPrice(input.getLimitPrice());
        if (input.getProduct() != null) {
            respVo.setProductId(String.valueOf(input.getProduct().getId()));
        }
        if (input.getMarketPrice() != null) {
            respVo.setMarketPrice(MoneyConverter.instance.fen2yuan(input.getMarketPrice()));
        }
        return respVo;
    }
}
