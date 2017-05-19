package com.biz.soa.order.controller;

import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.vo.cart.*;
import com.biz.service.cart.ShopCartService;
import com.biz.soa.base.SoaBaseController;
import com.biz.support.web.handler.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 购物车soa controller
 *
 * @author lei
 * @date 2017年05月14日
 * @reviewer
 * @see
 */
@RestController
@RequestMapping(value = "/soa/shopcart")
public class SoaShopCartController extends SoaBaseController {

    @Autowired
    private ShopCartService shopCartService;

    /**
     * 添加购物车
     */
    @RequestMapping("/app/add")
    public JSONResult addCartItem(@RequestBody ShopCartItemAddReqVo reqVo) throws DepotNextDoorException {
        shopCartService.addCartItem(reqVo);
        return new JSONResult();
    }


    /**
     * 获取购物车列表
     */
    @RequestMapping("/app/list")
    public JSONResult getCartInfo(@RequestBody ShopCartListReqVo reqVo) throws DepotNextDoorException {
        ShopCartRespVo respVo = shopCartService.getCartInfo(reqVo);
        return new JSONResult(respVo);
    }

    /**
     * 添加购物车
     */
    @RequestMapping("/app/delete")
    public JSONResult deleteCartItem(@RequestBody ShopCartItemBatchDeleteReqVo reqVo) throws DepotNextDoorException {
        shopCartService.deleteCartItems(reqVo);
        ShopCartRespVo respVo = shopCartService.getCartInfo(new ShopCartListReqVo());
        return new JSONResult(respVo);
    }

    @RequestMapping("/app/cartNum")
    @ResponseBody
    public JSONResult cartNum(@RequestBody ShopCartNumReqVo reqVo) throws DepotNextDoorException {
        ShopCartNumRespVo respVo = shopCartService.getCartNum(reqVo);
        return new JSONResult(respVo);
    }



    @GetMapping(value = "/test")
    public String getTestString() {
        return "I am a test String";
    }

}
