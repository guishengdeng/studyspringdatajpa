package com.biz.soa.feign.hystrix.stock;

import com.biz.gbck.vo.stock.*;
import com.biz.soa.feign.client.stock.StockFeignClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by david-liu on 2017/05/04 09:46.
 */
@Component
public class StockFeignClientHystrix implements StockFeignClient {
    @Override
    public PartnerStockRespVO getStock(PartnerStockReqVO reqVo) {
        return null;
    }

    @Override
    public List<PartnerStockRespVO> getStocks(PartnerStocksReqVO reqVo) {
        return null;
    }

    @Override
    public void updateStocks(List<UpdatePartnerStockReqVO> reqVos) {

    }

    @Override
    public void orderUpdateLockStocks(List<UpdatePartnerLockStockReqVO> reqVos) {

    }

    @Override
    public void releaseLockStock() {

    }

    @Override
    public String getTestString() {
        return null;
    }
}
