package com.biz.soa.product.service.frontend;

import com.biz.gbck.vo.product.gbck.request.ProductAppListReqVo;
import com.biz.gbck.vo.search.ProductSearchResultEntityVo;
import com.biz.gbck.vo.search.ProductSearchResultVo;

/**
 * 商品搜索Service
 *
 * Created by david-liu on 2017/05/02 10:54.
 */
public interface IProductSearchService {
    ProductSearchResultVo<ProductSearchResultEntityVo> searchProducts(ProductAppListReqVo reqVo);
}
