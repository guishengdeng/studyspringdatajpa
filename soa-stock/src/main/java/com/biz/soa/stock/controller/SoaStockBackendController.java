package com.biz.soa.stock.controller;

import com.biz.gbck.dao.mysql.po.stock.CompanyStock;
import com.biz.gbck.vo.stock.StockShowVo;
import com.biz.soa.base.SoaBaseController;
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

//    @Autowired
//    @Qualifier("stockBackendService")
//    private StockBackendService stockBackendService;

    @RequestMapping(value = "search", method = RequestMethod.POST)
    Page<CompanyStock> search(@RequestBody StockShowVo stockShowVo){
        return null;
    }

}
