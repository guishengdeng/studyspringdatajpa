package com.biz.soa.order.controller;

import com.biz.gbck.vo.cart.*;
import com.biz.gbck.vo.soa.MicroServiceResult;
import com.biz.service.cart.ShopCartService;
import com.biz.soa.base.SoaBaseController;
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
    public MicroServiceResult<ShopCartRespVo> addCartItem(@RequestBody ShopCartItemAddReqVo reqVo) {
        try {
            shopCartService.addCartItem(reqVo);
            ShopCartListReqVo cartListReqVo = new ShopCartListReqVo();
            cartListReqVo.setUserId(reqVo.getUserId());
            return render200(shopCartService.getCartItemsInfo(cartListReqVo));
        } catch (Exception e) {
            return render500(e);
        }
    }


    /**
     * 获取购物车列表
     */
    @RequestMapping("/app/list")
    public MicroServiceResult<ShopCartRespVo> getCartInfo(@RequestBody ShopCartListReqVo reqVo) {
        try {
            return render200(shopCartService.getCartItemsInfo(reqVo));
        } catch (Exception e) {
            return render500(e);
        }
    }

    /**
     * 添加购物车
     */
    @RequestMapping("/app/delete")
    public MicroServiceResult<ShopCartRespVo> deleteCartItem(@RequestBody ShopCartItemBatchDeleteReqVo reqVo) {
        try {
            shopCartService.deleteCartItems(reqVo);
            ShopCartListReqVo cartListReqVo = new ShopCartListReqVo();
            cartListReqVo.setUserId(reqVo.getUserId());
            return render200(shopCartService.getCartItemsInfo(cartListReqVo));
        } catch (Exception e) {
            return render500(e);
        }
    }

    @RequestMapping("/app/cartNum")
    @ResponseBody
    public MicroServiceResult<ShopCartNumRespVo> cartNum(@RequestBody ShopCartNumReqVo reqVo) {
        try {
            return render200(shopCartService.getCartNum(reqVo));
        } catch (Exception e) {
            return render500(e);
        }
    }



    @GetMapping(value = "/test")
    public MicroServiceResult<String> getTestString() {
        return render200("I am a test String");
    }

}
