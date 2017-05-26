package com.biz.soa.feign.client.stock;

import com.biz.gbck.vo.stock.*;
import com.biz.soa.feign.hystrix.stock.StockFeignClientHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 *
 * @author lei
 * @date 2017年05月16日
 * @reviewer
 * @see
 */
@FeignClient(name = "soa-stock", fallback = StockFeignClientHystrix.class)
public interface StockFeignClient {

    /**
     * 获取库存
     */
    @RequestMapping("/soa/stock/app/getStock")
    CompanyStockRespVO getStock(@RequestBody CompanyStockReqVO reqVo);

    /**
     * 获取批量库存
     */
    @RequestMapping("/soa/stock/app/getStocks")
    List<CompanyStockRespVO> getStocks(@RequestBody CompanyStocksReqVO reqVo);

    /**
     * 批量更新库存
     */
    @RequestMapping("/soa/stock/app/updateStocks")
    void updateStocks(@RequestBody List<UpdateCompanyStockReqVO> reqVos);

    /**
     * 批量更新锁定库存
     */
    @RequestMapping("/soa/stock/app/updateLockStocks")
    void orderUpdateLockStocks(@RequestBody List<UpdateCompanyLockStockReqVO> reqVos);

    /**
     * 释放锁定库存
     */
    @RequestMapping("/soa/stock/app/releaseLockStock")
    void releaseLockStock() ;


    @GetMapping(value = "/soa/stock/test")
    String getTestString();

}
