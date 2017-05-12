package com.biz.soa.order.service.frontend;

import com.biz.gbck.dao.redis.repository.cart.ShopCartItemRedisDao;
import com.biz.gbck.dao.redis.ro.cart.ShopCartItemRo;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.exceptions.cart.CartItemNotExistException;
import com.biz.gbck.exceptions.cart.IllegalParameterException;
import com.biz.service.AbstractBaseService;
import com.biz.service.cart.ShopCartService;
import com.biz.gbck.vo.cart.*;
import org.apache.commons.collections.CollectionUtils;
import org.codelogger.utils.StringUtils;
import org.codelogger.utils.ValueUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    private final int MAX_SHOP_CART_PRODUCT_QUANTITY = 99; //购物车最大添加数量

    @Autowired
    private ShopCartItemRedisDao shopCartItemRedisDao;


    @Override
    public void addCartItem(ShopCartItemAddReqVo reqVo) throws DepotNextDoorException {
        if (reqVo == null || reqVo.getUserId() == null || StringUtils.isBlank(reqVo.getProductId()) || ValueUtils
                .getValue(reqVo.getQuantity()) <= 0) {
            logger.warn("购物车参数不合法");
            throw new IllegalParameterException("参数不合法");
        }
        Long userId = Long.valueOf(reqVo.getUserId());
        String productId = reqVo.getProductId();
        int quantity = reqVo.getQuantity();

        //TODO 数量校验
        ShopCartItemRo shopCartItemRo = shopCartItemRedisDao.findByUserIdAndProductId(Long.valueOf(reqVo
                .getUserId()), productId);

        if (shopCartItemRo != null) {
            quantity =  shopCartItemRo.getQuantity() + quantity;
        } else {
            shopCartItemRo = new ShopCartItemRo();
            shopCartItemRo.setUserId(userId);
            shopCartItemRo.setProductId(productId);
            shopCartItemRo.setSelected(true);
        }
        shopCartItemRo.setQuantity(quantity);
        shopCartItemRedisDao.save(shopCartItemRo);
    }

    @Override
    public ShopCartRespVo getCartInfo(ShopCartListReqVo reqVo) throws DepotNextDoorException  {
        if (reqVo == null || reqVo.getUserId() == null) {
            logger.warn("购物车参数不合法");
            throw new IllegalParameterException("参数不合法");
        }

        ShopCartRespVo cartRespVo = new ShopCartRespVo();
        String userId = reqVo.getUserId();
        List<ShopCartItemRo> shopCartItemRos = shopCartItemRedisDao.findByUserId(Long.valueOf(userId));

        if (CollectionUtils.isEmpty(shopCartItemRos)) {
            logger.warn("购物车商品为空.");
            return cartRespVo;
        }

        //创建时间排倒序输出
        shopCartItemRos.sort((o1, o2) -> (int) (o2.getCreateTimestamp().getTime() - o1.getCreateTimestamp().getTime()));
        //TODO 获取商品信息

        return cartRespVo;
    }

    @Override
    public void deleteCartItems(ShopCartItemBatchDeleteReqVo reqVo) throws DepotNextDoorException {
        if (logger.isDebugEnabled()) {
            logger.debug("Delete shop cart items with quantity : {}", reqVo);
        }

        if (reqVo == null || reqVo.getUserId() == null || CollectionUtils.isEmpty(reqVo.getProductIds())) {
            logger.warn("购物车参数不合法");
            throw new IllegalParameterException("参数不合法");
        }

        shopCartItemRedisDao.deleteByUserIdAndProductIds(Long.valueOf(reqVo.getUserId()), reqVo.getProductIds());
    }


    @Override
    public void updateCartItemQuantity(ShopCartItemUpdateReqVo reqVo) throws
            DepotNextDoorException {
        if (logger.isDebugEnabled()) {
            logger.debug("Update shop cart count updateVo: {}", reqVo);
        }

        if (reqVo == null || reqVo.getUserId() == null) {
            logger.warn("添加购物车参数不合法");
            throw new IllegalParameterException("参数不合法");
        }

        if (reqVo.getQuantity() > MAX_SHOP_CART_PRODUCT_QUANTITY) {
            reqVo.setQuantity(MAX_SHOP_CART_PRODUCT_QUANTITY);
        }
        ShopCartItemRo shopCartItemRo = shopCartItemRedisDao.findByUserIdAndProductId(Long.valueOf(reqVo.getUserId
                ()), reqVo.getProductId());
        if (shopCartItemRo != null) {
            shopCartItemRo.setQuantity(reqVo.getQuantity());
            shopCartItemRo.setSelected(true);
            shopCartItemRedisDao.save(shopCartItemRo);
        } else {
            throw new CartItemNotExistException("购物车商品不存在");
        }
    }

    @Override
    public ShopCartNumRespVo getCartNum(ShopCartNumReqVo reqVo) throws DepotNextDoorException {
        String userId = reqVo.getUserId();
        if (userId == null) {
            throw new IllegalParameterException("参数不合法");
        }

        ShopCartNumRespVo cartItemRespVo = new ShopCartNumRespVo();
        List<ShopCartItemRo> shopCartItemRos = shopCartItemRedisDao.findByUserId(Long.valueOf(userId));
        int totalNum = 0;
        int selectedNum = 0;
        if (CollectionUtils.isNotEmpty(shopCartItemRos)) {
            for (ShopCartItemRo cartItemRo : shopCartItemRos) {
                totalNum += ValueUtils.getValue(cartItemRo.getQuantity());
                if (cartItemRo.isSelected()) {
                    selectedNum += ValueUtils.getValue(cartItemRo.getQuantity());
                }
            }
        }
        cartItemRespVo.setTotalNum(totalNum);
        cartItemRespVo.setSelectedNum(selectedNum);
        return null;
    }
}
