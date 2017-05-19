package com.biz.soa.stock.controller;

import com.biz.gbck.dao.mysql.po.stock.Stock;
import com.biz.gbck.vo.stock.StockShowVo;
import com.biz.service.stock.StockBackendService;
import com.biz.soa.base.SoaBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lzz on 2017/5/19.
 */
@RestController
@RequestMapping(value = "soa/stock")
public class SoaStockBackendController extends SoaBaseController{

    @Autowired
    @Qualifier("stockBackendService")
    private StockBackendService stockBackendService;

    @RequestMapping(value = "findList", method = RequestMethod.POST)
    Page<Stock> findList(@RequestBody StockShowVo stockShowVo){
        return stockBackendService.findList(stockShowVo);
    }

}
