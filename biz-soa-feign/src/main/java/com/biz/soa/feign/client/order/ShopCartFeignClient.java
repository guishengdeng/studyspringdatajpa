package com.biz.soa.feign.client.order;

import com.biz.gbck.vo.cart.*;
import com.biz.soa.feign.hystrix.order.ShopCartFeignClientHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author lei
 * @date 2017年05月16日
 * @reviewer
 * @see
 */
@FeignClient(name = "soa-shopcart", fallback = ShopCartFeignClientHystrix.class)
public interface ShopCartFeignClient {

    @RequestMapping(value = "/soa/shopcart/app/test", method = RequestMethod.GET)
    String getTestString();

    @RequestMapping(value = "/soa/shopcart/app/add", method = RequestMethod.POST)
    void addCartItem(ShopCartItemAddReqVo reqVo);

    @RequestMapping(value = "/soa/shopcart/app/list", method = RequestMethod.POST)
    ShopCartRespVo getCartInfo(ShopCartListReqVo reqVo);

    @RequestMapping(value = "/soa/shopcart/app/cartNum", method = RequestMethod.POST)
    ShopCartNumRespVo getCartNum(ShopCartNumReqVo reqVo);

    void deleteCartItems(ShopCartItemBatchDeleteReqVo reqVo);
}
