package com.biz.soa.product.service.interfaces.impl;

import com.biz.gbck.vo.stock.ProductCodeSellerIdStockReqVO;
import com.biz.gbck.vo.stock.ProductCodesSellerIdStockReqVO;
import com.biz.gbck.vo.stock.ProductStockVO;
import com.biz.soa.product.service.interfaces.IProductStockService;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 * Created by david-liu on 2017/05/16 17:44.
 */
@Service
public class ProductStockServiceImpl implements IProductStockService {
    @Override
    public List<ProductStockVO> productStocks(ProductCodesSellerIdStockReqVO reqVO) {
        List<Long> productIds = reqVO.getProductIds();
        List<ProductStockVO> stockVOS = Lists.newArrayList();
        productIds.forEach(productId -> {
            ProductStockVO stockVO = new ProductStockVO();
            stockVO.setProductId(productId);
            stockVO.setSellerId(1L);
            stockVO.setStock(1000);
            stockVOS.add(stockVO);
        });
        return stockVOS;
    }

    @Override
    public ProductStockVO productStock(ProductCodeSellerIdStockReqVO reqVO) {
        ProductStockVO stockVO = new ProductStockVO();
        stockVO.setProductId(reqVO.getProductId());
        stockVO.setSellerId(1L);
        stockVO.setStock(1000);
        return stockVO;
    }

    @Override
    public List<ProductStockVO> productStocks(List<ProductCodeSellerIdStockReqVO> reqVOS) {
        return reqVOS.stream().map(VO -> {
            ProductStockVO stockVO = new ProductStockVO();
            stockVO.setProductId(VO.getProductId());
            stockVO.setSellerId(1L);
            stockVO.setStock(1000);
            return stockVO;
        }).collect(Collectors.toList());
    }
}
