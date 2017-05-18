package com.biz.soa.order.service.frontend;

import com.biz.core.asserts.SystemAsserts;
import com.biz.gbck.dao.redis.repository.cart.ShopCartItemRedisDao;
import com.biz.gbck.dao.redis.ro.cart.ShopCartItemRo;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.exceptions.cart.CartItemInvalidException;
import com.biz.gbck.exceptions.cart.CartItemNotExistException;
import com.biz.gbck.exceptions.cart.IllegalParameterException;
import com.biz.gbck.vo.cart.*;
import com.biz.gbck.vo.order.req.ProductItemReqVo;
import com.biz.gbck.vo.stock.PartnerStockReqVO;
import com.biz.gbck.vo.stock.PartnerStockRespVO;
import com.biz.service.AbstractBaseService;
import com.biz.service.cart.ShopCartService;
import com.biz.soa.feign.client.product.ProductFeignClient;
import com.biz.soa.feign.client.stock.StockFeignClient;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.codelogger.utils.StringUtils;
import org.codelogger.utils.ValueUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;

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

    @Autowired
    private ShopCartItemRedisDao shopCartItemRedisDao;

    @Autowired(required = false)
    private StockFeignClient stockFeignClient;

    @Autowired(required = false)
    private ProductFeignClient productFeignClient;


    @Override
    public void addCartItem(ShopCartItemAddReqVo reqVo) throws DepotNextDoorException {
        if (logger.isDebugEnabled()) {
            logger.debug("添加购物车商品-------请求vo: {}", reqVo);
        }
        if (reqVo == null || reqVo.getUserId() == null || StringUtils.isBlank(reqVo.getProductId()) || ValueUtils
                .getValue(reqVo.getQuantity()) <= 0) {
            logger.warn("购物车参数不合法");
            throw new IllegalParameterException("参数不合法");
        }
        Long userId = Long.valueOf(reqVo.getUserId());
        String productId = reqVo.getProductId();
        int quantity = reqVo.getQuantity();

        this.validQuantity(Long.valueOf(reqVo.getUserId()), Long.valueOf(reqVo.getProductId()), reqVo.getQuantity());

        ShopCartItemRo shopCartItemRo = shopCartItemRedisDao.findByUserIdAndProductId(Long.valueOf(reqVo.getUserId())
                , productId);

        if (shopCartItemRo != null) {
            quantity = shopCartItemRo.getQuantity() + quantity;
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
    public ShopCartRespVo getCartInfo(ShopCartListReqVo reqVo) throws DepotNextDoorException {
        if (logger.isDebugEnabled()) {
            logger.debug("获取购物车信息-------请求vo: {}", reqVo);
        }
        if (reqVo == null) {
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

        this.getProductInfo(reqVo, shopCartItemRos);

        if (logger.isDebugEnabled()) {
            logger.debug("获取购物车信息-------请求: {}, 返回值: {}", reqVo, cartRespVo);
        }
        return cartRespVo;
    }

    @Override
    public void deleteCartItems(ShopCartItemBatchDeleteReqVo reqVo) throws DepotNextDoorException {
        if (logger.isDebugEnabled()) {
            logger.debug("删除购物车商品-------请求vo: {}", reqVo);
        }
        if (reqVo == null || reqVo.getUserId() == null || CollectionUtils.isEmpty(reqVo.getProductIds())) {
            logger.warn("购物车参数不合法");
            throw new IllegalParameterException("参数不合法");
        }

        shopCartItemRedisDao.deleteByUserIdAndProductIds(Long.valueOf(reqVo.getUserId()), reqVo.getProductIds());
    }

    @Override
    public void updateCartItemQuantity(ShopCartItemUpdateReqVo reqVo) throws DepotNextDoorException {
        if (logger.isDebugEnabled()) {
            logger.debug("更新购物车数量-------请求vo: {}", reqVo);
        }

        if (reqVo == null || reqVo.getUserId() == null || reqVo.getProductId() == null) {
            logger.warn("添加购物车参数不合法");
            throw new IllegalParameterException("参数不合法");
        }

        this.validQuantity(Long.valueOf(reqVo.getUserId()), Long.valueOf(reqVo.getProductId()), reqVo.getQuantity());
        ShopCartItemRo shopCartItemRo = shopCartItemRedisDao.findByUserIdAndProductId(Long.valueOf(reqVo.getUserId())
                , reqVo.getProductId());
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
        if (logger.isDebugEnabled()) {
            logger.debug("获取购物车数量-------请求vo: {}", reqVo);
        }
        String userId = reqVo.getUserId();
        if (userId == null) {
            throw new IllegalParameterException("参数不合法");
        }

        ShopCartNumRespVo cartItemRespVo = new ShopCartNumRespVo();
        List<ShopCartItemRo> shopCartItemRos = shopCartItemRedisDao.findByUserId(Long.valueOf(userId));
        int totalNum = 0;
        if (CollectionUtils.isNotEmpty(shopCartItemRos)) {
            for (ShopCartItemRo cartItemRo : shopCartItemRos) {
                totalNum += ValueUtils.getValue(cartItemRo.getQuantity());
            }
        }
        cartItemRespVo.setCartNum(totalNum);

        if (logger.isDebugEnabled()) {
            logger.debug("获取购物车数量-------请求: {}, 返回值: {}", reqVo, cartItemRespVo);
        }
        return cartItemRespVo;
    }

    /**
     * 获取商品信息
     */
    private void getProductInfo(ShopCartListReqVo reqVo, List<ShopCartItemRo> shopCartItemRos) throws
            DepotNextDoorException {
        List<Long> productIds = newArrayList();
        if (reqVo instanceof ShopCartListSettleReqVo) {
            ShopCartListSettleReqVo cartSettleReqVo = (ShopCartListSettleReqVo) reqVo;
            Map<String, ShopCartItemRo> productIdToCartItemRo = newHashMap();
            for (ShopCartItemRo shopCartItemRo : shopCartItemRos) {
                productIdToCartItemRo.put(shopCartItemRo.getProductId(), shopCartItemRo);
            }

            for (ProductItemReqVo itemReqVo : cartSettleReqVo.getItems()) {
                ShopCartItemRo cartItemRo = productIdToCartItemRo.get(itemReqVo.getProductId());
                if (cartItemRo == null || itemReqVo.getQuantity() < 1 || !Objects.equals(cartItemRo.getQuantity(),
                        itemReqVo.getQuantity())) {
                    throw new CartItemInvalidException("结算商品信息不匹配");
                } else {
                    productIds.add(Long.valueOf(itemReqVo.getProductId()));
                }
            }
        } else {
            productIds = Lists.transform(shopCartItemRos, input -> Long.valueOf(input.getProductId()));
        }

        //TODO 获取刘伟接口
    }

    /**
     * 校验数量
     *
     * @throws DepotNextDoorException
     */
    private void validQuantity(Long userId, Long productId, int quantity) throws DepotNextDoorException {
        //TODO 根据用户拿对应合伙人id
        Long partnerId = null;
        PartnerStockRespVO stockRespVO = stockFeignClient.getStock(new PartnerStockReqVO(partnerId, productId));
        SystemAsserts.notNull(stockRespVO, "库存不足");
        SystemAsserts.isTrue(quantity <= stockRespVO.getQuantity(), "库存不足");
    }
}
