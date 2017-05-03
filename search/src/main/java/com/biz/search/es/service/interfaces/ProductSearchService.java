package com.biz.search.es.service.interfaces;

import com.biz.gbck.vo.product.gbck.request.ProductAppListReqVo;
import com.biz.gbck.vo.search.ProductSearchResultEntityVo;
import com.biz.gbck.vo.search.ProductSearchResultVo;

/**
 * 商品搜索Service
 *
 * Created by david-liu on 2017/05/02 23:03.
 */
public interface ProductSearchService {

    ProductSearchResultVo<ProductSearchResultEntityVo> searchProducts(ProductAppListReqVo reqVo);

}
