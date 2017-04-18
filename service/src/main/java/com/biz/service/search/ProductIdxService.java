package com.biz.service.search;

import com.biz.gbck.vo.search.ProductIdxIdentityVo;
import com.biz.gbck.vo.search.ProductIdxReqVo;
import com.biz.gbck.vo.search.ProductIdxRespVo;
import com.biz.gbck.vo.search.ProductIdxVo;

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
