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

        try {
            shopCartFeignClient.addCartItem(reqVo);
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
        ShopCartListReqVo reqVo = RestUtil.parseBizData(request, ShopCartListReqVo.class);
        try {
            ShopCartRespVo respVo = shopCartFeignClient.getCartInfo(reqVo);
            return new JSONResult(respVo);
        } catch (Exception e) {
            logger.error("添加购物车出错.", e);
            return new JSONResult(-1, "获取购物车信息出错");
        }
    }

    /**
     * 添加购物车
     */
    @RequestMapping("delete")
    public JSONResult deleteCartItem(HttpServletRequest request) {
        ShopCartItemBatchDeleteReqVo reqVo = RestUtil.parseBizData(request, ShopCartItemBatchDeleteReqVo.class);
        try {
            shopCartFeignClient.deleteCartItems(reqVo);
            ShopCartRespVo respVo = shopCartFeignClient.getCartInfo(new ShopCartListReqVo());
            return new JSONResult(respVo);
        } catch (Exception e) {
            logger.error("添加购物车出错.", e);
            return new JSONResult(-1, "删除购物车商品");
        }
    }

    @RequestMapping("cartNum")
    @ResponseBody
    public JSONResult cartNum(HttpServletRequest request) {
        ShopCartNumReqVo reqVo = RestUtil.parseBizData(request, ShopCartNumReqVo.class);
        try {
            ShopCartNumRespVo respVo = shopCartFeignClient.getCartNum(reqVo);
            return new JSONResult(respVo);
        } catch (Exception e) {
            logger.error("取购物车数量异常", e);
            return new JSONResult(-1, "获取购物车数量出错");
        }
    }


}
