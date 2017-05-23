package com.biz.soa.stock.service;

import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.vo.stock.*;

import java.util.List;

/**
 * StockService
 *
 * @author lei
 * @date 2017年05月18日
 * @reviewer
 * @see
 */
public interface StockService {
    CompanyStockRespVO getStock(CompanyStockReqVO reqVo) throws DepotNextDoorException;

    List<CompanyStockRespVO> getStocks(CompanyStocksReqVO reqVo) throws DepotNextDoorException;

    void updateStocks(List<UpdateCompanyStockReqVO> reqVos) throws DepotNextDoorException;

    void orderUpdateLockStocks(List<UpdateCompanyLockStockReqVO> reqVos) throws DepotNextDoorException;

    void releaseLockStocks();
}
