package com.biz.soa.feign.client.stock;

import com.biz.gbck.dao.mysql.po.stock.Stock;
import com.biz.gbck.vo.stock.StockShowVo;
import com.biz.service.stock.StockBackendService;
import com.biz.soa.feign.hystrix.stock.StockBackendFeignClientHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by lzz on 2017/5/19.
 */
@FeignClient(name = "soa-stock-service" ,fallback = StockBackendFeignClientHystrix.class)
public interface StockBackendFeignClient extends StockBackendService {

    @Override
    @RequestMapping(value = "soa/stockBackendService/findList", method = RequestMethod.POST)
    Page<Stock> findList(@RequestBody StockShowVo stockShowVo);
}
