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
    ShopCartRespVo addCartItem(ShopCartItemAddReqVo reqVo) throws DepotNextDoorException;

    /**
     * 获取购物车商品信息
     * @param reqVo
     */
    ShopCartRespVo getCartItemsInfo(ShopCartListReqVo reqVo) throws DepotNextDoorException;

    /**
     * 批量删除购物车商品
     * @param reqVo
     */
    void deleteCartItems(ShopCartItemBatchDeleteReqVo reqVo) throws DepotNextDoorException;

    /**
     * 购物车数量更新
     * @param reqVo
     */
    void updateCartItemQuantity(ShopCartItemUpdateReqVo reqVo) throws DepotNextDoorException;


    /**
     * 获取购物车数量
     */
    ShopCartNumRespVo getCartNum(ShopCartNumReqVo reqVo) throws DepotNextDoorException ;

    /**
     * 清空购物车,一般下单后操作
     */
    void cleanCart(String userId);
}
