package com.biz.soa.stock.service;

import com.biz.gbck.dao.redis.repository.stock.OrderStockLockRedisDao;
import com.biz.gbck.dao.redis.repository.stock.PartnerStockLockRedisDao;
import com.biz.gbck.dao.redis.repository.stock.PartnerStockRedisDao;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.vo.stock.*;
import com.biz.service.AbstractBaseService;
import com.biz.service.stock.StockService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * StockService
 *
 * @author lei
 * @date 2017年05月11日
 * @reviewer
 * @see
 */
public class StockServiceImpl extends AbstractBaseService implements StockService {


    @Autowired
    private OrderStockLockRedisDao orderStockLockRedisDao;

    @Autowired
    private PartnerStockRedisDao partnerStockRedisDao;

    @Autowired
    private PartnerStockLockRedisDao partnerStockLockRedisDao;


    @Override
    public PartnerStockRespVO getStock(PartnerStockReqVO reqVo) throws DepotNextDoorException {

    }

    @Override
    public List<PartnerStockRespVO> getStocks(PartnerStocksReqVO reqVo) throws DepotNextDoorException {
        return null;
    }

    @Override
    public void updateStocks(List<UpdatePartnerStockReqVO> reqVos) throws DepotNextDoorException {

    }

    @Override
    public void updateLockStocks(List<UpdatePartnerLockStockReqVO> reqVos) throws DepotNextDoorException {

    }
}
