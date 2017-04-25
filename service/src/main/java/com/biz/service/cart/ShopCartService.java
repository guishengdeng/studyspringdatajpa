package com.biz.service.cart;

import com.biz.vo.cart.*;

/**
 * 购物车接口
 *
 * @author lei
 * @date 2017年04月25日
 * @reviewer
 * @see
 */
public interface ShopCartService {

    /**
     * 添加商品到购物车
     * @return
     */
    boolean addCartItem(ShopCartItemAddReqVo reqVo);

    /**
     * 获取购物车信息
     * @param reqVo
     * @return
     */
    ShopCartRespVo getCartInfo(ShopCartListReqVo reqVo);

    /**
     * 批量删除购物车商品
     * @param reqVo
     */
    void deleteCartItems(ShopCartItemBatchDeleteRespVo reqVo);

    /**
     * 购物车数量更新
     * @param reqVo
     */
    ShopCartItemUpdateRespVo updateCartItemQuantity(ShopCartItemUpdateReqVo reqVo);
}
