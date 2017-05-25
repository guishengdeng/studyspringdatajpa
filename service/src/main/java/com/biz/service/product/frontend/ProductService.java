package com.biz.service.product.frontend;

import com.biz.gbck.vo.product.gbck.request.ProductAppDetailReqVo;
import com.biz.gbck.vo.product.gbck.request.ProductAppListReqVo;
import com.biz.gbck.vo.product.gbck.request.PurchaseProductReqVO;
import com.biz.gbck.vo.product.gbck.response.ProductAppDetailRespVO;
import com.biz.gbck.vo.product.gbck.response.ProductAppListItemVo;
import com.biz.gbck.vo.product.gbck.response.ProductAppListRespVO;
import com.biz.gbck.vo.product.gbck.response.PurchaseProductItemVO;
import java.util.List;

/**
 * 商品Service
 *
 * Created by david-liu on 2017/05/02 09:33.
 */
public interface ProductService {
    ProductAppListRespVO searchProducts(ProductAppListReqVo reqVo);

    ProductAppDetailRespVO productDetail(ProductAppDetailReqVo reqVo);

    List<PurchaseProductItemVO> purchaseProducts(PurchaseProductReqVO reqVO);


}
