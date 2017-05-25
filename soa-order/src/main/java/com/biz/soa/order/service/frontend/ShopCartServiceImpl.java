package com.biz.soa.order.service.frontend;

import com.biz.core.asserts.BusinessAsserts;
import com.biz.core.asserts.SystemAsserts;
import com.biz.gbck.common.vo.CommonReqVoBindUserId;
import com.biz.gbck.dao.redis.repository.cart.ShopCartItemRedisDao;
import com.biz.gbck.dao.redis.ro.cart.ShopCartItemRo;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.exceptions.DepotNextDoorExceptions;
import com.biz.gbck.exceptions.cart.CartItemNotExistException;
import com.biz.gbck.exceptions.cart.CartItemProductInvalidException;
import com.biz.gbck.exceptions.cart.IllegalParameterException;
import com.biz.gbck.vo.cart.*;
import com.biz.gbck.vo.order.req.ProductItemReqVo;
import com.biz.gbck.vo.org.UserInfoVo;
import com.biz.gbck.vo.product.gbck.request.PurchaseProductReqVO;
import com.biz.gbck.vo.product.gbck.response.ProductAppListItemVo;
import com.biz.gbck.vo.product.gbck.response.PurchaseProductItemVO;
import com.biz.gbck.vo.soa.MicroServiceResult;
import com.biz.gbck.vo.stock.CompanyStockReqVO;
import com.biz.gbck.vo.stock.CompanyStockRespVO;
import com.biz.service.AbstractBaseService;
import com.biz.service.cart.ShopCartService;
import com.biz.soa.feign.client.org.UserFeignClient;
import com.biz.soa.feign.client.product.ProductFeignClient;
import com.biz.soa.feign.client.stock.StockFeignClient;
import com.biz.soa.order.builder.ShopCartRespVoBuilder;
import org.apache.commons.collections.CollectionUtils;
import org.codelogger.utils.StringUtils;
import org.codelogger.utils.ValueUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static java.util.stream.Collectors.toList;

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

    @Autowired(required = false)
    private UserFeignClient userFeignClient;


    @Override
    public ShopCartRespVo addCartItem(ShopCartItemAddReqVo reqVo) throws DepotNextDoorException {
        if (logger.isDebugEnabled()) {
            logger.debug("添加购物车商品-------请求vo: {}", reqVo);
        }
        if (reqVo == null || reqVo.getUserId() == null || StringUtils.isBlank(reqVo.getProductId())) {
            throw new IllegalParameterException("参数不合法");
        }
        String userId = reqVo.getUserId();
        String productId = reqVo.getProductId();
        int quantity = reqVo.getQuantity();

        this.validQuantity(reqVo.getUserId(), Long.valueOf(reqVo.getProductId()), reqVo.getQuantity());

        ShopCartItemRo shopCartItemRo = shopCartItemRedisDao.findByUserIdAndProductId(reqVo.getUserId(), productId);

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

        ShopCartNumReqVo cartNumReqVo = new ShopCartNumReqVo();
        cartNumReqVo.setUserId(reqVo.getUserId());
        ShopCartNumRespVo cartNum = this.getCartNum(cartNumReqVo);
        ShopCartRespVo shopCartRespVo = ShopCartRespVoBuilder.createBuilder().setCartNum(cartNum.getCartNum()).build();

        if (logger.isDebugEnabled()) {
            logger.debug("添加购物车-------请求: {}, 返回值: {}", reqVo, shopCartRespVo);
        }
        return shopCartRespVo;


    }

    @Override
    public ShopCartRespVo getCartItemsInfo(ShopCartListReqVo reqVo) throws DepotNextDoorException {
        if (logger.isDebugEnabled()) {
            logger.debug("获取购物车信息-------请求vo: {}", reqVo);
        }
        if (reqVo == null) {
            logger.warn("购物车参数不合法");
            throw new IllegalParameterException("参数不合法");
        }

        ShopCartRespVo cartRespVo = new ShopCartRespVo();
        String userId = reqVo.getUserId();
        List<ShopCartItemRo> shopCartItemRos = shopCartItemRedisDao.findByUserId(userId);

        if (CollectionUtils.isEmpty(shopCartItemRos)) {
            logger.warn("购物车商品为空.");
            return cartRespVo;
        }

        List<ShopCartItemRo> usableCartItemRos;
        if (reqVo instanceof ShopCartListSettleReqVo) { //结算+创建订单
            usableCartItemRos = this.filterShopCartItemRosForSettle((ShopCartListSettleReqVo) reqVo, shopCartItemRos);
        } else { //购物车展示
            usableCartItemRos = shopCartItemRos;
        }

        cartRespVo = this.generateCartRespInfo(userId, usableCartItemRos);

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

        shopCartItemRedisDao.deleteByUserIdAndProductIds(reqVo.getUserId(), reqVo.getProductIds());
    }


    @Override
    public void updateCartItemQuantity(ShopCartItemUpdateReqVo reqVo) throws DepotNextDoorException {
        if (logger.isDebugEnabled()) {
            logger.debug("Update shop cart count updateVo: {}", reqVo);
        }

        if (reqVo == null || reqVo.getUserId() == null) {
            logger.warn("添加购物车参数不合法");
            throw new IllegalParameterException("参数不合法");
        }
        SystemAsserts.isTrue(reqVo.getQuantity() > 0, "购物车更新数量不能小于0");
        ShopCartItemRo shopCartItemRo = shopCartItemRedisDao.findByUserIdAndProductId(reqVo.getUserId(), reqVo
                .getProductId());
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
        List<ShopCartItemRo> shopCartItemRos = shopCartItemRedisDao.findByUserId(userId);
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


    @Override
    public void cleanCart(CommonReqVoBindUserId reqVo) {
        if (logger.isDebugEnabled()) {
            logger.debug("清空购物车-------请求vo: {}", reqVo);
        }
        BusinessAsserts.notNull(reqVo, DepotNextDoorExceptions.Global.PARAMETER_ERROR);
        BusinessAsserts.notNull(reqVo.getUserId(), DepotNextDoorExceptions.User.USER_NOT_EXIST);
        shopCartItemRedisDao.removeAllByUserId(reqVo.getUserId());
    }

    /**
     * 获取购物车返回信息
     */
    private ShopCartRespVo generateCartRespInfo(String userId, List<ShopCartItemRo> shopCartItemRos) throws
            DepotNextDoorException {

        List<Long> productIds = shopCartItemRos.stream().filter(ro -> ro != null && ro.getProductId() != null).map(ro
                -> Long.valueOf(ro.getProductId())).collect(toList());

        UserInfoVo userInfo = this.getUserInfo(userId);
        Map<String, PurchaseProductItemVO> productIdToProductItemVo = this.getProductIdToProductVoMap(productIds,
                userInfo);

        int orderAmount = 0;
        int cartNum = 0;
        List<ShopCartItemRespVo> cartItemRespVos = newArrayList();
        for (ShopCartItemRo shopCartItemRo : shopCartItemRos) {
            PurchaseProductItemVO productItemVo = productIdToProductItemVo.get(shopCartItemRo.getId());
            if (productItemVo != null) {
                ShopCartItemRespVo cartItemRespVo = new ShopCartItemRespVo(productItemVo);
                cartItemRespVo.setQuantity(shopCartItemRo.getQuantity());
                cartItemRespVo.setCreateTime(shopCartItemRo.getCreateTimestamp());
                cartItemRespVo.setUpdateTime(shopCartItemRo.getUpdateTimestamp());
                cartItemRespVos.add(cartItemRespVo);
                orderAmount += ValueUtils.getValue(cartItemRespVo.getQuantity()) * cartItemRespVo.getPrice();
                cartNum += ValueUtils.getValue(cartItemRespVo.getQuantity());
            }
        }
        cartItemRespVos.sort((o1, o2) -> (int) (o2.getCreateTime().getTime() - o1.getCreateTime().getTime()));
        return ShopCartRespVoBuilder.createBuilder().setItems(cartItemRespVos).setOrderAmount(orderAmount).setCartNum
                (cartNum).build();
    }

    //订单结算
    private List<ShopCartItemRo> filterShopCartItemRosForSettle(ShopCartListSettleReqVo reqVo, List<ShopCartItemRo>
            shopCartItemRos) {
        Map<String, ShopCartItemRo> productIdToCartItemRo = newHashMap();
        for (ShopCartItemRo shopCartItemRo : shopCartItemRos) {
            productIdToCartItemRo.put(shopCartItemRo.getProductId(), shopCartItemRo);
        }

        List<ShopCartItemRo> usableCartItemRos = newArrayList();
        for (ProductItemReqVo itemReqVo : reqVo.getItems()) {
            ShopCartItemRo cartItemRo = productIdToCartItemRo.get(itemReqVo.getProductId());
            if (cartItemRo != null) {
                usableCartItemRos.add(cartItemRo);
            }
        }
        return usableCartItemRos;
    }

    //获取商品信息且map
    private Map<String, PurchaseProductItemVO> getProductIdToProductVoMap(List<Long> productIds, UserInfoVo userInfo)
            throws CartItemProductInvalidException {
        //TODO Mock
//        PurchaseProductReqVO productReqVo = new PurchaseProductReqVO();
//        productReqVo.setProductIds(productIds);
//        productReqVo.setSellerId(userInfo.getPartnerId());
//        productReqVo.setCompanyGroupId(userInfo.getCompanyGroupId());

        PurchaseProductReqVO productReqVo = new PurchaseProductReqVO();
        productReqVo.setProductIds(productIds);
        productReqVo.setSellerId(1l);
        productReqVo.setCompanyGroupId(1l);
        if (logger.isDebugEnabled()) {
            logger.debug("获取购物车商品详情请求vo: {}", productReqVo);
        }
        MicroServiceResult<List<PurchaseProductItemVO>> productResult = productFeignClient.getPurchaseProducts
                (productReqVo);
        if (logger.isDebugEnabled()) {
            logger.debug("获取购物车商品详情返回结果: {}", productResult);
        }
        if (productResult == null || !productResult.isSuccess() || productResult.getData() == null) {
            throw new CartItemProductInvalidException("获取商品信息失败");
        }
        return productResult.getData().stream().collect(Collectors.toMap(ProductAppListItemVo::getId, Function
                .identity()));
    }

    /**
     * 校验数量
     */
    private void validQuantity(String userId, Long productId, int quantity) throws DepotNextDoorException {
        SystemAsserts.isTrue(quantity > 0, "添加数量应大于0, 添加购物车失败");
        UserInfoVo userInfo = this.getUserInfo(userId);
        CompanyStockReqVO reqVo = new CompanyStockReqVO(userInfo.getPartnerId(), productId);
        CompanyStockRespVO stockRespVO = stockFeignClient.getStock(reqVo);
        if (logger.isDebugEnabled()) {
            logger.debug("购物车验证库存,请求:{}, 返回值: {}", reqVo, stockRespVO);
        }
        SystemAsserts.notNull(stockRespVO, "库存不足, 添加购物车失败");
        SystemAsserts.isTrue(quantity <= ValueUtils.getValue(stockRespVO.getQuantity()), "库存不足");
    }

    //获取并校验用户信息
    private UserInfoVo getUserInfo(String userId) throws DepotNextDoorException {
        UserInfoVo userInfo = userFeignClient.findUserInfo(Long.valueOf(userId));
        if (logger.isDebugEnabled()) {
            logger.debug("购物车[userId={}]获取用户信息user: {}", userId, userInfo);
        }
        BusinessAsserts.notNull(userInfo, DepotNextDoorExceptions.User.USER_NOT_EXIST);
        SystemAsserts.notNull(userInfo.getPartnerId(), "合伙人不能为空");
        return userInfo;
    }
}
