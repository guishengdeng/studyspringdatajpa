package com.biz.gbck.transform.order;

import com.biz.gbck.vo.cart.ShopCartItemRespVo;
import com.biz.gbck.vo.order.resp.OrderItemRespVo;
import com.google.common.base.Function;

/**
 * ShopCartItemRespVo -> OrderItemRespVo
 *
 * @author lei
 * @date 2017年05月10日
 * @reviewer
 * @see
 */
public class ShopCartItemRespVo2OrderItemRespVo implements Function<ShopCartItemRespVo, OrderItemRespVo> {
    @Override
    public OrderItemRespVo apply(ShopCartItemRespVo input) {
        if (input != null) {
            OrderItemRespVo itemVo = new OrderItemRespVo();
            itemVo.setProductId(Long.valueOf(input.getProductId()));
            itemVo.setProductCode(input.getProductCode());
            itemVo.setName(input.getName());
            itemVo.setLogo(input.getLogo());
            itemVo.setQuantity(input.getQuantity());
            itemVo.setMarketPrice(input.getMarketPrice());
            itemVo.setSalePrice(input.getSalePrice());
            itemVo.setStock(input.getStock());
            return itemVo;
        }
        return null;
    }
}
