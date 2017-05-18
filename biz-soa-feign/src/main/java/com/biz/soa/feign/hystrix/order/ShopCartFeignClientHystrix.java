package com.biz.soa.feign.hystrix.order;

import com.biz.gbck.vo.cart.*;
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
    public String getTestString() {
        return null;
    }

    @Override
    public void addCartItem(ShopCartItemAddReqVo reqVo) {

    }

    @Override
    public ShopCartRespVo getCartInfo(ShopCartListReqVo reqVo) {
        return null;
    }

    @Override
    public ShopCartNumRespVo getCartNum(ShopCartNumReqVo reqVo) {
        return null;
    }

    @Override
    public void deleteCartItems(ShopCartItemBatchDeleteReqVo reqVo) {

    }
}
