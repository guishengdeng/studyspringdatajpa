package com.biz.service.product.frontend;

import com.biz.gbck.vo.search.ProductIdxIdentityVo;
import com.biz.gbck.vo.search.ProductSearchResultEntityVo;
import com.biz.gbck.vo.search.ProductSearchResultVo;
import com.biz.gbck.vo.search.SearchProductConditionVo;
import java.io.Serializable;

/**
 * 商品搜索接口
 *
 * @author david-liu
 * @date 2017年01月07日
 * @reviewer
 * @see
 */
public interface IProductSearchService extends Serializable {

    /**
     * 根据商品搜索条件 Vo 得到商品搜索结果Vo
     *
     * @param vo 商品搜索条件 Vo
     * @return 搜索结果商品 Vo
     */
    ProductSearchResultVo<ProductSearchResultEntityVo> searchProduct(SearchProductConditionVo vo);

    /**
     * 搜索商家店内商品
     *
     * @param vo 商品搜索条件 Vo
     * @return 搜索结果商品 Vo
     */
    ProductSearchResultVo<ProductSearchResultEntityVo> searchVendorProduct(SearchProductConditionVo vo);


    /**
     * 更新商品的全量索引
     */
    void updateTotalProductIndexDocuments(ProductIdxIdentityVo vo);

    /**
     * 更新商品增量索引
     *
     * @param vo 索引 Vo
     */
    void updateIncrProductIndexDocument(ProductIdxIdentityVo vo);

    /**
     * 更新B类商品 全部区域的增量索引
     */
    void updateIncrProductAllIndexDocument(ProductIdxIdentityVo vo);
}
