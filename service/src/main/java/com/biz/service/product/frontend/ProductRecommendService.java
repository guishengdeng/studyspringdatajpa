package com.biz.service.product.frontend;


import com.biz.gbck.vo.product.frontend.ProductRecItemsVo;

/**
 * 商品推荐Service
 *
 * @author 江南
 * @date 2017/1/20
 * @reviewer
 */
public interface ProductRecommendService {

    /**
     * 推荐场景:首页推荐
     *
     * @param userId 用户ID
     * @return 12个推荐结果和traceID
     */
    ProductRecItemsVo getHomePageProductRec(String userId);

    /**
     * 推荐场景:vendor首页推荐
     */
    @Deprecated
    ProductRecItemsVo getVendorHomeProductRec(String userId);

    /**
     * 推荐场景:商品详情页,推荐商品
     */
    ProductRecItemsVo getProductDetailRec(String userId, String itemId);

}
