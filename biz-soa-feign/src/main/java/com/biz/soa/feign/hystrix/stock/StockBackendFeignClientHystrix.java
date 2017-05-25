package com.biz.soa.feign.hystrix.stock;

import com.biz.gbck.dao.mysql.po.stock.CompanyStock;
import com.biz.gbck.vo.stock.StockShowVo;
import com.biz.soa.feign.client.stock.StockBackendFeignClient;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by lzz on 2017/5/19.
 */
@Deprecated
public class StockBackendFeignClientHystrix implements StockBackendFeignClient {

    @Override
    public Page<CompanyStock> findList(StockShowVo stockShowVo) {
        return null;
    }

}
