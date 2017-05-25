package com.biz.soa.stock.controller;

import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.vo.stock.*;
import com.biz.soa.base.SoaBaseController;
import com.biz.soa.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 库存soa controller
 *
 * @author lei
 * @date 2017年05月14日
 * @reviewer
 * @see
 */
@RestController
@RequestMapping(value = "/soa/stock")
public class SoaStockController extends SoaBaseController {

    @Autowired
    private StockService stockService;


    /**
     * 获取库存
     */
    @RequestMapping("/app/getStock")
    public CompanyStockRespVO getStock(@RequestBody CompanyStockReqVO reqVo) throws DepotNextDoorException{

        return stockService.getStock(reqVo);
    }

    /**
     * 获取批量库存
     */
    @RequestMapping("/app/getStocks")
    public List<CompanyStockRespVO> getStocks(@RequestBody CompanyStocksReqVO reqVo) throws DepotNextDoorException{
        return stockService.getStocks(reqVo);
    }

    /**
     * 添加购物车
     */
    @RequestMapping("/app/updateStocks")
    public void updateStocks(@RequestBody List<UpdateCompanyStockReqVO> reqVos) throws DepotNextDoorException{
        stockService.updateStocks(reqVos);
    }

    /**
     * 添加购物车
     */
    @RequestMapping("/app/updateLockStocks")
    public void orderUpdateLockStocks(@RequestBody List<UpdateCompanyLockStockReqVO> reqVos) throws DepotNextDoorException{
        stockService.orderUpdateLockStocks(reqVos);
    }

    /**
     * 添加购物车
     */
    @RequestMapping("/app/releaseLockStock")
    public void releaseLockStock() {
        stockService.releaseLockStocks();
    }


    @GetMapping(value = "/test")
    public String getTestString() {
        return "I am a test String";
    }

}
