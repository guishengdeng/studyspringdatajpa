package com.biz.service.stock;

import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.vo.stock.*;

import java.util.List;

/**
 * StockService
 *
 * @author lei
 * @date 2017年05月11日
 * @reviewer
 * @see
 */
public interface StockService {

    PartnerStockRespVO getStock(PartnerStockReqVO reqVo) throws DepotNextDoorException;

    List<PartnerStockRespVO> getStocks(PartnerStocksReqVO reqVo) throws DepotNextDoorException;

    void updateStocks(List<UpdatePartnerStockReqVO> reqVos) throws DepotNextDoorException;

    void orderLockStocks(List<UpdatePartnerLockStockReqVO> reqVos) throws DepotNextDoorException;

    void releaseLockStocks();


}
