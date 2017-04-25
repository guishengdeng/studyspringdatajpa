package com.biz.service.cart;

import com.biz.service.AbstractBaseService;
import com.biz.vo.cart.*;
import org.springframework.stereotype.Service;

/**
 * 购物车service
 *
 * @author lei
 * @date 2017年04月25日
 * @reviewer
 * @see
 */
@Service
public class ShopCartServiceImpl extends AbstractBaseService implements ShopCartService {


    @Override
    public boolean addCartItem(ShopCartItemAddReqVo reqVo) {
        //TODO 添加商品到购物车
        return false;
    }

    @Override
    public ShopCartRespVo getCartInfo(ShopCartListReqVo reqVo) {
        //TODO 获取购物车信息
        return null;
    }

    @Override
    public void deleteCartItems(ShopCartItemBatchDeleteRespVo reqVo) {
        //TODO 批量删除购物车商品
    }


    @Override
    public ShopCartItemUpdateRespVo updateCartItemQuantity(ShopCartItemUpdateReqVo reqVo) {
        //TODO 更新购物车数量
        return new ShopCartItemUpdateRespVo();
    }
}
