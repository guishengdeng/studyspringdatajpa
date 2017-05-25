package com.biz.rest.controller.cart;

import com.biz.gbck.vo.cart.*;
import com.biz.rest.controller.BaseRestController;
import com.biz.rest.util.RestUtil;
import com.biz.service.cart.ShopCartService;
import com.biz.soa.feign.client.order.ShopCartFeignClient;
import com.biz.support.web.handler.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 购物车controller
 *
 * @author lei
 * @date 2017年04月25日
 * @reviewer
 * @see
 */
@RestController
@RequestMapping("/cart")
public class ShopCartController extends BaseRestController {


    @Autowired(required = false)
    private ShopCartService cartService;

    @Autowired
    private ShopCartFeignClient shopCartFeignClient;

    /**
     * 添加购物车
     */
    @RequestMapping("add")
    public JSONResult addCartItem(HttpServletRequest request) {
        ShopCartItemAddReqVo reqVo = RestUtil.parseBizData(request, ShopCartItemAddReqVo.class);
        return RestUtil.parseBizResult(shopCartFeignClient.addCartItem(reqVo));
    }


    /**
     * 获取购物车列表
     */
    @RequestMapping("list")
    public JSONResult getCartInfo(HttpServletRequest request) {
        ShopCartListReqVo reqVo = RestUtil.parseBizData(request, ShopCartListReqVo.class);
        return RestUtil.parseBizResult(shopCartFeignClient.getCartInfo(reqVo));
    }

    /**
     * 添加购物车
     */
    @RequestMapping("updateQuantity")
    public JSONResult updateItemQuantity(HttpServletRequest request) {
        ShopCartItemUpdateReqVo reqVo = RestUtil.parseBizData(request, ShopCartItemUpdateReqVo.class);
        return RestUtil.parseBizResult(shopCartFeignClient.updateItemQuantity(reqVo));
    }

    /**
     * 添加购物车
     */
    @RequestMapping("delete")
    public JSONResult deleteCartItem(HttpServletRequest request) {
        ShopCartItemBatchDeleteReqVo reqVo = RestUtil.parseBizData(request, ShopCartItemBatchDeleteReqVo.class);
        return RestUtil.parseBizResult(shopCartFeignClient.deleteCartItems(reqVo));
    }

    @RequestMapping("cartNum")
    @ResponseBody
    public JSONResult cartNum(HttpServletRequest request) {
        ShopCartNumReqVo reqVo = RestUtil.parseBizData(request, ShopCartNumReqVo.class);
        return RestUtil.parseBizResult(shopCartFeignClient.getCartNum(reqVo));
    }


}
