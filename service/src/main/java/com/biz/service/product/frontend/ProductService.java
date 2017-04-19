package com.biz.service.product.frontend;

import com.biz.core.page.PageResult;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.exceptions.product.IllegalParameterException;
import com.biz.gbck.vo.product.ProductPageResult;
import com.biz.gbck.vo.product.ProductPropertyVo;
import com.biz.gbck.vo.product.backend.CascadeVo;
import com.biz.gbck.vo.product.backend.ProductDetailVo;
import com.biz.gbck.vo.product.frontend.*;
import com.biz.gbck.vo.search.SearchProductConditionVo;
import com.biz.gbck.vo.user.UserProductsReqVo;
import java.io.Serializable;
import java.util.List;

/**
 * 前端商品 Service 接口定义
 *
 * @author david-liu
 * @date 2017年01月11日
 * @reviewer
 */
public interface ProductService extends Serializable {
    /**
     * 商品搜索接口
     *
     * @param vo 商品搜索 Vo
     * @return 分页商品列表
     */
    ProductPageResult searchProducts(SearchProductConditionVo vo);

    /**
     * 获取用户足迹页面商品
     *
     * @param reqVo 请求Vo
     * @return 用户足迹商品返回Vo
     */
    List<UserProductItemVo> userProducts(UserProductsReqVo reqVo);

    /**
     * 获取购物车商品信息
     *
     * @param reqVo 购物车商品信息reqVo
     * @return 购物车商品信息列表
     */
    List<ShopCartProductResponseVo> shoppingCartProducts(ShopCartProductRequestVo reqVo);

    /**
     * 获取平台端商品详情Vo
     *
     * 根据商品编码查找商品
     *
     * @
     */
    PlatformProductDetailVo findPlatformProductDetailByProductCode(String productCode);


    /**
     * 获取订单商品信息,比购物车商品信息多几个属性
     */
    List<SoaOrderProductResponseVo> orderProducts(ShopCartProductRequestVo req);

    /**
     * 商户店内搜索商品
     *
     * @param vo 商品搜索vo
     * @return 分页商品列表
     */
    PageResult<ProductListItemVo> searchVendorProducts(SearchProductConditionVo vo);

    /**
     * 商品详情
     *
     * @param vo 商品详情请求Vo
     * @return 商品详情Vo
     */
    ProductDetailVo productDetail(ProductDetailRequestVo vo) throws DepotNextDoorException;

    /**
     * 获取阿里云推荐商品
     *
     * @param reqVo 推荐商品请求Vo
     * @return 推荐商品列表
     */
    List<ProductListItemVo> recommendProducts(RecommendProductReqVo reqVo) throws DepotNextDoorException;

    /**
     * 获取关联商品
     *
     * @param reqVo 关联商品请求Vo
     * @return 关联商品列表
     * @throws DepotNextDoorException Soa异常
     */
    List<ProductListItemVo> relevantProducts(RelevantProductReqVo reqVo) throws DepotNextDoorException;

    /**
     * 获取配置商品
     *
     * @param reqVo 配置商品请求Vo
     * @return 商品配置Vo
     * @throws DepotNextDoorException Soa异常
     */
    CascadeVo cascadeProducts(ProductCascadeReqVo reqVo) throws DepotNextDoorException;

    /**
     * 秒杀商品详情
     *
     * @param reqVo 秒杀商品详情请求Vo
     * @return 秒杀商品详情返回Vo
     * @throws DepotNextDoorException Soa异常
     */
    SeckillProductDetailRespVo seckillProductDetail(SeckillProductDetailReqVo reqVo) throws DepotNextDoorException;

    /**
     * 获取商品库存
     *
     * @param reqVo 商品库存请求Vo
     * @return 商品库存Vo
     */
    ProductStockVo productStock(TypeBProductStockReqVo reqVo);

    /**
     * 获取商品价格
     *
     * @param reqVo 商品价格请求Vo
     * @return 商品价格Vo
     */
    ProductPriceVo productPrice(ProductPriceReqVo reqVo);

    /**
     * 首页商品广告
     *
     * @throws IllegalParameterException
     */
    List<ProductListItemVo> indexAdProducts(IndexAdProductReqVo req) throws IllegalParameterException;

    /**
     * 根据分类id，返回该分类下按销量排序，最畅销的N个商品，N为传入的size
     */
    List<ProductListItemVo> listTopProductsBySalesVolumeForCategory(ListTopNReqVo reqVo) throws IllegalParameterException;

    /**
     * 获取商品属性表
     *
     * @return 商品属性Vo
     * @throws DepotNextDoorException Soa异常
     */
    ProductPropertyVo productProperties(ProductPropertyReqVo reqVo) throws DepotNextDoorException;

    /**
     * 首页商品
     *
     * @param reqVo 首页商品请求Vo
     * @return 商品信息列表集合
     * @throws DepotNextDoorException Soa异常
     */
    List<ProductListItemVo> indexProducts(IndexProductReqVo reqVo) throws DepotNextDoorException;

    /**
     * 获取到所有的商品
     */
    List<ProductListItemVo> findAllProducts();

    /**
     * 单页活动列表
     */
    List<ProductListItemVo> singlePageActivity(SinglePageActivityReqVo reqVo);

}
