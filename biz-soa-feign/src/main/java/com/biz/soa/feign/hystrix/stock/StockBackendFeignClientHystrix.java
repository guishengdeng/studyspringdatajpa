package com.biz.soa.feign.hystrix.stock;

import com.biz.gbck.dao.mysql.po.stock.Stock;
import com.biz.gbck.vo.stock.StockShowVo;
import com.biz.soa.feign.client.stock.StockBackendFeignClient;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by lzz on 2017/5/19.
 */
public class StockBackendFeignClientHystrix implements StockBackendFeignClient {

    @Override
    public Page<Stock> findList(StockShowVo stockShowVo) {
        return null;
    }

    @Override
    public List<StockShowVo> find() {
        return null;
    }
}
