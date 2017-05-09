package com.biz.service.cart;

import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.vo.cart.*;

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
    void addCartItem(ShopCartItemAddReqVo reqVo) throws DepotNextDoorException;

    /**
     * 获取购物车信息
     * @param reqVo
     * @return
     */
    ShopCartRespVo getCartInfo(ShopCartListReqVo reqVo) throws DepotNextDoorException;

    /**
     * 批量删除购物车商品
     * @param reqVo
     */
    void deleteCartItems(ShopCartItemBatchDeleteReqVo reqVo) throws DepotNextDoorException;

    /**
     * 购物车数量更新
     * @param reqVo
     */
    ShopCartItemUpdateRespVo updateCartItemQuantity(ShopCartItemUpdateReqVo reqVo) throws DepotNextDoorException;


    /**
     * 获取购物车数量
     */
    ShopCartNumRespVo getCartNum(ShopCartNumReqVo reqVo) throws DepotNextDoorException ;
}