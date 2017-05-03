package com.biz.soa.product.service.interfaces;

import com.biz.gbck.vo.stock.ProductCodeSellerIdStockReqVO;
import com.biz.gbck.vo.stock.ProductCodesSellerIdStockReqVO;
import com.biz.gbck.vo.stock.ProductStockVO;
import java.util.List;

/**
 * 商品库存代理Service
 *
 * Created by david-liu on 2017/05/02 11:47.
 */
public interface IProductStockService {

    List<ProductStockVO> productStocks(ProductCodesSellerIdStockReqVO reqVO);

    ProductStockVO productStock(ProductCodeSellerIdStockReqVO reqVO);

}
