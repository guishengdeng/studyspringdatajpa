package com.biz.rest.controller.cart;

import com.biz.rest.controller.BaseRestController;
import com.biz.service.cart.ShopCartService;
import com.biz.support.web.handler.JSONResult;
import com.biz.vo.cart.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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


    @Autowired
    private ShopCartService cartService;

    /**
     * 添加购物车
     */
    @RequestMapping("add")
    public JSONResult addCartItem(HttpServletRequest request) {
        ShopCartItemAddReqVo reqVo = super.parseBizData(request, ShopCartItemAddReqVo.class);

        try {
            cartService.addCartItem(reqVo);
            return new JSONResult();
        } catch (Exception e) {
            logger.error("添加购物车出错.", e);
            return new JSONResult(-1, "添加购物车出错");
        }
    }


    /**
     * 获取购物车列表
     */
    @RequestMapping("list")
    public JSONResult getCartInfo(HttpServletRequest request) {
        ShopCartListReqVo reqVo = super.parseBizData(request, ShopCartListReqVo.class);
        try {
            ShopCartRespVo respVo = cartService.getCartInfo(reqVo);
            return new JSONResult();
        } catch (Exception e) {
            logger.error("添加购物车出错.", e);
            return new JSONResult(-1, "添加购物车出错");
        }
    }

    /**
     * 添加购物车
     */
    @RequestMapping("batchDelete")
    public JSONResult deleteCartItem(HttpServletRequest request) {
        ShopCartItemBatchDeleteRespVo reqVo = super.parseBizData(request, ShopCartItemBatchDeleteRespVo.class);
        try {
            cartService.deleteCartItems(reqVo);
            return new JSONResult();
        } catch (Exception e) {
            logger.error("添加购物车出错.", e);
            return new JSONResult(-1, "添加购物车出错");
        }
    }

    /**
     * 更新购物车数量
     */
    @RequestMapping("updateQuantity")
    public JSONResult updateCartItemQuantity(HttpServletRequest request) {
        ShopCartItemUpdateReqVo reqVo = super.parseBizData(request, ShopCartItemUpdateReqVo.class);
        try {
            ShopCartItemUpdateRespVo respVo = cartService.updateCartItemQuantity(reqVo);
            return new JSONResult(respVo);
        } catch (Exception e) {
            logger.error("添加购物车出错.", e);
            return new JSONResult(-1, "添加购物车出错");
        }
    }

}
