package com.biz.service.search.bbc;

import com.biz.gbck.vo.search.bbc.ProductIdxIdentityVo;
import com.biz.gbck.vo.search.bbc.ProductIdxReqVo;
import com.biz.gbck.vo.search.bbc.ProductIdxRespVo;
import com.biz.gbck.vo.search.bbc.ProductIdxVo;

/**
 * 商品索引Service
 *
 * @author david-liu
 * @date 2017年01月19日
 * @reviewer
 */
public interface ProductIdxService extends IdxService<ProductIdxVo, ProductIdxIdentityVo> {

    ProductIdxRespVo getProductIndices(ProductIdxReqVo reqVo);

}
