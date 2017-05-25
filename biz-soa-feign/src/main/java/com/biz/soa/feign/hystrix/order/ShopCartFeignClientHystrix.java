package com.biz.soa.feign.hystrix.order;

import com.biz.gbck.vo.cart.*;
import com.biz.gbck.vo.soa.MicroServiceResult;
import com.biz.soa.feign.client.order.ShopCartFeignClient;
import org.springframework.stereotype.Component;

/**
 *
 * @author lei
 * @date 2017年05月16日
 * @reviewer
 * @see
 */
@Component
public class ShopCartFeignClientHystrix implements ShopCartFeignClient {

    @Override
    public MicroServiceResult<ShopCartRespVo> addCartItem(ShopCartItemAddReqVo reqVo) {
        return null;
    }

    @Override
    public MicroServiceResult<ShopCartRespVo> getCartInfo(ShopCartListReqVo reqVo) {
        return null;
    }

    @Override
    public MicroServiceResult<ShopCartRespVo> deleteCartItems(ShopCartItemBatchDeleteReqVo reqVo) {
        return null;
    }

    @Override
    public MicroServiceResult<ShopCartRespVo> updateItemQuantity(ShopCartItemUpdateReqVo reqVo) {
        return null;
    }

    @Override
    public MicroServiceResult<ShopCartNumRespVo> getCartNum(ShopCartNumReqVo reqVo) {
        return null;
    }

    @Override
    public MicroServiceResult<String> getTestString() {
        return null;
    }
}
